# Plan: Hermes Onboarding App — Complete Incremental Upgrade

## Decisions
- Migration style: **Incremental** (low risk, phased delivery)
- Frontend: **Full SPA** (React/Vue.js) — Spring Boot becomes a pure REST API
- Java target: **Java 21 LTS**
- Security: Bundled within the upgrade phases (not a separate sprint)
- Testing: Out of scope for now

## Current Stack (Baseline)
- Java 1.8, Spring 4.2.5 (mixed 4.3.30), Spring Security 4.0.4
- Hibernate 5.2.1, C3P0 pool, MySQL/MariaDB
- JSP 2.1, jQuery 1.11.1, Bootstrap 4.0.0, DataTables
- Log4j 1.2.15, Apache Commons DBCP 1.4
- Maven WAR deployment (Tomcat)
- ~267 Java files, 15+ controllers, 50+ services, 40+ DAOs
- ~40 JSP views

## Critical Issues Found (security + quality)
- HARDCODED credentials in XML/properties/YAML: DB, AD, email passwords
- SQL injection in UserManagementDaoImpl (password change query)
- Race condition + path traversal in MoodleController (instance variable absolutePath)
- XSS in ReportController (unescaped strings in views)
- Password plaintext logging in CommonUtil
- Deprecated Hibernate Criteria API (will break in H6)
- OpenSessionInViewFilter anti-pattern (N+1 risk)
- No test coverage
- Log4j 1.2.15 (EOL), JUnit commented out
- ChartDirector.jar via system-scope (non-portable)
- Manual transaction management in UsersDaoImpl

---

## PHASE 1 — Security & Critical Fixes (Existing App, Zero Architecture Change)

**Goal**: Make the app secure without touching architecture. Done in-place on current stack.

### Steps
1. **Externalize all credentials** — move DB, AD, email passwords to environment variables using `${ENV_VAR}` in Spring XML configs and `activeDirectory.properties`. Create `.env.example` template.
2. **Fix SQL injection** — replace string-concatenated SQL in `UserManagementDaoImpl` (password change/lookup queries) with `PreparedStatement` or HQL named parameters.
3. **Remove password logging** — delete `logger.info("getPasswordBcrypt password :" + password)` in `CommonUtil.java`.
4. **Fix MoodleController race condition** — remove `this.absolutePath` instance variable; use method-local variables and pass file reference through method return/request.
5. **Fix XSS** — replace unescaped string insertion in `ReportController` views with `<c:out>` JSTL escaping in JSPs.
6. **Enable security compiler warnings** — uncomment `<Xlint:all>`, `<showDeprecation>true` in `pom.xml`.
7. **Fix hardcoded log path** — replace `C:/work/psa/onboarding/logs/onboard.log` in `log4j.properties` with system property like `${catalina.home}/logs/onboard.log`.
8. **Unify Spring versions** — align all Spring artifacts to `4.3.30.RELEASE` (current safe LTS).

**Files affected**:
- `src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml`
- `src/main/webapp/WEB-INF/spring/appServlet/security-context.xml`
- `src/main/resources/activeDirectory.properties`
- `src/main/resources/PSA_Config.yml`
- `src/main/resources/log4j.properties`
- `src/main/java/com/capgemini/onboarding/dao/impl/UserManagementDaoImpl.java`
- `src/main/java/com/capgemini/onboarding/util/CommonUtil.java`
- `src/main/java/com/capgemini/onboarding/MoodleController.java`
- JSPs using unescaped model attributes

---

## PHASE 2 — Spring Boot 3.x Migration (Backend Modernization)

**Goal**: Migrate backend to Spring Boot 3.x (Java 21, Spring 6.x, Hibernate 6.x). App still serves JSP temporarily.

### Steps
1. **Upgrade Java 8 → Java 21** in `pom.xml` compiler plugin. Fix any `javax.*` → `jakarta.*` namespace breaks.
2. **Introduce Spring Boot parent POM** (`spring-boot-starter-parent 3.3.x`) — remove individually managed dependency versions where Boot manages them.
3. **Replace C3P0 + Commons DBCP with HikariCP** — Spring Boot default; remove `c3p0` and `commons-dbcp` from pom, configure via `application.properties`.
4. **Migrate XML configs to Java `@Configuration` classes**:
   - `servlet-context.xml` → `WebMvcConfig.java` (component scan, ViewResolver, MessageSource)
   - `security-context.xml` + `spring-security.xml` → `SecurityConfig.java` (Spring Security 6.x DSL)
   - Remove `web.xml` → use `SpringBootServletInitializer` or embedded Tomcat
5. **Upgrade Hibernate 5.x → 6.x** (via Spring Boot 3.x BOM). Fix deprecated APIs:
   - `session.createCriteria()` → `session.createQuery()` with JPA `CriteriaBuilder`
   - `session.createSQLQuery()` → `session.createNativeQuery()`
   - Remove `OpenSessionInViewFilter` from web.xml — enable Spring Boot's `spring.jpa.open-in-view=false`
6. **Upgrade Spring Security 4.x → 6.x**:
   - Replace XML security config with `@Bean SecurityFilterChain` in `SecurityConfig.java`
   - Move role authorization from session strings in controllers → Spring Security method security (`@PreAuthorize`)
   - Add `@EnableMethodSecurity` on `SecurityConfig`
   - Fix session fixation: add `.sessionManagement().sessionFixation().newSession()`
7. **Replace Log4j 1.x → SLF4J + Logback** (Spring Boot default). Remove `log4j.properties`, add `logback-spring.xml`.
8. **Upgrade Apache POI 4.x → 5.x**. Upgrade Quartz → 2.3.x (if scheduler used). Remove JFreeChart 1.0.0, replace with Chart.js on frontend later.
9. **Remove ChartDirector system-scope dependency** from `pom.xml` — identify all usages in `PyramidController` and replace with Chart.js API call placeholder.
10. **Fix manual transaction management** in `UsersDaoImpl` — replace with `@Transactional` and Spring-managed session.
11. **Add `@ControllerAdvice`** `GlobalExceptionHandler.java` — centralize all `try/catch` currently duplicated in controllers.
12. **Add `@Async` for email sending** — annotate `PsaMailUtility` send methods and add `@EnableAsync`.

**New files**:
- `src/main/java/com/capgemini/onboarding/config/WebMvcConfig.java`
- `src/main/java/com/capgemini/onboarding/config/SecurityConfig.java`
- `src/main/java/com/capgemini/onboarding/config/AsyncConfig.java`
- `src/main/java/com/capgemini/onboarding/exception/GlobalExceptionHandler.java`
- `src/main/resources/application.properties` (replaces XML datasource config)
- `src/main/resources/logback-spring.xml`

**Key reference patterns**:
- SecurityConfig: use `http.authorizeHttpRequests()` chain (Spring Security 6)
- HikariCP: `spring.datasource.hikari.maximum-pool-size=20` (not 500)
- `@PreAuthorize("hasRole('ROLE_ADMIN')")` on controller methods instead of session checks

---

## PHASE 3 — REST API Layer

**Goal**: Build a clean REST API on the Spring Boot backend. JSP views still work. Frontend team can start in parallel.

### Steps
1. **Design API endpoint map** for all current functionality (one API module per domain):
   - `/api/employees` — CRUD, search, offboarding
   - `/api/preonboarding` — pre-onboarding records
   - `/api/training` — training allocation
   - `/api/users` — user management, roles, password
   - `/api/dashboard` — chart data, statistics
   - `/api/reports` — employee lists, FTE, BIS reports
   - `/api/moodle` — moodle integration
2. **Create REST controllers** (new classes, not replacing existing MVC controllers yet):
   - Use `@RestController` + `@RequestMapping("/api/...")`
   - Use `ResponseEntity<T>` return types
   - Map to existing service layer — zero service changes needed
3. **Create DTO classes** for all API responses — decouple entities from API surface:
   - `EmployeeDTO`, `EmployeeSearchDTO`, `DashboardStatsDTO`, `TrainingDTO`, etc.
   - Use MapStruct or manual mapping in service layer
4. **Replace session-based authentication with JWT**:
   - Add `spring-boot-starter-security` + `jjwt` library
   - Create `JwtTokenProvider`, `JwtAuthFilter`
   - Add `/api/auth/login` endpoint returning JWT token
   - Keep form login for legacy JSP routes during transition period
   - Add `/api/auth/password/change` endpoint
5. **Add OpenAPI/Swagger 3** documentation:
   - Add `springdoc-openapi-starter-webmvc-ui` dependency
   - Auto-generates `/swagger-ui.html` from `@RestController` annotations
6. **Configure CORS** — add `@CrossOrigin` or global `CorsConfigurationSource` bean for frontend origin.
7. **Migrate authorization** to REST API layer — `@PreAuthorize` annotations on REST endpoints using actual Spring Security roles.

**New files**:
- `src/main/java/com/capgemini/onboarding/api/EmployeeApiController.java` (and similar for each domain)
- `src/main/java/com/capgemini/onboarding/dto/*.java` (DTO classes)
- `src/main/java/com/capgemini/onboarding/security/JwtTokenProvider.java`
- `src/main/java/com/capgemini/onboarding/security/JwtAuthFilter.java`

---

## PHASE 4 — React SPA Frontend

**Goal**: Build new React frontend consuming the REST API. Runs alongside the JSP app initially.

### Tech choices
- **React 18 + TypeScript + Vite** — fast build, best ecosystem
- **React Router v6** — client-side routing
- **Axios** — HTTP client for API calls
- **TanStack Query (React Query)** — server state management + caching
- **Zustand** — lightweight auth state
- **Tailwind CSS** — replaces Bootstrap
- **AG Grid Community** — replaces DataTables (handles large grids)
- **Recharts** — replaces JFreeChart/ChartDirector
- **React Hook Form + Zod** — replaces jquery.validate

### Steps
1. **Initialize React project** — `npm create vite@latest hermes-ui -- --template react-ts` in a `/frontend` folder at project root.
2. **Build authentication flow** — JWT login page, token storage (httpOnly cookie or memory), auth context.
3. **One-by-one replace each JSP view with a React page**:
   - Dashboard (charts, stat cards)
   - Employee list / search
   - Add/Edit employee forms
   - Pre-onboarding search + edit
   - Offboarding form
   - Training management
   - Reports (FTE, BIS, offboarding report)
   - User management
   - Moodle enrolment
   - Change password
4. **Configure API base URL** via environment variable `VITE_API_BASE_URL`.
5. **Build/integrating strategy**: During transition, Spring Boot serves both `/` → JSP (legacy) and `/api/**` → REST. React app can be served from a separate port or proxied. After full migration, Spring Boot serves only `/api/**` and React app is served by a CDN or Nginx.

**Frontend project structure**:
```
/frontend
  src/
    api/           # Axios API clients
    components/    # Reusable UI components
    pages/         # One file per screen
    hooks/         # Custom React hooks
    store/         # Zustand auth store
    types/         # TypeScript types (DTOs)
```

---

## PHASE 5 — Cutover & JSP Decommission

**Goal**: Switch fully to React frontend, remove all JSP views.

### Steps
1. Verify all JSP views have React equivalents and REST API endpoints
2. UAT (user acceptance testing) on React app
3. Switch default route from JSP welcome page to React app
4. Keep Spring Boot serving `/api/**` only
5. Remove all `*.jsp` files from `src/main/webapp/WEB-INF/views/`
6. Remove legacy Spring MVC controllers (non-API)
7. Remove JSP/JSTL dependencies from pom.xml
8. Remove legacy JS files: jQuery 1.11.1, common.js, DataTables, validation libs

---

## PHASE 6 — Performance & DevOps

### Performance
1. Add **2nd-level Hibernate cache** — add `spring-boot-starter-cache` + Caffeine
2. Fix **N+1 queries** — add `@EntityGraph` or `JOIN FETCH` in DAO queries for Employee + associations
3. Add **missing DB indexes** — `employee(first_name, last_name, email, joining_date)`, `employee_training(emp_id, training_id)`
4. Tune HikariCP — `maximum-pool-size=20`, `minimum-idle=5`, `connection-timeout=30000`
5. Add **Redis caching** for dashboard stats (change frequently but not real-time)
6. Enable HTTP **response compression** in Spring Boot (`server.compression.enabled=true`)

### DevOps
1. Add `Dockerfile` — multi-stage build (Maven build → Tomcat/OpenJDK 21 runtime)
2. Add `docker-compose.yml` — app + MySQL + Redis
3. Add `.github/workflows/ci.yml` — Maven build + test
4. Add `.mvn/wrapper/` — Maven wrapper for consistent builds
5. Add **Spring Boot Actuator** (`/actuator/health`, `/actuator/metrics`) for monitoring
6. Add SonarQube config (`sonar-project.properties`) for code quality

---

## Relevant Files (Current)
- `pom.xml` — dependencies, plugins, Java version
- `src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml` — datasource, Spring MVC config
- `src/main/webapp/WEB-INF/spring/appServlet/security-context.xml` — security datasource
- `src/main/webapp/WEB-INF/spring/appServlet/spring-security.xml` — Spring Security config
- `src/main/webapp/WEB-INF/web.xml` — servlet config, filters
- `src/main/resources/hibernate.cfg.xml` — Hibernate config
- `src/main/resources/activeDirectory.properties` — AD credentials (to be externalized)
- `src/main/resources/PSA_Config.yml` — email credentials (to be externalized)
- `src/main/java/com/capgemini/onboarding/dao/impl/UserManagementDaoImpl.java` — SQL injection
- `src/main/java/com/capgemini/onboarding/util/CommonUtil.java` — password logging
- `src/main/java/com/capgemini/onboarding/MoodleController.java` — race condition
- `src/main/java/com/capgemini/onboarding/dao/impl/EmployeeDAOImpl.java` — deprecated Criteria API, N+1

---

## Verification (per phase)
- **Phase 1**: Run app, verify no credentials in source, test password change (no injection), confirm no plaintext password in logs
- **Phase 2**: App boots on Java 21, all existing pages load, Hibernate queries work
- **Phase 3**: Swagger UI shows all endpoints, Postman test each API endpoint, JWT auth works
- **Phase 4**: React app loads, all pages functional, matches JSP feature parity
- **Phase 5**: All user flows tested in React, no 404s, JSP files deleted, WAR size reduced
- **Phase 6**: DB indexes verified, `/actuator/health` responds, Docker image builds

---

## Scope Boundaries
- **Included**: All 6 phases above
- **Excluded**: Unit tests (out of scope per user decision), Moodle LMS server changes, AD/LDAP server changes
- **Deferred**: OAuth2 migration (partially attempted in codebase, not included in current plan)
