package com.capgemini.onboarding.dao;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.constants.OnBoardingSQLQueries;
import com.capgemini.onboarding.constants.OnboardingConstants;
import com.capgemini.onboarding.model.Bis;
import com.capgemini.onboarding.model.BisPMOMap;
import com.capgemini.onboarding.model.BundleEm;
import com.capgemini.onboarding.model.CGEntityDetail;
import com.capgemini.onboarding.model.Country;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.GlobalGrade;
import com.capgemini.onboarding.model.Grade;
import com.capgemini.onboarding.model.OffshoreEm;
import com.capgemini.onboarding.model.PreOnbEmployee;
import com.capgemini.onboarding.model.ReplacementType;
import com.capgemini.onboarding.model.RollOffType;
import com.capgemini.onboarding.model.Spoc;
import com.capgemini.onboarding.model.State;
import com.capgemini.onboarding.model.Technology;
import com.capgemini.onboarding.model.Users;
import com.capgemini.onboarding.service.BisService;
import com.capgemini.onboarding.service.BundleEmService;
import com.capgemini.onboarding.service.CgEntityService;
import com.capgemini.onboarding.service.CountryService;
import com.capgemini.onboarding.service.EmployeeService;
import com.capgemini.onboarding.service.EntityService;
import com.capgemini.onboarding.service.GlobalGradeService;
import com.capgemini.onboarding.service.GradeService;
import com.capgemini.onboarding.service.OffshoreEmService;
import com.capgemini.onboarding.service.ResourceManagerService;
import com.capgemini.onboarding.service.SpocService;
import com.capgemini.onboarding.service.StateService;
import com.capgemini.onboarding.service.TechnologyService;
import com.capgemini.onboarding.service.TrainingService;
import com.capgemini.onboarding.service.VendorService;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired(required = true)
	private EmployeeService employeeService;

	@Autowired(required = true)
	private GradeService gradeService;

	@Autowired(required = true)
	private TechnologyService technologyService;

	@Autowired(required = true)
	private CountryService countryService;

	@Autowired(required = true)
	private StateService stateService;

	@Autowired(required = true)
	private VendorService vendorService;

	@Autowired(required = true)
	private SpocService spocService;

	@Autowired(required = true)
	private GlobalGradeService globalGradeService;

	@Autowired(required = true)
	private TrainingService trainingService;

	@Autowired(required = true)
	private EntityService entityService;

	@Autowired(required = true)
	private CgEntityService cgEntityService;

	@Autowired(required = true)
	private OffshoreEmService offShoreEmService;
	
	@Autowired(required = true)
    private BundleEmService bundleEmService;
	
	@Autowired(required = true)
	private ResourceManagerService resourceMgrService;

	@Autowired(required = true)
	private BisService bisService;

	private Object values;
	

	@Override
	public void addEmployee(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();
		/*
		 * boolean isExistRecording = false; List<Employee> empList =
		 * addEmployeeDetailsExist(employee); if (empList != null &&
		 * !empList.isEmpty()) { isExistRecording = true; logger.info(
		 * "The same resource is already present with overlapping dates. Please enter non-overlapping dates"
		 * + empList);
		 * 
		 * } else { isExistRecording = false;
		 */
		session.saveOrUpdate(employee);
		logger.info("employee saved successfully, Employee Details=" + employee);
		/*
		 * } return isExistRecording;
		 */
	}

	@Override
	public void updateEmployee(Employee employee) {
		Session session = this.sessionFactory.getCurrentSession();
		/*
		 * boolean isExistRecording = false; List<Employee> empList =
		 * editEmployeeDetailsExist(employee); if (empList != null &&
		 * !empList.isEmpty()) { isExistRecording = true; logger.info(
		 * "The same resource is already present with overlapping dates. Please enter non-overlapping dates"
		 * + empList);
		 * 
		 * } else {
		 */
		
		session.clear();
		
		session.saveOrUpdate(employee);
		
		
		logger.info("employee saved successfully, Employee Details=" + employee);
		// }
		// return isExistRecording;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> listManagers() {
		List<Employee> listEmployees = new ArrayList<>();
		Session session = this.sessionFactory.getCurrentSession();
		/*
		 * TypedQuery<Employee> query =
		 * session.createNamedQuery("listManagers");
		 */

		String queryString = OnBoardingSQLQueries.ListOfManager;
		TypedQuery<Employee> query = session.createSQLQuery(queryString);

		listEmployees = query.getResultList();
		List<Employee> managerList = new ArrayList<>();
		Iterator itr = listEmployees.iterator();
		while (itr.hasNext()) {
			Employee emp = new Employee();
			Object[] obj = (Object[]) itr.next();
			emp.setId(Long.parseLong(String.valueOf(obj[0])));
			if (String.valueOf(obj[1]) != null) {
				emp.setEmpId(String.valueOf(obj[1]));
			}
			if (String.valueOf(obj[2]) != null) {
				emp.setFirstName(String.valueOf(obj[2]));
			}
			if (String.valueOf(obj[3]) != null) {
				emp.setLastName(String.valueOf(obj[3]));
			}
			if (String.valueOf(obj[4]) != null) {
				emp.setCorpId(String.valueOf(obj[4]));
			}
			if (String.valueOf(obj[4]) != null) {
				emp.setEmail(String.valueOf(obj[5]));
			}
			managerList.add(emp);
		}
		Collections.sort(managerList);

		return managerList;
	}

	@Override
	public Employee getEmployeeById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee p = (Employee) session.get(Employee.class, id);
		logger.info("Person loaded successfully, Person details=" + p);
		session.clear();
	
		return p;
	}
	
	@Override
	public Employee getEmployee(Long id) {//mehens
		
		Session session = this.sessionFactory.getCurrentSession();
		TypedQuery<Employee> query = session.createNamedQuery("listEmployeeById");
		query.setParameter("id", id);
		List<Employee> rs = query.getResultList();
		Employee emp = rs.get(0);
		
		logger.info("Emp loaded successfully, Emp Id=" + emp.getId());
		return emp;
	}

	@Override
	public void removeEmployee(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Employee p = (Employee) session.load(Employee.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
		logger.info("Person deleted successfully, person details=" + p);
	}

	@Override
	public List<Employee> getEmployeesByGrade(List<Integer> gradeList) {
		Session session = this.sessionFactory.getCurrentSession();
		TypedQuery<Employee> query = session.createNamedQuery("listEmployeesByGrade");
		query.setParameter("gradeList", gradeList);
		return query.getResultList();
	}

	@Override
	public List<Employee> getAllEmployees() {
		Session session = this.sessionFactory.getCurrentSession();
		/*
		 * String role_value =
		 * SecurityContextHolder.getContext().getAuthentication().getAuthorities
		 * ().toString(); String loggedUser =
		 * SecurityContextHolder.getContext().getAuthentication().getName();
		 * 
		 * TypedQuery<Employee> query1 =
		 * session.createNamedQuery("getLoggedUserID");
		 * query1.setParameter("corpID", loggedUser);
		 * 
		 * Long loggedUserID = query1.getResultList().get(0).getId(); Criteria
		 * criteria = session.createCriteria(Employee.class);
		 * if(role_value.equalsIgnoreCase(OnboardingConstants.Bundle_EM)){
		 * criteria.add(Restrictions.eq("bundleEM.id", loggedUserID)); } else
		 * if(role_value.equalsIgnoreCase(OnboardingConstants.EM)){
		 * criteria.add(Restrictions.eq("EM.id", loggedUserID)); }
		 * 
		 * 
		 * return criteria.list();
		 */
		TypedQuery<Employee> query = session.createNamedQuery("listAllEmployees");
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> searchEmployees(Employee emp) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
		String empId = emp.getEmpId();
		String corpId = emp.getCorpId();
		String firstName = emp.getFirstName();
		String lastName = emp.getLastName();
		String psaId =emp.getPsaId();
	
		if (empId != null && !empId.equalsIgnoreCase("")) {
			criteria.add(Restrictions.ilike("empId", empId, MatchMode.ANYWHERE));
		}
		if (corpId != null && !corpId.equalsIgnoreCase("")) {
			criteria.add(Restrictions.ilike("corpId", corpId, MatchMode.ANYWHERE));
		}
		if (firstName != null && !firstName.equalsIgnoreCase("")) {
			criteria.add(Restrictions.ilike("firstName", firstName, MatchMode.ANYWHERE));
		}
		if (lastName != null && !lastName.equalsIgnoreCase("")) {
			criteria.add(Restrictions.ilike("lastName", lastName, MatchMode.ANYWHERE));
		}
		
		if (psaId != null && !psaId.equalsIgnoreCase("")) {
			criteria.add(Restrictions.ilike("psaId",psaId, MatchMode.ANYWHERE));
		}
		
		criteria.add(Restrictions.ne("id", new Long(0)));

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> searchEmployeeReport(Employee emp, Integer limit) {
		Session session = this.sessionFactory.getCurrentSession();

		Calendar cal = Calendar.getInstance();
		Date currentdate = cal.getTime();
		currentdate.setHours(0);
		currentdate.setMinutes(0);
		currentdate.setSeconds(0);
		//LocalDate currentdate = LocalDate.now();
		
		Criteria criteria = session.createCriteria(Employee.class);
		String empType = emp.getEmpType();
		Integer country = null;
		Integer bis = null;
		Long buddyId=null;
		if(limit != null) {
			criteria.setMaxResults(limit);
		}
		String totalTrainingStatusWeek = emp.getTotalWeeks();
		if (emp.getCountry() != null) {
			country = emp.getCountry().getCountryId();
		}
		if (emp.getBis() != null) {
			bis = emp.getBis().getBis_id();
		}
		/*if(emp.getId()!=null)
		{
			buddyId =emp.getId();
		}
		*/
		Date joiningDate = emp.getJoiningDate();
		Date endDate = emp.getActualEndDate();
		String resStatus = emp.getResourceStatus();

		if (joiningDate != null) {
			criteria.add(Restrictions.ge("joiningDate", joiningDate));
		} else if(resStatus.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_SHADOW)) {
			criteria.add(Restrictions.le("joiningDate", currentdate));
			criteria.add(Restrictions.gt("billingDate", currentdate));
		}else if(resStatus.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_BILLABLE)){
			criteria.add(Restrictions.le("billingDate", currentdate));
		}else if(resStatus.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_ACTIVE)){ //new
			criteria.add(Restrictions.le("joiningDate", currentdate));
		}

	if (endDate != null) {
			criteria.add(Restrictions.le("actualEndDate", endDate));
		}

		if (OnboardingConstants.ALL.equalsIgnoreCase(empType)) {
			criteria.add(Restrictions.in("empType", new String[] { OnboardingConstants.EMP_TYPE_INTERNAL, OnboardingConstants.EMP_TYPE_EXTERNAL }));
		} else if (empType != null) {
			criteria.add(Restrictions.ilike("empType", empType, MatchMode.ANYWHERE));
		}

		logger.info(""+Integer.MAX_VALUE);
		
		if (resStatus.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_ACTIVE)) {

			//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
			//if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
				Criterion joiningdateCondtn = Restrictions.le("joiningDate", currentdate);

				//Criterion finalShadowCondtn = Restrictions.and(showDateCondtn, checkShadowDate);
				//Criterion onboardingDateCondtn = Restrictions.or(joiningdateCondtn, finalShadowCondtn);
				criteria.add(joiningdateCondtn);//onboardingDateCondtn

				Criterion c1 = Restrictions.isNotNull("actualEndDate");
				Criterion c2 = Restrictions.ge("actualEndDate", currentdate);
				LogicalExpression conjActualEndDate = Restrictions.and(c1, c2);

				Criterion c3 = Restrictions.isNull("actualEndDate");
				Criterion c4 = Restrictions.isNotNull("endDate");
				Criterion c5 = Restrictions.ge("endDate", currentdate);
				LogicalExpression logiclConj = Restrictions.and(c3, Restrictions.and(c4, c5));
				criteria.add(Restrictions.or(conjActualEndDate, logiclConj));
				
				
				/*}

			else {

				Criterion joiningdateCondtn = Restrictions.le("joiningDate", currentdate);
				criteria.add(joiningdateCondtn);
				Criterion c1 = Restrictions.isNotNull("actualEndDate");
				Criterion c2 = Restrictions.ge("actualEndDate", currentdate);
				LogicalExpression conjActualEndDate = Restrictions.and(c1, c2);

				Criterion c3 = Restrictions.isNull("actualEndDate");
				Criterion c4 = Restrictions.isNotNull("endDate");
				Criterion c5 = Restrictions.ge("endDate", currentdate);
				LogicalExpression logiclConj = Restrictions.and(c3, Restrictions.and(c4, c5));
				criteria.add(Restrictions.or(conjActualEndDate, logiclConj));
			}*/

			// resStatus = OnBoardingSQLQueries.ResourceActiveEmployees;
			// logger.info(resStatus);

		} else if (resStatus.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_ALL)) {

			

			//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
			
				/*Criterion joiningdateCondtn = Restrictions.le("joiningDate", currentdate);
				criteria.add(joiningdateCondtn);*/
			
		}else if(resStatus.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_BILLABLE)) {

			
				Criterion billingNotNull = Restrictions.isNotNull("billingDate");
				Criterion billingdate = Restrictions.le("billingDate", currentdate);
				
				Criterion billingdateCondtn = Restrictions.and(billingNotNull, billingdate);
				
				criteria.add(billingdateCondtn);

				Criterion c1 = Restrictions.isNotNull("actualEndDate");
				Criterion c2 = Restrictions.ge("actualEndDate", currentdate);
				LogicalExpression conjActualEndDate = Restrictions.and(c1, c2);

				Criterion c3 = Restrictions.isNull("actualEndDate");
				Criterion c4 = Restrictions.isNotNull("endDate");
				Criterion c5 = Restrictions.ge("endDate", currentdate);
				LogicalExpression logiclConj = Restrictions.and(c3, Restrictions.and(c4, c5));
				criteria.add(Restrictions.or(conjActualEndDate, logiclConj));
			

		}else if(resStatus.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_SHADOW)) {
			
			Criterion joiningdateCondtn = Restrictions.le("joiningDate", currentdate);
			criteria.add(joiningdateCondtn);
			
			Criterion billingNotNull = Restrictions.isNotNull("billingDate");
			Criterion billingdate = Restrictions.gt("billingDate", currentdate);
			
			Criterion billingdateCondtn = Restrictions.and(billingNotNull, billingdate);
			
			criteria.add(billingdateCondtn);
			
			Criterion c1 = Restrictions.isNotNull("actualEndDate");
			Criterion c2 = Restrictions.ge("actualEndDate", currentdate);
			LogicalExpression conjActualEndDate = Restrictions.and(c1, c2);

			Criterion c3 = Restrictions.isNull("actualEndDate");
			Criterion c4 = Restrictions.isNotNull("endDate");
			Criterion c5 = Restrictions.ge("endDate", currentdate);
			LogicalExpression logiclConj = Restrictions.and(c3, Restrictions.and(c4, c5));
			criteria.add(Restrictions.or(conjActualEndDate, logiclConj));
		}

		if (totalTrainingStatusWeek != null && !totalTrainingStatusWeek.isEmpty()) {
			String sqlWhere = "{alias}.joining_date <= DATE_SUB(curdate(),INTERVAL " + totalTrainingStatusWeek
					+ " DAY) and {alias}.security_training = 0";
			criteria.add(Restrictions.sqlRestriction(sqlWhere));
		}
		if (country != null && country > 0) {
			criteria.add(Restrictions.eq("country.countryId", country));
		}
		if (bis != null && bis > 0) {
			criteria.add(Restrictions.eq("bis.bis_id", bis));
		}
		/*if (buddyId != null && buddyId > 0) {
			criteria.add(Restrictions.eq("buddy_Id", buddyId));
		}*/

		int heritageType = emp.getHeritageType();
		if (heritageType > 0) {
			criteria.add(Restrictions.eq("heritageType", heritageType));
		}

		//criteria.add(Restrictions.ne("id", new Long(0)));

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> searchEmpReportWithoutPlannedOffBoardDate(Employee emp, Integer limit) {
		Session session = this.sessionFactory.getCurrentSession();
		Calendar cal = Calendar.getInstance();
		Date currentdate = cal.getTime();
		currentdate.setHours(0);
		currentdate.setMinutes(0);
		currentdate.setSeconds(0);
		//LocalDate currentdate = LocalDate.now();
		
		Criteria criteria = session.createCriteria(Employee.class);
		String empType = emp.getEmpType();
		Integer country = null;
		Integer bis = null;
		Long buddyId=null;
		if(limit != null) {
			criteria.setMaxResults(limit);
		}
		String totalTrainingStatusWeek = emp.getTotalWeeks();
		if (emp.getCountry() != null) {
			country = emp.getCountry().getCountryId();
		}
		if (emp.getBis() != null) {
			bis = emp.getBis().getBis_id();
		}
		/*if(emp.getId()!=null)
		{
			buddyId =emp.getId();
		}
		*/
		Date joiningDate = emp.getJoiningDate();
		Date endDate = emp.getActualEndDate();
		String resStatus = emp.getResourceStatus();

		if (joiningDate != null) {
			criteria.add(Restrictions.ge("joiningDate", joiningDate));
		} else if(resStatus.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_SHADOW)) {
			criteria.add(Restrictions.le("joiningDate", currentdate));
			criteria.add(Restrictions.gt("billingDate", currentdate));
		}else if(resStatus.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_BILLABLE)){
			criteria.add(Restrictions.le("billingDate", currentdate));
		}else if(resStatus.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_ACTIVE)){ //new
			criteria.add(Restrictions.le("joiningDate", currentdate));
		}

	if (endDate != null) {
			criteria.add(Restrictions.le("actualEndDate", endDate));
		}

		if (OnboardingConstants.ALL.equalsIgnoreCase(empType)) {
			criteria.add(Restrictions.in("empType", new String[] { OnboardingConstants.EMP_TYPE_INTERNAL, OnboardingConstants.EMP_TYPE_EXTERNAL }));
		} else if (empType != null) {
			criteria.add(Restrictions.ilike("empType", empType, MatchMode.ANYWHERE));
		}

		logger.info(""+Integer.MAX_VALUE);
		
		if (resStatus.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_ACTIVE)) {

			//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
			//if (role_id.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
				Criterion joiningdateCondtn = Restrictions.le("joiningDate", currentdate);

				//Criterion finalShadowCondtn = Restrictions.and(showDateCondtn, checkShadowDate);
				//Criterion onboardingDateCondtn = Restrictions.or(joiningdateCondtn, finalShadowCondtn);
				criteria.add(joiningdateCondtn);//onboardingDateCondtn

				Criterion c1 = Restrictions.isNotNull("actualEndDate");
				Criterion c2 = Restrictions.ge("actualEndDate", currentdate);
				LogicalExpression conjActualEndDate = Restrictions.and(c1, c2);

				Criterion c3 = Restrictions.isNull("actualEndDate");
				Criterion c4 = Restrictions.isNotNull("endDate");
				Criterion c5 = Restrictions.ge("endDate", currentdate);
				LogicalExpression logiclConj = Restrictions.and(c3, Restrictions.and(c4, c5));
				//criteria.add(Restrictions.or(conjActualEndDate, logiclConj));
				criteria.add(Restrictions.or(conjActualEndDate, c3));//mehens-new
				
				
				/*}

			else {

				Criterion joiningdateCondtn = Restrictions.le("joiningDate", currentdate);
				criteria.add(joiningdateCondtn);
				Criterion c1 = Restrictions.isNotNull("actualEndDate");
				Criterion c2 = Restrictions.ge("actualEndDate", currentdate);
				LogicalExpression conjActualEndDate = Restrictions.and(c1, c2);

				Criterion c3 = Restrictions.isNull("actualEndDate");
				Criterion c4 = Restrictions.isNotNull("endDate");
				Criterion c5 = Restrictions.ge("endDate", currentdate);
				LogicalExpression logiclConj = Restrictions.and(c3, Restrictions.and(c4, c5));
				criteria.add(Restrictions.or(conjActualEndDate, logiclConj));
			}*/

			// resStatus = OnBoardingSQLQueries.ResourceActiveEmployees;
			// logger.info(resStatus);

		} else if (resStatus.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_ALL)) {

			

			//String role_id = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
			
				/*Criterion joiningdateCondtn = Restrictions.le("joiningDate", currentdate);
				criteria.add(joiningdateCondtn);*/
			
		}else if(resStatus.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_BILLABLE)) {

			
				Criterion billingNotNull = Restrictions.isNotNull("billingDate");
				Criterion billingdate = Restrictions.le("billingDate", currentdate);
				
				Criterion billingdateCondtn = Restrictions.and(billingNotNull, billingdate);
				
				criteria.add(billingdateCondtn);

				Criterion c1 = Restrictions.isNotNull("actualEndDate");
				Criterion c2 = Restrictions.ge("actualEndDate", currentdate);
				LogicalExpression conjActualEndDate = Restrictions.and(c1, c2);

				Criterion c3 = Restrictions.isNull("actualEndDate");
				Criterion c4 = Restrictions.isNotNull("endDate");
				Criterion c5 = Restrictions.ge("endDate", currentdate);
				LogicalExpression logiclConj = Restrictions.and(c3, Restrictions.and(c4, c5));
				//criteria.add(Restrictions.or(conjActualEndDate, logiclConj));
				criteria.add(Restrictions.or(conjActualEndDate, c3));//mehens-new
			

		}else if(resStatus.equalsIgnoreCase(OnboardingConstants.EMP_RESOURCE_SHADOW)) {
			
			Criterion joiningdateCondtn = Restrictions.le("joiningDate", currentdate);
			criteria.add(joiningdateCondtn);
			
			Criterion billingNotNull = Restrictions.isNotNull("billingDate");
			Criterion billingdate = Restrictions.gt("billingDate", currentdate);
			
			Criterion billingdateCondtn = Restrictions.and(billingNotNull, billingdate);
			
			criteria.add(billingdateCondtn);
			
			Criterion c1 = Restrictions.isNotNull("actualEndDate");
			Criterion c2 = Restrictions.ge("actualEndDate", currentdate);
			LogicalExpression conjActualEndDate = Restrictions.and(c1, c2);

			Criterion c3 = Restrictions.isNull("actualEndDate");
			Criterion c4 = Restrictions.isNotNull("endDate");
			Criterion c5 = Restrictions.ge("endDate", currentdate);
			LogicalExpression logiclConj = Restrictions.and(c3, Restrictions.and(c4, c5));
			//criteria.add(Restrictions.or(conjActualEndDate, logiclConj));
			criteria.add(Restrictions.or(conjActualEndDate, c3));//mehens-new
		}

		if (totalTrainingStatusWeek != null && !totalTrainingStatusWeek.isEmpty()) {
			String sqlWhere = "{alias}.joining_date <= DATE_SUB(curdate(),INTERVAL " + totalTrainingStatusWeek
					+ " DAY) and {alias}.security_training = 0";
			criteria.add(Restrictions.sqlRestriction(sqlWhere));
		}
		if (country != null && country > 0) {
			criteria.add(Restrictions.eq("country.countryId", country));
		}
		if (bis != null && bis > 0) {
			criteria.add(Restrictions.eq("bis.bis_id", bis));
		}
		/*if (buddyId != null && buddyId > 0) {
			criteria.add(Restrictions.eq("buddy_Id", buddyId));
		}*/

		int heritageType = emp.getHeritageType();
		if (heritageType > 0) {
			criteria.add(Restrictions.eq("heritageType", heritageType));
		}

		//criteria.add(Restrictions.ne("id", new Long(0)));

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
	
	@Override 
	public void offboardEmployee(Date endDate, Long id, Employee employee, Employee updatedEmpVal) {
		Session session = this.sessionFactory.getCurrentSession();
		/*
		 * TypedQuery<Employee> query =
		 * session.createNamedQuery("offBoardEmployee");
		 * query.setParameter("endDate", endDate); query.setParameter("id", id);
		 * query.executeUpdate();
		 */
		employee.setActualEndDate(endDate);
		employee.setId(id);
		employee.setRr_no(updatedEmpVal.getRr_no());
		employee.setRepCorpId(updatedEmpVal.getRepCorpId());
		employee.setRepfirstName(updatedEmpVal.getRepfirstName());
		employee.setReplacementRequired(updatedEmpVal.isReplacementRequired());
		employee.setReplacementType(updatedEmpVal.getReplacementType());
		employee.setRollOffType(updatedEmpVal.getRollOffType());
		employee.setOffboardcomment(updatedEmpVal.getOffboardcomment());

		
		
		session.saveOrUpdate(employee);
		logger.info("Offboarding Date updated successfully, endDate = " + endDate);
	}

	@Override
	public List<Employee> addEmployeeDetailsExist(Employee emp) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
		String empId = emp.getEmpId();
		Date joiningDate = emp.getJoiningDate();
		Date endDate = emp.getEndDate();
		if (empId != null) {
			criteria.add(Restrictions.ilike("empId", empId));
		}
		if (joiningDate != null) {
			criteria.add(Restrictions.ge("joiningDate", joiningDate));
		}
		if (endDate != null) {
			criteria.add(Restrictions.le("endDate", endDate));
		}

		return criteria.list();
	}

	@Override
	public List<Employee> editEmployeeDetailsExist(Employee emp) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
		String empId = emp.getEmpId();
		Date joiningDate = emp.getJoiningDate();
		Date endDate = emp.getEndDate();
		if (empId != null) {
			criteria.add(Restrictions.ilike("empId", empId));
		}
		if (joiningDate != null) {
			criteria.add(Restrictions.ge("joiningDate", joiningDate));
			criteria.add(Restrictions.not(Restrictions.in("joiningDate", joiningDate)));
		}
		if (endDate != null) {
			criteria.add(Restrictions.le("endDate", endDate));
			criteria.add(Restrictions.not(Restrictions.in("endDate", endDate)));
		}

		return criteria.list();
	}

	@Override
	public boolean checkEmployeeExists(String empId, String empType) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
		boolean isExist = false;
		if (empId != null && !empId.equalsIgnoreCase("")) {
			if (empType.equalsIgnoreCase("Internal")) {
				criteria.add(Restrictions.ilike("empId", empId));
			} else {
				criteria.add(Restrictions.ilike("psaId", empId));
			}

		}
		List<Employee> empList = criteria.list();
		if (empList != null && !empList.isEmpty()) {
			isExist = true;
		}
		return isExist;
	}
	
	public Employee checkArchivedEmployeeExists(String corpId) {
		
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
		boolean isArchivedExist = false;
		try {
			if(!corpId.isEmpty()) {
				criteria.add(Restrictions.ilike("corpId", corpId));
				criteria.add(Restrictions.isNotNull("archiveType"));
				//criteria.add(Projections.max("id"));
				criteria.addOrder(Order.desc("id"));
				List<Employee> allarchivedEmp = criteria.list();
				return allarchivedEmp.get(0);
			}
		}
		catch(Exception e){
			logger.info("Issue in checkArchivedEmployeeExists for corp id "+corpId);
			logger.info(e.getMessage());
			
		}
		return null;
	}

	@Override
	public boolean checkCorpIdExists(String corpId, String empId, String empType,String pageType) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
		boolean isExist = false;
		
		if (corpId != null && !corpId.equalsIgnoreCase("")) {
			criteria.add(Restrictions.ilike("corpId", corpId));
		}
		List<Employee> emplist = criteria.list();
		if (emplist != null && !emplist.isEmpty()) {
			isExist = true;
		}
		
		return isExist;
	}
	
	

	@Override
	public boolean checkPsaIDExists(String psaId, String empId, String empType) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
		boolean isExits = false;
		if (psaId != null && !psaId.equalsIgnoreCase("")) {
			criteria.add(Restrictions.ilike("psaId", psaId));
		}
		if (empId != null && !empId.equalsIgnoreCase("")) {
			if (empType.equalsIgnoreCase("Internal")) {
				criteria.add(Restrictions.ne("empId", empId));
			} else {
				criteria.add(Restrictions.ne("psaId", empId));
			}

		}

		List<Employee> emplist = criteria.list();
		if (emplist != null && !emplist.isEmpty()) {
			isExits = true;
		}

		return isExits;
	}

	@Override
	public boolean checkEmployeeEmailExists(String email, String empId, String empType) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
		boolean isExits = false;

		if (email != null && !email.equalsIgnoreCase("")) {
			criteria.add(Restrictions.ilike("email", email));
		}
		if (empId != null && !empId.equalsIgnoreCase("")) {
			if (empType.equalsIgnoreCase("Internal")) {
				criteria.add(Restrictions.ne("empId", empId));
			} else {
				criteria.add(Restrictions.ne("psaId", empId));
			}

		}
		List<Employee> emplist = criteria.list();
		if (emplist != null && !emplist.isEmpty()) {
			isExits = true;
		}
		return isExits;
	}

	@Override
	public List<Employee> getAllEmployeesCountryWise() {
		Session session;
		try {
			session = this.sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		TypedQuery<Employee> query = session.createNamedQuery("listAllEmployeesByCountry");
		return query.getResultList();
	}

	@Override
	public List<Employee> getInternalEmployeesCountryWise() {
		Session session;
		try {
			session = this.sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		TypedQuery<Employee> query = session.createNamedQuery("listInternalEmpCountryWise");
		return query.getResultList();
	}

	@Override
	public List<Employee> getExternalEmployeesCountryWise() {
		Session session;
		try {
			session = this.sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		TypedQuery<Employee> query = session.createNamedQuery("listExternalEmpCountryWise");
		return query.getResultList();
	}

	public List<Employee> getDashBoardList() {
		Session session;
		try {
			session = this.sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		String queryString = OnBoardingSQLQueries.DashBoardGridForAllEmployee;
		TypedQuery<Employee> query = session.createSQLQuery(queryString);
		return query.getResultList();
	}

	@Override
	public List<Employee> getActiveEmployeesCountryWise() {

		Session session;
		try {
			session = this.sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		TypedQuery<Employee> query = session.createSQLQuery(OnBoardingSQLQueries.ListActiveEmpCountryWise);
		return query.getResultList();

	}

	@Override
	public List<Employee> getActiveInternalEmployeesCountryWise() {
		Session session;
		try {
			session = this.sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		TypedQuery<Employee> query = session.createSQLQuery(OnBoardingSQLQueries.ListActiveInternalEmpCountryWise);
		return query.getResultList();
	}

	@Override
	public List<Employee> getActiveExternalEmployeesCountryWise() {
		Session session;
		try {
			session = this.sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		TypedQuery<Employee> query = session.createSQLQuery(OnBoardingSQLQueries.ListActiveExternalEmpCountryWise);
		return query.getResultList();
	}

	@Override
	public List<Employee> getDashBoardActiveEmpList() {

		Session session;
		try {
			session = this.sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		String queryString = OnBoardingSQLQueries.DashBoardGridActiveEmployee;
		TypedQuery<Employee> query = session.createSQLQuery(queryString);
		return query.getResultList();

	}

	@Override
	public List<Employee> getBISActiveEmpList(int bisId){//mehens - BIS
		Session session;
		try {
			session = this.sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		String queryString;
		if(bisId == 0) {
		 queryString = OnBoardingSQLQueries.DashBoardBISActiveEmployee;
		}
		else {
		 queryString = "SELECT country_id , SUM(emp_type='Internal') , SUM(emp_type='External') ,COUNT(emp_type) from employee "
				+ "WHERE emp_id not in('NA') and bis_id = "
				+ " '" +bisId+"'"
				+ " and  ( (actual_end_date is not null AND actual_end_date >= CURDATE() ) "
				+ "OR ( actual_end_date is  null   )) GROUP BY country_id "
				+ "UNION "
				+ "SELECT '0', SUM(emp_type='Internal') , SUM(emp_type='External') ,COUNT(emp_type) from employee "
				+ "WHERE emp_id not in('NA') and bis_id= "
				+ " '" +bisId+"'"
				+ " and  ( (actual_end_date is not null AND actual_end_date >= CURDATE() ) "
				+ "OR ( actual_end_date is  null   ))";
		}
		
		TypedQuery<Employee> query = session.createSQLQuery(queryString);
		return query.getResultList();
		
		
		/*String queryString = "SELECT country_id, SUM(CASE WHEN emp_type = 'Internal' THEN 1 ELSE 0 END), "
   	            + "SUM(CASE WHEN emp_type = 'External' THEN 1 ELSE 0 END), COUNT(*) "
   	            + "FROM employee WHERE emp_id != 'NA' AND bis_id = :bisId AND "
   	            + "(actual_end_date IS NULL OR actual_end_date >= CURRENT_DATE) "
   	            + "GROUP BY country_id WITH ROLLUP"
   	            + "HAVING country_id IS NOT NULL OR internal_count IS NOT NULL OR external_count IS NOT NULL";
   		TypedQuery<Employee> query = session.createSQLQuery(queryString);
   	    query.setParameter("bisId", bisId);
   	    return query.getResultList();*/
		
		}
	
	
	@Override
	public List<Employee> searchOffboardReport(Employee emp) {
		Session session = this.sessionFactory.getCurrentSession();
		/*
		 * String role_value =
		 * SecurityContextHolder.getContext().getAuthentication().getAuthorities
		 * ().toString(); String loggedUser =
		 * SecurityContextHolder.getContext().getAuthentication().getName();
		 * TypedQuery<Employee> query1 =
		 * session.createNamedQuery("getLoggedUserID");
		 * query1.setParameter("corpID", loggedUser); Long loggedUserID =
		 * query1.getResultList().get(0).getId();
		 */

		Criteria criteria = session.createCriteria(Employee.class);

		Integer country = null;
		if (emp != null) {
			if (emp.getCountry() != null) {
				country = emp.getCountry().getCountryId();
			}
		}

		if (country != null && country > 0) {
			criteria.add(Restrictions.eq("country.countryId", country));
		}
		
		Calendar cal = Calendar.getInstance();
		Date currentdate = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 42);
		Date futureDate = cal.getTime();

		Criterion c1 = Restrictions.isNotNull("actualEndDate");
		Criterion c2 = Restrictions.between("actualEndDate", currentdate, futureDate);
		LogicalExpression conjActualEndDate = Restrictions.and(c1, c2);

		Criterion c3 = Restrictions.isNull("actualEndDate");
		Criterion c4 = Restrictions.isNotNull("endDate");
		Criterion c5 = Restrictions.between("endDate", currentdate, futureDate);
		LogicalExpression logiclConj = Restrictions.and(c3, Restrictions.and(c4, c5));
		criteria.add(Restrictions.or(conjActualEndDate, logiclConj));
		criteria.add(Restrictions.ne("id", new Long(0)));
		return criteria.list();
	}

	@Override
	public List<Employee> getPyramidChart(Integer countryId) {

		Employee emp = new Employee();
		emp.setEmpType("Internal");
		Country country = new Country();
		emp.setCountry(country);
		emp.getCountry().setCountryId(countryId);
		Session session = this.sessionFactory.getCurrentSession();
		String queryString = OnBoardingSQLQueries.PyramidChart;

		String condition = "";
		Integer countryID = null;
		if (emp.getCountry() != null) {
			countryID = emp.getCountry().getCountryId();
		}
		String empType = emp.getEmpType();
		if (countryID != null && countryID > 0 && empType != null && !empType.equalsIgnoreCase("")) {//mehens-new
			condition = condition
					//+ " where ( (actual_end_date is not null AND actual_end_date >= CURDATE() ) OR ( actual_end_date is  null  and end_date is not null AND end_date >= CURDATE() ))"
					+ " where ( (actual_end_date is not null AND actual_end_date >= CURDATE() ) OR ( actual_end_date is  null  ))"
					+ " and  global_grade_id is not null and ";
			if (countryID != null && countryID > 0) {
				condition = condition + "  country_id = " + countryID;
			}
			condition = condition + " and ";
			if (empType != null && !empType.equalsIgnoreCase("")) {
				condition = condition + "  emp_type = " + "'" + empType + "'";
			}
		} else if (countryID != null && countryID > 0 || empType != null && !empType.equalsIgnoreCase("")) {//mehens-new
			condition = condition
					//+ " where  ( (actual_end_date is not null AND actual_end_date >= CURDATE() ) OR ( actual_end_date is  null  and end_date is not null AND end_date >= CURDATE() )) "
					+ " where  ( (actual_end_date is not null AND actual_end_date >= CURDATE() ) OR ( actual_end_date is  null  )) "
					+ "and global_grade_id is not null and ";
			if (countryID != null && countryID > 0) {
				condition = "  country_id = " + countryID;
			}
			if (empType != null && !empType.equalsIgnoreCase("")) {
				condition = condition + "  emp_type = " + "'" + empType + "'";
			}
		} else {//mehens-new
			condition = condition
					//+ " where ( (actual_end_date is not null AND actual_end_date >= CURDATE() ) OR ( actual_end_date is  null  and end_date is not null AND end_date >= CURDATE() )) and "
					+ " where ( (actual_end_date is not null AND actual_end_date >= CURDATE() ) OR ( actual_end_date is  null )) and "
					+ "global_grade_id is not null  ";
		}

		if (countryID != null && countryID > 0) {
			condition = condition + " group by global_grade_id , country_id ";
		} else {
			condition = condition + " group by global_grade_id ";
		}

		// ( (actual_end_date is not null AND actual_end_date >= CURDATE() ) OR
		// ( actual_end_date is null and end_date is not null AND end_date >=
		// CURDATE() ))
		TypedQuery<Employee> query = session.createSQLQuery(queryString + condition+" order by global_grade_id");  
//		TypedQuery<Employee> query = session.createSQLQuery(queryString + condition);
		return query.getResultList();
	}

	@Override
	public List<Employee> listManagersByBundleEm() {
		List<Employee> listEmployees = new ArrayList<>();
		Session session = this.sessionFactory.getCurrentSession();
		/*
		 * TypedQuery<Employee> query =
		 * session.createNamedQuery("listManagers");
		 */

		String queryString = OnBoardingSQLQueries.ListOfManagersByBundleEm;
		TypedQuery<Employee> query = session.createSQLQuery(queryString);

		listEmployees = query.getResultList();
		List<Employee> managerList = new ArrayList<>();
		Iterator itr = listEmployees.iterator();
		while (itr.hasNext()) {
			Employee emp = new Employee();
			Object[] obj = (Object[]) itr.next();
			emp.setId(Long.parseLong(String.valueOf(obj[0])));
			if (String.valueOf(obj[1]) != null) {
				emp.setEmpId(String.valueOf(obj[1]));
			}
			if (String.valueOf(obj[2]) != null) {
				emp.setFirstName(String.valueOf(obj[2]));
			}
			if (String.valueOf(obj[3]) != null) {
				emp.setLastName(String.valueOf(obj[3]));
			}
			if (String.valueOf(obj[4]) != null) {
				emp.setCorpId(String.valueOf(obj[4]));
			}
			if (String.valueOf(obj[5]) != null) {
				emp.setEmail(String.valueOf(obj[5]));
			}
			managerList.add(emp);
		}
		Collections.sort(managerList);

		return managerList;

	}
	
	
	@Override
	public List<Employee> listBuddyEmp() {//mehens-buddy
		List<Employee> listEmployees = new ArrayList<>();
		Session session = this.sessionFactory.getCurrentSession();
		/*
		 * TypedQuery<Employee> query =
		 * session.createNamedQuery("listManagers");
		 */

		String queryString = OnBoardingSQLQueries.ListOfBuddyEmp;
		TypedQuery<Employee> query = session.createSQLQuery(queryString);

		listEmployees = query.getResultList();
		List<Employee> managerList = new ArrayList<>();
		Iterator itr = listEmployees.iterator();
		while (itr.hasNext()) {
			Employee emp = new Employee();
			Object[] obj = (Object[]) itr.next();
			emp.setId(Long.parseLong(String.valueOf(obj[0])));
			if (String.valueOf(obj[1]) != null) {
				emp.setEmpId(String.valueOf(obj[1]));
			}
			if (String.valueOf(obj[2]) != null) {
				emp.setFirstName(String.valueOf(obj[2]));
			}
			if (String.valueOf(obj[3]) != null) {
				emp.setLastName(String.valueOf(obj[3]));
			}
			if (String.valueOf(obj[4]) != null) {
				emp.setCorpId(String.valueOf(obj[4]));
			}
			if (String.valueOf(obj[5]) != null) {
				emp.setEmail(String.valueOf(obj[5]));
			}
			managerList.add(emp);
		}
		//Collections.sort(managerList);

		return managerList;

	}

	@Override
	public List<Employee> listManagersByEm(String ActiveOrNot) {
		List<Employee> listEmployees = new ArrayList<>();
		Session session = this.sessionFactory.getCurrentSession();
		/*
		 * TypedQuery<Employee> query =
		 * session.createNamedQuery("listManagers");
		 */

		String queryString = null;
		if(ActiveOrNot.length() == 0) {
			queryString = OnBoardingSQLQueries.ListOfManagersByEm;
		}else {
			queryString = OnBoardingSQLQueries.ListOfManagersByEmActive;
		}
		TypedQuery<Employee> query = session.createSQLQuery(queryString);

		listEmployees = query.getResultList();
		List<Employee> managerList = new ArrayList<>();
		Iterator itr = listEmployees.iterator();
		while (itr.hasNext()) {
			Employee emp = new Employee();
			Object[] obj = (Object[]) itr.next();
			emp.setId(Long.parseLong(String.valueOf(obj[0])));
			if (String.valueOf(obj[1]) != null) {
				emp.setEmpId(String.valueOf(obj[1]));
			}
			if (String.valueOf(obj[2]) != null) {
				emp.setFirstName(String.valueOf(obj[2]));
			}
			if (String.valueOf(obj[3]) != null) {
				emp.setLastName(String.valueOf(obj[3]));
			}
			if (String.valueOf(obj[4]) != null) {
				emp.setCorpId(String.valueOf(obj[4]));
			}
			if (String.valueOf(obj[5]) != null) {
				emp.setEmail(String.valueOf(obj[5]));
			}
			managerList.add(emp);
		}
		//Collections.sort(managerList);

		return managerList;
	}

	@Override
	public List<Employee> listEm(int pp) {
		List<Employee> listEmployees = new ArrayList<>();
		Session session = this.sessionFactory.getCurrentSession();

		String queryString = null;
		if(pp == 10) {
			queryString = OnBoardingSQLQueries.ListEmEngg;
		}else {
			queryString = OnBoardingSQLQueries.ListEmOthers;
		}
		TypedQuery<Employee> query = session.createSQLQuery(queryString);

		listEmployees = query.getResultList();
		List<Employee> managerList = new ArrayList<>();
		Iterator itr = listEmployees.iterator();
		while (itr.hasNext()) {
			Employee emp = new Employee();
			Object[] obj = (Object[]) itr.next();
			emp.setId(Long.parseLong(String.valueOf(obj[0])));
			if (String.valueOf(obj[1]) != null) {
				emp.setEmpId(String.valueOf(obj[1]));
			}
			if (String.valueOf(obj[2]) != null) {
				emp.setFirstName(String.valueOf(obj[2]));
			}
			if (String.valueOf(obj[3]) != null) {
				emp.setLastName(String.valueOf(obj[3]));
			}
			if (String.valueOf(obj[4]) != null) {
				emp.setCorpId(String.valueOf(obj[4]));
			}
			if (String.valueOf(obj[5]) != null) {
				emp.setEmail(String.valueOf(obj[5]));
			}
			managerList.add(emp);
		}
		//Collections.sort(managerList);

		return managerList;
	}
	
	/*@Override
	public List<Users> getAllEM() {
		List<Users> listOfEM = new ArrayList<>();
		Session session = this.sessionFactory.getCurrentSession();

		String queryString = OnBoardingSQLQueries.listOfEM;
		TypedQuery query = session.createSQLQuery(queryString);

		listOfEM = query.getResultList();
		List<Users> emList = new ArrayList<>();
		Iterator itr = listOfEM.iterator();
		for (int i = 0; i < listOfEM.size(); i++) {
			Users users = new Users();
			Object[] obj = (Object[]) itr.next();

			if (String.valueOf(obj[0]) != null) {
				users.setUserName(String.valueOf(obj[0]));
			}

			if (String.valueOf(obj[0]) != null) {
				users.setRole_id(String.valueOf(obj[3]));
			}

			emList.add(users);
		}
		return emList;
	}*/

	@Override
	public Employee getEmployeeByCorpId(String corpID) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("corpId", corpID));
		List<Employee> p = criteria.list();
		Employee emp = null;
		if (p != null && p.size()>0) {
			emp = p.get(0);
		}
		return emp;
	}
	
	//new method for logged user
	@Override
	public Employee getActiveEmployeeByCorpId(String corpID) {
	
		Session session = this.sessionFactory.getCurrentSession();
		TypedQuery<Employee> query = session.createNamedQuery("getLoggedUserID");
		query.setParameter("corpId", corpID);
		List<Employee> rs = query.getResultList();
		Employee emp = rs.get(0);
		
		logger.info("Logged Emp "+corpID+" and Emp Id= " + emp.getId());
		return emp;
	}

	@Override
	public List<Employee> getOffBoardingListForNext30Days(Employee emp) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);

		criteria.add(Restrictions.eq("EM.id", emp.getId()));

		Calendar cal = Calendar.getInstance();
		Date currentdate = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		Date futureDate = cal.getTime();

		Criterion c1 = Restrictions.isNotNull("actualEndDate");
		Criterion c2 = Restrictions.between("actualEndDate", currentdate, futureDate);
		LogicalExpression conjActualEndDate = Restrictions.and(c1, c2);

		Criterion c3 = Restrictions.isNull("actualEndDate");
		Criterion c4 = Restrictions.isNotNull("endDate");
		Criterion c5 = Restrictions.between("endDate", currentdate, futureDate);
		LogicalExpression logiclConj = Restrictions.and(c3, Restrictions.and(c4, c5));
		criteria.add(Restrictions.or(conjActualEndDate, logiclConj));
		return criteria.list();
	}

	@Override
	public List<Employee> listOffshoreEmIndia() {

		List<Employee> listEmployees = new ArrayList<>();
		Session session = this.sessionFactory.getCurrentSession();

		String queryString = OnBoardingSQLQueries.offShoreEMIndia;
		TypedQuery<Employee> query = session.createSQLQuery(queryString);

		listEmployees = query.getResultList();
		List<Employee> managerList = new ArrayList<>();
		Iterator itr = listEmployees.iterator();
		while (itr.hasNext()) {
			Employee emp = new Employee();
			Object[] obj = (Object[]) itr.next();
			emp.setId(Long.parseLong(String.valueOf(obj[0])));
			if (String.valueOf(obj[1]) != null) {
				emp.setEmpId(String.valueOf(obj[1]));
			}
			if (String.valueOf(obj[2]) != null) {
				emp.setFirstName(String.valueOf(obj[2]));
			}
			if (String.valueOf(obj[3]) != null) {
				emp.setLastName(String.valueOf(obj[3]));
			}
			if (String.valueOf(obj[4]) != null) {
				emp.setCorpId(String.valueOf(obj[4]));
			}
			if (String.valueOf(obj[5]) != null) {
				emp.setEmail(String.valueOf(obj[5]));
			}
			managerList.add(emp);
		}
		Collections.sort(managerList);

		return managerList;
	}

	@Override
	public List<Float> headCountList(LocalDate current_month_startDate_local, LocalDate current_Month_Enddate__local,
			int country_Id) {

		List<Float> resultList = new ArrayList<Float>();
		DecimalFormat df = new DecimalFormat("#.##");
		for (int i = 0; i < 6; i++) {
			float currentResult = new Float(0);
			List<Float> totalFTECount = new ArrayList<>();
			List<Employee> empList = getFTEList(current_month_startDate_local.plusMonths(i),
					current_Month_Enddate__local.plusMonths(i), country_Id);

			for (Employee e : empList) {
				currentResult = currentResult + e.getMonthlyFTE();
			}
			currentResult = Float.parseFloat(df.format(currentResult));
			totalFTECount.add(currentResult);
			resultList.addAll(totalFTECount);
		}

		/*
		 * List resultlist = new ArrayList<>(); for(int j=0;j<=6;j++){
		 * 
		 * 
		 * 
		 * }
		 */

		/*
		 * List<Long> result = new ArrayList<Long>(); for(int i=0 ; i<6 ; i++){
		 * 
		 * 
		 * Session session = this.sessionFactory.getCurrentSession(); Criteria
		 * criteria = session.createCriteria(Employee.class); Date
		 * current_month_startDate =
		 * Date.from(current_month_startDate_local.plusMonths(i).atStartOfDay(
		 * ZoneId.systemDefault()).toInstant()); Date current_Month_Enddate =
		 * Date.from(current_Month_Enddate__local.plusMonths(i).atStartOfDay(
		 * ZoneId.systemDefault()).toInstant());
		 * 
		 * 
		 * 
		 * Criterion startDateCriteria = Restrictions.le("joiningDate",
		 * current_Month_Enddate) ; criteria.add(startDateCriteria);
		 * 
		 * Criterion c1 = Restrictions.isNotNull("actualEndDate"); Criterion c2
		 * = Restrictions.ge("actualEndDate", current_month_startDate);
		 * LogicalExpression conjActualEndDate = Restrictions.and(c1, c2);
		 * 
		 * Criterion c3 = Restrictions.isNull("actualEndDate"); Criterion c4 =
		 * Restrictions.isNotNull("endDate"); Criterion c5 =
		 * Restrictions.ge("endDate", current_month_startDate);
		 * LogicalExpression logiclConj = Restrictions.and(c3,
		 * Restrictions.and(c4, c5));
		 * criteria.add(Restrictions.or(conjActualEndDate, logiclConj));
		 * 
		 * criteria.add(Restrictions.like("country.countryId", country_Id));
		 * criteria.add(Restrictions.ne("empId", "NA"));
		 * 
		 * criteria.setProjection(Projections.projectionList().add(Projections.
		 * rowCount())); criteria.addOrder(Order.asc("country")); List<Long>
		 * currentResult2 = criteria.list(); result.addAll(currentResult2); }
		 */

		return resultList;
	}

	@Override
	public List<Employee> getFTEList(LocalDate current_month_startDate, LocalDate current_Month_Enddate,
			int country_Id) {
		List result = new ArrayList<>();
		Session session = this.sessionFactory.getCurrentSession();

		String queryString = "select " + OnBoardingSQLQueries.selectColumn + " ,(( LEAST('" + current_Month_Enddate
				+ "' , IF( `actual_end_date` is not null , actual_end_date , `end_date` ) ) -  GREATEST('"
				+ current_month_startDate + "' , joining_date) ) + 1 ) /  (DATEDIFF('" + current_Month_Enddate + "' , '"
				+ current_month_startDate + "')+1) as effort " + "from EMPLOYEE this_ where this_.joining_date<= '"
				+ current_Month_Enddate + "' and ((this_.actual_end_date is not null and this_.actual_end_date>='"
				+ current_month_startDate + "') or "
				+ "(this_.actual_end_date is null and (this_.end_date is not null and this_.end_date>= '"
				+ current_month_startDate + "'))) ";

		if (country_Id != 0) {
			queryString = queryString + "and country_id =  " + country_Id;
		}

		TypedQuery<Employee> query = session.createSQLQuery(queryString);

		result = query.getResultList();

		List<Employee> empList = new ArrayList<>();

		Iterator itr = result.iterator();
		while (itr.hasNext()) {

			Employee emp = new Employee();
			DecimalFormat df = new DecimalFormat("#.##");
			Object[] obj = (Object[]) itr.next();
			if (!String.valueOf(obj[1]).equalsIgnoreCase("NA")) {
				emp.setId(Long.parseLong(String.valueOf(obj[0])));
				if (String.valueOf(obj[1]) != null && !String.valueOf(obj[1]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[1]).equalsIgnoreCase("")) {
					emp.setEmpId(String.valueOf(obj[1]));
				}
				if (String.valueOf(obj[2]) != null && !String.valueOf(obj[2]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[2]).equalsIgnoreCase("")) {
					emp.setCorpId(String.valueOf(obj[2]));
				}
				if (String.valueOf(obj[3]) != null && !String.valueOf(obj[3]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[3]).equalsIgnoreCase("")) {
					emp.setLastName(String.valueOf(obj[3]));
				}
				if (String.valueOf(obj[4]) != null && !String.valueOf(obj[4]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[4]).equalsIgnoreCase("")) {
					emp.setFirstName(String.valueOf(obj[4]));
				}
				if (String.valueOf(obj[5]) != null && !String.valueOf(obj[5]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[5]).equalsIgnoreCase("")) {
					emp.setEmail(String.valueOf(obj[5]));
				}
				if (String.valueOf(obj[6]) != null && !String.valueOf(obj[6]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[6]).equalsIgnoreCase("")) {
					Grade grade = new Grade();
					grade = gradeService.getGradeById(Integer.parseInt(String.valueOf(obj[6])));
					emp.setGrade(grade);
				}
				if (String.valueOf(obj[7]) != null && !String.valueOf(obj[7]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[7]).equalsIgnoreCase("")) {
					Country country = new Country();
					country = countryService.getCountryById(Integer.parseInt(String.valueOf(obj[7])));
					emp.setCountry(country);
				}
				if (String.valueOf(obj[8]) != null && !String.valueOf(obj[8]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[8]).equalsIgnoreCase("")) {
					State state = new State();
					state = stateService.getStateById(Integer.parseInt(String.valueOf(obj[8])));
					emp.setLocation(state);
				}

				if (String.valueOf(obj[9]) != null && !String.valueOf(obj[9]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[9]).equalsIgnoreCase("")) {
					Technology primarySkill = new Technology();
					primarySkill = technologyService.getTechnologyById(Integer.parseInt(String.valueOf(obj[9])));
					emp.setPrimaryTechnology(primarySkill);
				}

				if (String.valueOf(obj[10]) != null && !String.valueOf(obj[10]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[10]).equalsIgnoreCase("")) {
					emp.setOnboardingDate(String.valueOf(obj[10]));
				}

				if (String.valueOf(obj[11]) != null && !String.valueOf(obj[11]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[11]).equalsIgnoreCase("")) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String endDateStringvalue = String.valueOf(obj[11]);
					Date endDate = null;
					try {
						endDate = dateFormat.parse(endDateStringvalue);
					} catch (ParseException e) {
						logger.error(" Some Problem occurs :- "+e.getMessage());
					}
					emp.setOffboardingDate(String.valueOf(obj[11]));
				}

				if (String.valueOf(obj[15]) != null && !String.valueOf(obj[15]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[15]).equalsIgnoreCase("")) {
					Employee mgr = new Employee();
					mgr = employeeService.getEmployeeById(Long.parseLong(String.valueOf(obj[15])));
					emp.setManager(mgr);
					
					
					
					
				}

				if (String.valueOf(obj[19]) != null && !String.valueOf(obj[19]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[19]).equalsIgnoreCase("")) {
					GlobalGrade globalGrade = new GlobalGrade();
					globalGrade = globalGradeService.getGlobalGradeById(Integer.parseInt(String.valueOf(obj[19])));
					emp.setGlobalGrade(globalGrade);
				}

				if (String.valueOf(obj[21]) != null && !String.valueOf(obj[21]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[21]).equalsIgnoreCase("")) {
					emp.setEmpType(String.valueOf(obj[21]));
				}

				if (String.valueOf(obj[22]) != null && !String.valueOf(obj[22]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[22]).equalsIgnoreCase("")) {
					emp.setActualOffboardingDate(String.valueOf(obj[22]));

				}

				if (String.valueOf(obj[25]) != null && !String.valueOf(obj[25]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[25]).equalsIgnoreCase("")) {
					emp.setPsaId(String.valueOf(obj[25]));
				}

				if (String.valueOf(obj[29]) != null && !String.valueOf(obj[29]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[29]).equalsIgnoreCase("")) {
					/*CGEntityDetail cgEntity = new CGEntityDetail();
					cgEntity = cgEntityService.getCgEntityById(Integer.parseInt(String.valueOf(obj[29])));
					emp.setCgEntityDetail(cgEntity);*/
					emp.setCgEntity(String.valueOf(obj[29]));
					
				}

				if (String.valueOf(obj[32]) != null && !String.valueOf(obj[32]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[32]).equalsIgnoreCase("")) {
					Employee Em = new Employee();
					Em = employeeService.getEmployeeById(Long.parseLong(String.valueOf(obj[32])));
					emp.setEM(Em);
				}

				if (String.valueOf(obj[33]) != null && !String.valueOf(obj[33]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[33]).equalsIgnoreCase("")) {
					BundleEm bundleEm = new BundleEm();
					bundleEm = bundleEmService.getBundleEmById(Integer.parseInt(String.valueOf(obj[33])));
					emp.setBundleEM(bundleEm);

				}

				if (String.valueOf(obj[34]) != null && !String.valueOf(obj[34]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[34]).equalsIgnoreCase("")) {

					OffshoreEm offshoreEm = new OffshoreEm();
					offshoreEm = offShoreEmService.getOffshoreById(Integer.parseInt(String.valueOf(obj[34])));

					emp.setOffshoreEm(offshoreEm);

				}

				if (String.valueOf(obj[37]) != null && !String.valueOf(obj[37]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[37]).equalsIgnoreCase("null")) {
					Bis bis = new Bis();
					bis = bisService.getBisById(Integer.parseInt(String.valueOf(obj[37])));
					emp.setBis(bis);

				}
				if (String.valueOf(obj[39]) != null && !String.valueOf(obj[39]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[39]).equalsIgnoreCase("")) {
					emp.setMonthlyFTE(Float.valueOf(df.format(obj[39])));
				}
				
				if (String.valueOf(obj[15]) != null && !String.valueOf(obj[15]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[15]).equalsIgnoreCase("")) {
					Employee buddy = new Employee();
					buddy = employeeService.getEmployeeById(Long.parseLong(String.valueOf(obj[15])));
					emp.setBuddy(buddy);
					
					
					
					
				}

				empList.add(emp);
			}

		}
		return empList;
	}

	public List<Employee> getFTEListCountryWise(LocalDate current_month_startDate, LocalDate current_Month_Enddate,
			int country_Id) {
		List result = new ArrayList<>();
		DecimalFormat df = new DecimalFormat("#.##");
		Session session = this.sessionFactory.getCurrentSession();

		LocalDate first_month_startDate = current_month_startDate;
		LocalDate first_month_endDate = current_Month_Enddate;

		LocalDate second_month_startDate = current_month_startDate.plusMonths(1);
		LocalDate second_month_endDate = second_month_startDate.with(lastDayOfMonth());

		LocalDate third_month_startDate = current_month_startDate.plusMonths(2);
		LocalDate third_month_endDate = third_month_startDate.with(lastDayOfMonth());

		LocalDate fourth_month_startDate = current_month_startDate.plusMonths(3);
		LocalDate fourth_month_endDate = fourth_month_startDate.with(lastDayOfMonth());

		LocalDate fifth_month_startDate = current_month_startDate.plusMonths(4);
		LocalDate fifth_month_endDate = fifth_month_startDate.with(lastDayOfMonth());

		LocalDate sixth_month_startDate = current_month_startDate.plusMonths(5);
		LocalDate sixth_month_endDate = sixth_month_startDate.with(lastDayOfMonth());

		String firstFTEQuery = "select " + OnBoardingSQLQueries.selectColumn + " ,(select  ((( LEAST('"
				+ first_month_endDate
				+ "' , IF( `actual_end_date` is not null , actual_end_date , `end_date` ) ) -  GREATEST('"
				+ first_month_startDate + "' , joining_date) ) + 1 ) /  (DATEDIFF('" + first_month_endDate + "' , '"
				+ first_month_startDate + "')+1) )as effort "
				+ "from EMPLOYEE emp1 where emp1.id = e.id and  emp1.joining_date<= '" + first_month_endDate
				+ "' and ((emp1.actual_end_date is not null and emp1.actual_end_date>='" + first_month_startDate
				+ "') or " + "(emp1.actual_end_date is null and (emp1.end_date is not null and emp1.end_date>= '"
				+ first_month_startDate + "')))  and country_id = " + country_Id + " )";

		String secondFTEQuery = " (select  ((( LEAST('" + second_month_endDate
				+ "' , IF( `actual_end_date` is not null , actual_end_date , `end_date` ) ) -  GREATEST('"
				+ second_month_startDate + "' , joining_date) ) + 1 ) /  (DATEDIFF('" + second_month_endDate + "' , '"
				+ second_month_startDate + "')+1) ) as effort"
				+ " from EMPLOYEE emp1 where emp1.id = e.id and emp1.joining_date<= '" + second_month_endDate
				+ "' and ((emp1.actual_end_date is not null and emp1.actual_end_date>='" + second_month_startDate
				+ "') or " + "(emp1.actual_end_date is null and (emp1.end_date is not null and emp1.end_date>= '"
				+ second_month_startDate + "'))) and country_id = " + country_Id + " )";

		String thirdFTEQuery = " (select  ((( LEAST('" + third_month_endDate
				+ "' , IF( `actual_end_date` is not null , actual_end_date , `end_date` ) ) -  GREATEST('"
				+ third_month_startDate + "' , joining_date) ) + 1 ) /  (DATEDIFF('" + third_month_endDate + "' , '"
				+ third_month_startDate + "')+1) ) as effort"
				+ " from EMPLOYEE emp1 where emp1.id = e.id and emp1.joining_date<= '" + third_month_endDate
				+ "' and ((emp1.actual_end_date is not null and emp1.actual_end_date>='" + third_month_startDate
				+ "') or " + "(emp1.actual_end_date is null and (emp1.end_date is not null and emp1.end_date>= '"
				+ third_month_startDate + "'))) and country_id = " + country_Id + " )";

		// after this need to chnage the date
		String fourthFTEQuery = " (select  ((( LEAST('" + fourth_month_endDate
				+ "' , IF( `actual_end_date` is not null , actual_end_date , `end_date` ) ) -  GREATEST('"
				+ fourth_month_startDate + "' , joining_date) ) + 1 ) /  (DATEDIFF('" + fourth_month_endDate + "' , '"
				+ fourth_month_startDate + "')+1) ) as effort"
				+ " from EMPLOYEE emp1 where emp1.id = e.id and emp1.joining_date<= '" + fourth_month_endDate
				+ "' and ((emp1.actual_end_date is not null and emp1.actual_end_date>='" + fourth_month_startDate
				+ "') or " + "(emp1.actual_end_date is null and (emp1.end_date is not null and emp1.end_date>= '"
				+ fourth_month_startDate + "'))) and country_id = " + country_Id + " )";

		String fifthFTEQuery = " (select  ((( LEAST('" + fifth_month_endDate
				+ "' , IF( `actual_end_date` is not null , actual_end_date , `end_date` ) ) -  GREATEST('"
				+ fifth_month_startDate + "' , joining_date) ) + 1 ) /  (DATEDIFF('" + fifth_month_endDate + "' , '"
				+ fifth_month_startDate + "')+1) ) as effort"
				+ " from EMPLOYEE emp1 where emp1.id = e.id and emp1.joining_date<= '" + fifth_month_endDate
				+ "' and ((emp1.actual_end_date is not null and emp1.actual_end_date>='" + fifth_month_startDate
				+ "') or " + "(emp1.actual_end_date is null and (emp1.end_date is not null and emp1.end_date>= '"
				+ fifth_month_startDate + "'))) and country_id = " + country_Id + " )";

		String sixthFTEQuery = " (select  ((( LEAST('" + sixth_month_endDate
				+ "' , IF( `actual_end_date` is not null , actual_end_date , `end_date` ) ) -  GREATEST('"
				+ sixth_month_startDate + "' , joining_date) ) + 1 ) /  (DATEDIFF('" + sixth_month_endDate + "' , '"
				+ sixth_month_startDate + "')+1) ) as effort"
				+ " from EMPLOYEE emp1 where emp1.id = e.id and emp1.joining_date<= '" + sixth_month_endDate
				+ "' and ((emp1.actual_end_date is not null and emp1.actual_end_date>='" + sixth_month_startDate
				+ "') or " + "(emp1.actual_end_date is null and (emp1.end_date is not null and emp1.end_date>= '"
				+ sixth_month_startDate + "'))) and country_id = " + country_Id + " )";

		String queryMainTable = "from EMPLOYEE e where e.joining_date<= '" + current_Month_Enddate
				+ "' and ((e.actual_end_date is not null and e.actual_end_date>='" + current_month_startDate + "') or "
				+ "(e.actual_end_date is null and (e.end_date is not null and e.end_date>= '" + current_month_startDate
				+ "'))) and e.country_id =  " + country_Id;

		String queryString = firstFTEQuery + " , " + secondFTEQuery + " , " + thirdFTEQuery + " , " + fourthFTEQuery
				+ " , " + fifthFTEQuery + "," + sixthFTEQuery + " " + queryMainTable;
		TypedQuery<Employee> query = session.createSQLQuery(queryString);
		result = query.getResultList();
		List<Employee> empList = new ArrayList<>();
		Iterator itr = result.iterator();

		while (itr.hasNext()) {

			Employee emp = new Employee();
			Object[] obj = (Object[]) itr.next();
			if (!String.valueOf(obj[1]).equalsIgnoreCase("NA")) {
				emp.setId(Long.parseLong(String.valueOf(obj[0])));
				if (String.valueOf(obj[1]) != null && !String.valueOf(obj[1]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[1]).equalsIgnoreCase("")) {
					emp.setEmpId(String.valueOf(obj[1]));
				}
				if (String.valueOf(obj[2]) != null && !String.valueOf(obj[2]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[2]).equalsIgnoreCase("")) {
					emp.setCorpId(String.valueOf(obj[2]));
				}
				if (String.valueOf(obj[3]) != null && !String.valueOf(obj[3]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[3]).equalsIgnoreCase("")) {
					emp.setLastName(String.valueOf(obj[3]));
				}
				if (String.valueOf(obj[4]) != null && !String.valueOf(obj[4]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[4]).equalsIgnoreCase("")) {
					emp.setFirstName(String.valueOf(obj[4]));
				}
				if (String.valueOf(obj[5]) != null && !String.valueOf(obj[5]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[5]).equalsIgnoreCase("")) {
					emp.setEmail(String.valueOf(obj[5]));
				}
				if (String.valueOf(obj[6]) != null && !String.valueOf(obj[6]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[6]).equalsIgnoreCase("")) {
					Grade grade = new Grade();
					grade = gradeService.getGradeById(Integer.parseInt(String.valueOf(obj[6])));
					emp.setGrade(grade);
				}
				if (String.valueOf(obj[7]) != null && !String.valueOf(obj[7]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[7]).equalsIgnoreCase("")) {
					Country country = new Country();
					country = countryService.getCountryById(Integer.parseInt(String.valueOf(obj[7])));
					emp.setCountry(country);
				}
				if (String.valueOf(obj[8]) != null && !String.valueOf(obj[8]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[8]).equalsIgnoreCase("")) {
					State state = new State();
					state = stateService.getStateById(Integer.parseInt(String.valueOf(obj[8])));
					emp.setLocation(state);
				}

				if (String.valueOf(obj[9]) != null && !String.valueOf(obj[9]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[9]).equalsIgnoreCase("")) {
					Technology primarySkill = new Technology();
					primarySkill = technologyService.getTechnologyById(Integer.parseInt(String.valueOf(obj[9])));
					emp.setPrimaryTechnology(primarySkill);
				}

				if (String.valueOf(obj[10]) != null && !String.valueOf(obj[10]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[10]).equalsIgnoreCase("")) {
					emp.setOnboardingDate(String.valueOf(obj[10]));
				}

				if (String.valueOf(obj[11]) != null && !String.valueOf(obj[11]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[11]).equalsIgnoreCase("")) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String endDateStringvalue = String.valueOf(obj[11]);
					Date endDate = null;
					try {
						endDate = dateFormat.parse(endDateStringvalue);
					} catch (ParseException e) {
						logger.error(" Some Problem occurs :- "+e.getMessage());
					}
					emp.setOffboardingDate(String.valueOf(obj[11]));
				}

				if (String.valueOf(obj[15]) != null && !String.valueOf(obj[15]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[15]).equalsIgnoreCase("")) {
					Employee mgr = new Employee();
					mgr = employeeService.getEmployeeById(Long.parseLong(String.valueOf(obj[15])));
					emp.setManager(mgr);
				}

				if (String.valueOf(obj[19]) != null && !String.valueOf(obj[19]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[19]).equalsIgnoreCase("")) {
					GlobalGrade globalGrade = new GlobalGrade();
					globalGrade = globalGradeService.getGlobalGradeById(Integer.parseInt(String.valueOf(obj[19])));
					emp.setGlobalGrade(globalGrade);
				}

				if (String.valueOf(obj[21]) != null && !String.valueOf(obj[21]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[21]).equalsIgnoreCase("")) {
					emp.setEmpType(String.valueOf(obj[21]));
				}

				if (String.valueOf(obj[22]) != null && !String.valueOf(obj[22]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[22]).equalsIgnoreCase("")) {
					emp.setActualOffboardingDate(String.valueOf(obj[22]));

				}

				if (String.valueOf(obj[25]) != null && !String.valueOf(obj[25]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[25]).equalsIgnoreCase("")) {
					emp.setPsaId(String.valueOf(obj[25]));
				}

				if (String.valueOf(obj[29]) != null && !String.valueOf(obj[29]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[29]).equalsIgnoreCase("")) {
					/*CGEntityDetail cgEntity = new CGEntityDetail();
					cgEntity = cgEntityService.getCgEntityById(Integer.parseInt(String.valueOf(obj[29])));
					emp.setCgEntityDetail(cgEntity);*/
					emp.setCgEntity(String.valueOf(obj[29]));
				}

				if (String.valueOf(obj[32]) != null && !String.valueOf(obj[32]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[32]).equalsIgnoreCase("")) {
					Employee Em = new Employee();
					Em = employeeService.getEmployeeById(Long.parseLong(String.valueOf(obj[32])));
					emp.setEM(Em);
				}

				if (String.valueOf(obj[33]) != null && !String.valueOf(obj[33]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[33]).equalsIgnoreCase("")) {
					BundleEm bundleEm = new BundleEm();
					bundleEm = bundleEmService.getBundleEmById(Integer.parseInt(String.valueOf(obj[33])));
					emp.setBundleEM(bundleEm);
				}

				if (String.valueOf(obj[34]) != null && !String.valueOf(obj[34]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[34]).equalsIgnoreCase("")) {
					OffshoreEm offShoreEM = new OffshoreEm();

					offShoreEM = offShoreEmService.getOffshoreById(Integer.parseInt(String.valueOf(obj[34])));

					emp.setOffshoreEm(offShoreEM);

				}

				if (String.valueOf(obj[37]) != null && !String.valueOf(obj[37]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[37]).equalsIgnoreCase("null")) {
					Bis bis = new Bis();
					bis = bisService.getBisById(Integer.parseInt(String.valueOf(obj[37])));
					emp.setBis(bis);

				}

				if (String.valueOf(obj[39]) != null && !String.valueOf(obj[39]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[39]).equalsIgnoreCase("")) {
					emp.setMonthlyFTE(Float.valueOf(df.format(obj[39])));
				}

				if (String.valueOf(obj[40]) != null && !String.valueOf(obj[40]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[40]).equalsIgnoreCase("")) {
					emp.setSecondFTE(Float.valueOf(df.format(obj[40])));
				}

				if (String.valueOf(obj[41]) != null && !String.valueOf(obj[41]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[41]).equalsIgnoreCase("")) {
					emp.setThirdFTE(Float.valueOf(df.format(obj[41])));
				}

				if (String.valueOf(obj[42]) != null && !String.valueOf(obj[42]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[42]).equalsIgnoreCase("")) {
					emp.setFourthFTE(Float.valueOf(df.format(obj[42])));
				}

				if (String.valueOf(obj[43]) != null && !String.valueOf(obj[43]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[43]).equalsIgnoreCase("")) {
					emp.setFifthFTE(Float.valueOf(df.format(obj[43])));
				}

				if (String.valueOf(obj[44]) != null && !String.valueOf(obj[44]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[44]).equalsIgnoreCase("")) {
					emp.setSixthFTE(Float.valueOf(df.format(obj[44])));
				}
				if (String.valueOf(obj[15]) != null && !String.valueOf(obj[15]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[15]).equalsIgnoreCase("")) {
					Employee buddy = new Employee();
					buddy = employeeService.getEmployeeById(Long.parseLong(String.valueOf(obj[15])));
					emp.setBuddy(buddy);
				}

				empList.add(emp);
			}

		}

		return empList;
	}

	@Override
	public List<Employee> getFTEListTotal(LocalDate current_month_startDate, LocalDate current_Month_Enddate,
			int country_Id) {
		List result = new ArrayList<>();
		Session session = this.sessionFactory.getCurrentSession();

		LocalDate first_month_startDate = current_month_startDate;
		LocalDate first_month_endDate = current_Month_Enddate;

		LocalDate second_month_startDate = current_month_startDate.plusMonths(1);
		LocalDate second_month_endDate = second_month_startDate.with(lastDayOfMonth());

		LocalDate third_month_startDate = current_month_startDate.plusMonths(2);
		LocalDate third_month_endDate = third_month_startDate.with(lastDayOfMonth());

		LocalDate fourth_month_startDate = current_month_startDate.plusMonths(3);
		LocalDate fourth_month_endDate = fourth_month_startDate.with(lastDayOfMonth());

		LocalDate fifth_month_startDate = current_month_startDate.plusMonths(4);
		LocalDate fifth_month_endDate = fifth_month_startDate.with(lastDayOfMonth());

		LocalDate sixth_month_startDate = current_month_startDate.plusMonths(5);
		LocalDate sixth_month_endDate = sixth_month_startDate.with(lastDayOfMonth());

		String firstFTEQuery = "select " + OnBoardingSQLQueries.selectColumn + " ,(select  ((( LEAST('"
				+ first_month_endDate
				+ "' , IF( `actual_end_date` is not null , actual_end_date , `end_date` ) ) -  GREATEST('"
				+ first_month_startDate + "' , joining_date) ) + 1 ) /  (DATEDIFF('" + first_month_endDate + "' , '"
				+ first_month_startDate + "')+1) )as effort "
				+ "from EMPLOYEE emp1 where emp1.id = e.id and  emp1.joining_date<= '" + first_month_endDate
				+ "' and ((emp1.actual_end_date is not null and emp1.actual_end_date>='" + first_month_startDate
				+ "') or " + "(emp1.actual_end_date is null and (emp1.end_date is not null and emp1.end_date>= '"
				+ first_month_startDate + "'))))";

		String secondFTEQuery = " (select  ((( LEAST('" + second_month_endDate
				+ "' , IF( `actual_end_date` is not null , actual_end_date , `end_date` ) ) -  GREATEST('"
				+ second_month_startDate + "' , joining_date) ) + 1 ) /  (DATEDIFF('" + second_month_endDate + "' , '"
				+ second_month_startDate + "')+1) ) as effort"
				+ " from EMPLOYEE emp1 where emp1.id = e.id and emp1.joining_date<= '" + second_month_endDate
				+ "' and ((emp1.actual_end_date is not null and emp1.actual_end_date>='" + second_month_startDate
				+ "') or " + "(emp1.actual_end_date is null and (emp1.end_date is not null and emp1.end_date>= '"
				+ second_month_startDate + "'))))";

		String thirdFTEQuery = " (select  ((( LEAST('" + third_month_endDate
				+ "' , IF( `actual_end_date` is not null , actual_end_date , `end_date` ) ) -  GREATEST('"
				+ third_month_startDate + "' , joining_date) ) + 1 ) /  (DATEDIFF('" + third_month_endDate + "' , '"
				+ third_month_startDate + "')+1) ) as effort"
				+ " from EMPLOYEE emp1 where emp1.id = e.id and emp1.joining_date<= '" + third_month_endDate
				+ "' and ((emp1.actual_end_date is not null and emp1.actual_end_date>='" + third_month_startDate
				+ "') or " + "(emp1.actual_end_date is null and (emp1.end_date is not null and emp1.end_date>= '"
				+ third_month_startDate + "'))))";

		// after this need to chnage the date
		String fourthFTEQuery = " (select  ((( LEAST('" + fourth_month_endDate
				+ "' , IF( `actual_end_date` is not null , actual_end_date , `end_date` ) ) -  GREATEST('"
				+ fourth_month_startDate + "' , joining_date) ) + 1 ) /  (DATEDIFF('" + fourth_month_endDate + "' , '"
				+ fourth_month_startDate + "')+1) ) as effort"
				+ " from EMPLOYEE emp1 where emp1.id = e.id and emp1.joining_date<= '" + fourth_month_endDate
				+ "' and ((emp1.actual_end_date is not null and emp1.actual_end_date>='" + fourth_month_startDate
				+ "') or " + "(emp1.actual_end_date is null and (emp1.end_date is not null and emp1.end_date>= '"
				+ fourth_month_startDate + "'))) )";

		String fifthFTEQuery = " (select  ((( LEAST('" + fifth_month_endDate
				+ "' , IF( `actual_end_date` is not null , actual_end_date , `end_date` ) ) -  GREATEST('"
				+ fifth_month_startDate + "' , joining_date) ) + 1 ) /  (DATEDIFF('" + fifth_month_endDate + "' , '"
				+ fifth_month_startDate + "')+1) ) as effort"
				+ " from EMPLOYEE emp1 where emp1.id = e.id and emp1.joining_date<= '" + fifth_month_endDate
				+ "' and ((emp1.actual_end_date is not null and emp1.actual_end_date>='" + fifth_month_startDate
				+ "') or " + "(emp1.actual_end_date is null and (emp1.end_date is not null and emp1.end_date>= '"
				+ fifth_month_startDate + "'))))";

		String sixthFTEQuery = " (select  ((( LEAST('" + sixth_month_endDate
				+ "' , IF( `actual_end_date` is not null , actual_end_date , `end_date` ) ) -  GREATEST('"
				+ sixth_month_startDate + "' , joining_date) ) + 1 ) /  (DATEDIFF('" + sixth_month_endDate + "' , '"
				+ sixth_month_startDate + "')+1) ) as effort"
				+ " from EMPLOYEE emp1 where emp1.id = e.id and emp1.joining_date<= '" + sixth_month_endDate
				+ "' and ((emp1.actual_end_date is not null and emp1.actual_end_date>='" + sixth_month_startDate
				+ "') or " + "(emp1.actual_end_date is null and (emp1.end_date is not null and emp1.end_date>= '"
				+ sixth_month_startDate + "'))) )";

		String queryMainTable = "from EMPLOYEE e where e.joining_date<= '" + current_Month_Enddate
				+ "' and ((e.actual_end_date is not null and e.actual_end_date>='" + current_month_startDate + "') or "
				+ "(e.actual_end_date is null and (e.end_date is not null and e.end_date>= '" + current_month_startDate
				+ "'))) ";

		String queryString = firstFTEQuery + " , " + secondFTEQuery + " , " + thirdFTEQuery + " , " + fourthFTEQuery
				+ " , " + fifthFTEQuery + "," + sixthFTEQuery + " " + queryMainTable;
		TypedQuery<Employee> query = session.createSQLQuery(queryString);
		result = query.getResultList();
		List<Employee> empList = new ArrayList<>();
		Iterator itr = result.iterator();

		while (itr.hasNext()) {

			Employee emp = new Employee();
			DecimalFormat df = new DecimalFormat("#.##");

			Object[] obj = (Object[]) itr.next();
			if (!String.valueOf(obj[1]).equalsIgnoreCase("NA")) {
				emp.setId(Long.parseLong(String.valueOf(obj[0])));
				if (String.valueOf(obj[1]) != null && !String.valueOf(obj[1]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[1]).equalsIgnoreCase("")) {
					emp.setEmpId(String.valueOf(obj[1]));
				}
				if (String.valueOf(obj[2]) != null && !String.valueOf(obj[2]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[2]).equalsIgnoreCase("")) {
					emp.setCorpId(String.valueOf(obj[2]));
				}
				if (String.valueOf(obj[3]) != null && !String.valueOf(obj[3]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[3]).equalsIgnoreCase("")) {
					emp.setLastName(String.valueOf(obj[3]));
				}
				if (String.valueOf(obj[4]) != null && !String.valueOf(obj[4]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[4]).equalsIgnoreCase("")) {
					emp.setFirstName(String.valueOf(obj[4]));
				}
				if (String.valueOf(obj[5]) != null && !String.valueOf(obj[5]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[5]).equalsIgnoreCase("")) {
					emp.setEmail(String.valueOf(obj[5]));
				}
				if (String.valueOf(obj[6]) != null && !String.valueOf(obj[6]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[6]).equalsIgnoreCase("")) {
					Grade grade = new Grade();
					grade = gradeService.getGradeById(Integer.parseInt(String.valueOf(obj[6])));
					emp.setGrade(grade);
				}
				if (String.valueOf(obj[7]) != null && !String.valueOf(obj[7]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[7]).equalsIgnoreCase("")) {
					Country country = new Country();
					country = countryService.getCountryById(Integer.parseInt(String.valueOf(obj[7])));
					emp.setCountry(country);
				}
				if (String.valueOf(obj[8]) != null && !String.valueOf(obj[8]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[8]).equalsIgnoreCase("")) {
					State state = new State();
					state = stateService.getStateById(Integer.parseInt(String.valueOf(obj[8])));
					emp.setLocation(state);
				}

				if (String.valueOf(obj[9]) != null && !String.valueOf(obj[9]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[9]).equalsIgnoreCase("")) {
					Technology primarySkill = new Technology();
					primarySkill = technologyService.getTechnologyById(Integer.parseInt(String.valueOf(obj[9])));
					emp.setPrimaryTechnology(primarySkill);
				}

				if (String.valueOf(obj[10]) != null && !String.valueOf(obj[10]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[10]).equalsIgnoreCase("")) {
					emp.setOnboardingDate(String.valueOf(obj[10]));
				}

				if (String.valueOf(obj[11]) != null && !String.valueOf(obj[11]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[11]).equalsIgnoreCase("")) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String endDateStringvalue = String.valueOf(obj[11]);
					Date endDate = null;
					try {
						endDate = dateFormat.parse(endDateStringvalue);
					} catch (ParseException e) {
						logger.error(" Some Problem occurs :- "+e.getMessage());
					}
					emp.setOffboardingDate(String.valueOf(obj[11]));
				}

				if (String.valueOf(obj[15]) != null && !String.valueOf(obj[15]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[15]).equalsIgnoreCase("")) {
					Employee mgr = new Employee();
					mgr = employeeService.getEmployeeById(Long.parseLong(String.valueOf(obj[15])));
					emp.setManager(mgr);
				}

				if (String.valueOf(obj[19]) != null && !String.valueOf(obj[19]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[19]).equalsIgnoreCase("")) {
					GlobalGrade globalGrade = new GlobalGrade();
					globalGrade = globalGradeService.getGlobalGradeById(Integer.parseInt(String.valueOf(obj[19])));
					emp.setGlobalGrade(globalGrade);
				}

				if (String.valueOf(obj[21]) != null && !String.valueOf(obj[21]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[21]).equalsIgnoreCase("")) {
					emp.setEmpType(String.valueOf(obj[21]));
				}

				if (String.valueOf(obj[22]) != null && !String.valueOf(obj[22]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[22]).equalsIgnoreCase("")) {
					emp.setActualOffboardingDate(String.valueOf(obj[22]));

				}

				if (String.valueOf(obj[25]) != null && !String.valueOf(obj[25]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[25]).equalsIgnoreCase("")) {
					emp.setPsaId(String.valueOf(obj[25]));
				}

				if (String.valueOf(obj[29]) != null && !String.valueOf(obj[29]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[29]).equalsIgnoreCase("")) {
					/*CGEntityDetail cgEntity = new CGEntityDetail();
					cgEntity = cgEntityService.getCgEntityById(Integer.parseInt(String.valueOf(obj[29])));
					emp.setCgEntityDetail(cgEntity);*/
					emp.setCgEntity(String.valueOf(obj[29]));
				}

				if (String.valueOf(obj[32]) != null && !String.valueOf(obj[32]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[32]).equalsIgnoreCase("")) {
					Employee Em = new Employee();
					Em = employeeService.getEmployeeById(Long.parseLong(String.valueOf(obj[32])));
					emp.setEM(Em);
				}

				if (String.valueOf(obj[33]) != null && !String.valueOf(obj[33]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[33]).equalsIgnoreCase("")) {
					BundleEm bundleEm = new BundleEm();
					bundleEm = bundleEmService.getBundleEmById(Integer.parseInt(String.valueOf(obj[33])));
					emp.setBundleEM(bundleEm);

				}

				if (String.valueOf(obj[34]) != null && !String.valueOf(obj[34]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[34]).equalsIgnoreCase("")) {
					OffshoreEm offShoreEM = new OffshoreEm();

					offShoreEM = offShoreEmService.getOffshoreById(Integer.parseInt(String.valueOf(obj[34])));

					emp.setOffshoreEm(offShoreEM);

				}

				if (String.valueOf(obj[37]) != null && !String.valueOf(obj[37]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[37]).equalsIgnoreCase("null")) {
					Bis bis = new Bis();
					bis = bisService.getBisById(Integer.parseInt(String.valueOf(obj[37])));
					emp.setBis(bis);

				}

				if (String.valueOf(obj[39]) != null && !String.valueOf(obj[39]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[39]).equalsIgnoreCase("")) {
					emp.setMonthlyFTE(Float.valueOf(df.format(obj[39])));
				}

				if (String.valueOf(obj[40]) != null && !String.valueOf(obj[40]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[40]).equalsIgnoreCase("")) {
					emp.setSecondFTE(Float.valueOf(df.format(obj[40])));
				}

				if (String.valueOf(obj[41]) != null && !String.valueOf(obj[41]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[41]).equalsIgnoreCase("")) {
					emp.setThirdFTE(Float.valueOf(df.format(obj[41])));
				}

				if (String.valueOf(obj[42]) != null && !String.valueOf(obj[42]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[42]).equalsIgnoreCase("")) {
					emp.setFourthFTE(Float.valueOf(df.format(obj[42])));
				}

				if (String.valueOf(obj[43]) != null && !String.valueOf(obj[43]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[43]).equalsIgnoreCase("")) {
					emp.setFifthFTE(Float.valueOf(df.format(obj[43])));
				}

				if (String.valueOf(obj[44]) != null && !String.valueOf(obj[44]).equalsIgnoreCase("null")
						&& !String.valueOf(obj[44]).equalsIgnoreCase("")) {
					emp.setSixthFTE(Float.valueOf(df.format(obj[44])));
				}

				empList.add(emp);
			}
		}

		return empList;
	}

	@Override
	public List<Employee> getMissingData(String searchType, int country_id) {
		
		Calendar cal = Calendar.getInstance();
	    Date currentdate = cal.getTime();
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
	
		if (country_id != 0) {
			criteria.add(Restrictions.eq("country.countryId", country_id));
		}
		
		if(searchType.equalsIgnoreCase("Actual Offboarding")){
			criteria.add(Restrictions.le("endDate", currentdate));
			criteria.add(Restrictions.isNull("actualEndDate"));
			return criteria.list();
		}else if(searchType.equalsIgnoreCase("Local Grade")) {
			criteria.add(Restrictions.eq("country.countryId", 2)); // local grades only for india
			Criterion c1 = Restrictions.isNotNull("actualEndDate");
			Criterion c2 = Restrictions.ge("actualEndDate", currentdate);
			LogicalExpression conjActualEndDate = Restrictions.and(c1, c2);

			Criterion c3 = Restrictions.isNull("actualEndDate");
			Criterion c4 = Restrictions.isNotNull("endDate");
			Criterion c5 = Restrictions.ge("endDate", currentdate);
			LogicalExpression logiclConj = Restrictions.and(c3, Restrictions.and(c4, c5));
			//criteria.add(Restrictions.or(conjActualEndDate, logiclConj));
			criteria.add(Restrictions.or(conjActualEndDate, c3));//mehens-new
			
			List<Employee> listToBeFiltered =  criteria.list();
			
			ListIterator<Employee> iter = listToBeFiltered.listIterator();
			while(iter.hasNext()) {
				Employee emp = iter.next();
				if(emp.getGlobalGrade().getGlobalGradeId().equals(emp.getGrade().getGlobalGradeId())) {
					//iter.previous();
					iter.remove();
				}
				else {
					System.out.println(emp.getCorpId());
				}
			}
			
			return listToBeFiltered;
			
		}else if(searchType.equalsIgnoreCase("Location")) {
			
			Criterion c1 = Restrictions.isNotNull("actualEndDate");
			Criterion c2 = Restrictions.ge("actualEndDate", currentdate);
			LogicalExpression conjActualEndDate = Restrictions.and(c1, c2);

			Criterion c3 = Restrictions.isNull("actualEndDate");
			Criterion c4 = Restrictions.isNotNull("endDate");
			Criterion c5 = Restrictions.ge("endDate", currentdate);
			LogicalExpression logiclConj = Restrictions.and(c3, Restrictions.and(c4, c5));
			//criteria.add(Restrictions.or(conjActualEndDate, logiclConj));
			criteria.add(Restrictions.or(conjActualEndDate, c3));//mehens-new
			List<Employee> listToBeFiltered = criteria.list();
			
			ListIterator<Employee> iter = listToBeFiltered.listIterator();
			while(iter.hasNext()) {
				Employee emp = iter.next();
				if(emp.getCountry().getCountryId().equals(emp.getLocation().getCountryId())) {
					iter.previous();
					iter.remove();
				}
			}
			return listToBeFiltered;
		}else if(searchType.equalsIgnoreCase("BIS PrimaryProgram")) {
			Criterion c1 = Restrictions.isNotNull("actualEndDate");
			Criterion c2 = Restrictions.ge("actualEndDate", currentdate);
			LogicalExpression conjActualEndDate = Restrictions.and(c1, c2);

			Criterion c3 = Restrictions.isNull("actualEndDate");
			Criterion c4 = Restrictions.isNotNull("endDate");
			Criterion c5 = Restrictions.ge("endDate", currentdate);
			LogicalExpression logiclConj = Restrictions.and(c3, Restrictions.and(c4, c5));
			//criteria.add(Restrictions.or(conjActualEndDate, logiclConj));
			criteria.add(Restrictions.or(conjActualEndDate, c3));//mehens-new
			List<Employee> listToBeFiltered = criteria.list();
			
			ListIterator<Employee> iter = listToBeFiltered.listIterator();
			while(iter.hasNext()) {
				Employee emp = iter.next();
				if(emp.getPrimaryprogram().getBisList().contains(emp.getBis())) {
					iter.previous();
					iter.remove();
				}
			}
			return listToBeFiltered;
		}else if (searchType.equalsIgnoreCase("empId")) {
		
			criteria.add(Restrictions.ne("country.countryId", 1));
		}
        if(searchType.equalsIgnoreCase("repCorpId")){
        	criteria.add(Restrictions.or(Restrictions.isNull("repCorpId"), Restrictions.eqOrIsNull("repCorpId","")));
        	criteria.add(Restrictions.le("actualEndDate", currentdate));
    		criteria.add(Restrictions.eq("replacementRequired", true));
    	
        }else{
        	criteria.add(Restrictions.or(Restrictions.isNull(searchType), Restrictions.eqOrIsNull(searchType,"")));
        	
        }	
	   
		return criteria.list();

	}

	@Override
	public List<Employee> getOrphanList(int country_id, String res_status) {

		List<Employee> listEmployees = new ArrayList<>();
		Employee employee = new Employee();
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);

		if (country_id != 0 && res_status.equalsIgnoreCase("Manager")) {
			String queryString = OnBoardingSQLQueries.orphanManagerListCountryWise;
			TypedQuery<Employee> query = session.createSQLQuery(queryString);
			query.setParameter("country_id", country_id);
			listEmployees = query.getResultList();
		} else if (country_id == 0 && res_status.equalsIgnoreCase("Manager")){
			String queryString = OnBoardingSQLQueries.orphanManagerList;
			TypedQuery<Employee> query = session.createSQLQuery(queryString);
			listEmployees = query.getResultList();
		}else {
			String queryString = null;
			
			if (res_status.equalsIgnoreCase("EM")){
				queryString = OnBoardingSQLQueries.orphanEMList;
			} else if (res_status.equalsIgnoreCase("BundleEM")){
				queryString = OnBoardingSQLQueries.orphanBundleEMList;
			} else if (res_status.equalsIgnoreCase("OffshoreEM")){
				queryString = OnBoardingSQLQueries.orphanOffshoreEMList;
			}
			
			if(country_id != 0) {
				queryString = queryString+" and  country_id = :country_id ";
			}
			TypedQuery<Employee> query = session.createSQLQuery(queryString);
			if(country_id != 0) {
				query.setParameter("country_id", country_id);
			}
			listEmployees = query.getResultList();
		}
		
		List<Employee> managerList = new ArrayList<>();
		Iterator itr = listEmployees.iterator();
		while (itr.hasNext()) {
			Employee emp = new Employee();
			Object[] obj = (Object[]) itr.next();
			emp.setId(Long.parseLong(String.valueOf(obj[0])));
			if (String.valueOf(obj[1]) != null && !String.valueOf(obj[1]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[1]).equalsIgnoreCase("")) {
				emp.setEmpId(String.valueOf(obj[1]));
			}
			if (String.valueOf(obj[2]) != null && !String.valueOf(obj[2]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[2]).equalsIgnoreCase("")) {
				emp.setCorpId(String.valueOf(obj[2]));
			}
			if (String.valueOf(obj[3]) != null && !String.valueOf(obj[3]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[3]).equalsIgnoreCase("")) {
				emp.setFirstName(String.valueOf(obj[3]));
			}
			if (String.valueOf(obj[4]) != null && !String.valueOf(obj[4]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[4]).equalsIgnoreCase("")) {
				emp.setLastName(String.valueOf(obj[4]));
			}
			if (String.valueOf(obj[5]) != null && !String.valueOf(obj[5]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[5]).equalsIgnoreCase("")) {
				emp.setEmail(String.valueOf(obj[5]));
			}
			if (String.valueOf(obj[6]) != null && !String.valueOf(obj[6]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[6]).equalsIgnoreCase("")) {
				Grade grade = new Grade();
				grade = gradeService.getGradeById(Integer.parseInt(String.valueOf(obj[6])));
				emp.setGrade(grade);
			}
			if (String.valueOf(obj[7]) != null && !String.valueOf(obj[7]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[7]).equalsIgnoreCase("")) {
				Country country = new Country();
				country = countryService.getCountryById(Integer.parseInt(String.valueOf(obj[7])));
				emp.setCountry(country);
			}
			if (String.valueOf(obj[8]) != null && !String.valueOf(obj[8]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[8]).equalsIgnoreCase("")) {
				State state = new State();
				state = stateService.getStateById(Integer.parseInt(String.valueOf(obj[8])));
				emp.setLocation(state);
			}

			if (String.valueOf(obj[9]) != null && !String.valueOf(obj[9]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[9]).equalsIgnoreCase("")) {
				Technology primarySkill = new Technology();
				primarySkill = technologyService.getTechnologyById(Integer.parseInt(String.valueOf(obj[9])));
				emp.setPrimaryTechnology(primarySkill);
			}

			if (String.valueOf(obj[10]) != null && !String.valueOf(obj[10]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[10]).equalsIgnoreCase("")) {
				emp.setJoiningDate((Date) obj[10]);
			}

			if (String.valueOf(obj[11]) != null && !String.valueOf(obj[11]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[11]).equalsIgnoreCase("")) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String endDateStringvalue = String.valueOf(obj[11]);
				Date endDate = null;
				try {
					endDate = dateFormat.parse(endDateStringvalue);
				} catch (ParseException e) {
					logger.error(" Some Problem occurs :- "+e.getMessage());
				}
				emp.setEndDate((Date) obj[11]);
			}

			if (String.valueOf(obj[15]) != null && !String.valueOf(obj[15]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[15]).equalsIgnoreCase("")) {
				Employee mgr = new Employee();
				mgr = employeeService.getEmployeeById(Long.parseLong(String.valueOf(obj[15])));
				emp.setManager(mgr);
			}

			if (String.valueOf(obj[19]) != null && !String.valueOf(obj[19]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[19]).equalsIgnoreCase("")) {
				GlobalGrade globalGrade = new GlobalGrade();
				globalGrade = globalGradeService.getGlobalGradeById(Integer.parseInt(String.valueOf(obj[19])));
				emp.setGlobalGrade(globalGrade);
			}

			if (String.valueOf(obj[21]) != null && !String.valueOf(obj[21]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[21]).equalsIgnoreCase("")) {
				emp.setEmpType(String.valueOf(obj[21]));
			}

			if (String.valueOf(obj[22]) != null && !String.valueOf(obj[22]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[22]).equalsIgnoreCase("")) {
				emp.setActualEndDate((Date) obj[22]);

			}

			if (String.valueOf(obj[25]) != null && !String.valueOf(obj[25]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[25]).equalsIgnoreCase("")) {
				emp.setPsaId(String.valueOf(obj[25]));
			}

			if (String.valueOf(obj[29]) != null && !String.valueOf(obj[29]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[29]).equalsIgnoreCase("")) {
				/*CGEntityDetail cgEntity = new CGEntityDetail();
				cgEntity = cgEntityService.getCgEntityById(Integer.parseInt(String.valueOf(obj[29])));
				emp.setCgEntityDetail(cgEntity);*/
				emp.setCgEntity(String.valueOf(obj[29]));
			}

			if (String.valueOf(obj[32]) != null && !String.valueOf(obj[32]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[32]).equalsIgnoreCase("")) {
				Employee Em = new Employee();
				Em = employeeService.getEmployeeById(Long.parseLong(String.valueOf(obj[32])));
				emp.setEM(Em);
			}

			if (String.valueOf(obj[33]) != null && !String.valueOf(obj[33]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[33]).equalsIgnoreCase("")) {
				BundleEm bundleEm = new BundleEm();
				bundleEm = bundleEmService.getBundleEmById(Integer.parseInt(String.valueOf(obj[33])));
				emp.setBundleEM(bundleEm);
			}

			if (String.valueOf(obj[34]) != null && !String.valueOf(obj[34]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[34]).equalsIgnoreCase("")) {
				OffshoreEm offShoreEM = new OffshoreEm();

				offShoreEM = offShoreEmService.getOffshoreById(Integer.parseInt(String.valueOf(obj[34])));

				emp.setOffshoreEm(offShoreEM);
			}

			if (String.valueOf(obj[37]) != null && !String.valueOf(obj[37]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[37]).equalsIgnoreCase("null")) {
				Bis bis = new Bis();
				bis = bisService.getBisById(Integer.parseInt(String.valueOf(obj[37])));
				emp.setBis(bis);
			}
			
			if (String.valueOf(obj[15]) != null && !String.valueOf(obj[15]).equalsIgnoreCase("null")
					&& !String.valueOf(obj[15]).equalsIgnoreCase("")) {
				Employee buddy = new Employee();
				buddy = employeeService.getEmployeeById(Long.parseLong(String.valueOf(obj[15])));
				emp.setBuddy(buddy);
			}
			managerList.add(emp);	
		}
		Collections.sort(managerList);
		return managerList;
	}

	@Override
	public List<Employee> getEmpNameByCorpId(String corpId) {
		List<Employee> replacementEmpDetail = null;
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
		if (corpId != null && !corpId.equalsIgnoreCase("")) {
			criteria.add(Restrictions.ilike("corpId", corpId));
		}
		replacementEmpDetail = criteria.list();
		return replacementEmpDetail;
	}

	@Override
	public List<Employee> getReplacementEmployees(int country_id) {
		Calendar cal = Calendar.getInstance();
		Date currentdate = cal.getTime();
		Employee emp = new Employee();
		List<Employee> empList = new ArrayList<>();
		String repfirstName = emp.getFirstName();
        Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
		Criteria mainCriteria = session.createCriteria(Employee.class);
		
		/*if (country_id != 0) {
			criteria.add(Restrictions.eq("country.countryId", country_id));
		}*/
		
		criteria.setProjection( Projections.distinct( Projections.property( "repCorpId")));
		criteria.add(Restrictions.ne("repCorpId", ""));
		//criteria.add(Restrictions.eq("repfirstName", repfirstName));
		//criteria.add(Restrictions.ne("repfirstName", ""));
		
        
		if(criteria.list().size()>0){
			
			mainCriteria.add(Restrictions.in("repCorpId", criteria.list()));
			
			if (country_id != 0){
			mainCriteria.add(Restrictions.eq("country.countryId", country_id));
			}
			empList = mainCriteria.list();
		}
	
		return empList;
	}

	@Override
	public List<Employee> emailForIndiaEmployee(int country_id,int bis_id,int location, String corpId) {
    Session session = this.sessionFactory.getCurrentSession();
    Criteria criteria = session.createCriteria(Employee.class);
    Employee emp = new Employee();
     
  /*  country_id = 2;
    bis_id = 3;*/
    if (country_id != 0 ) {
    	criteria.add(Restrictions.eq("country.countryId", country_id));
    	//criteria.add(Restrictions.eq("location.stateId", location));
    	Calendar cal = Calendar.getInstance();
		Date currentdate = cal.getTime();
		Criterion joiningdateCondtn = Restrictions.le("joiningDate", currentdate);
		criteria.add(joiningdateCondtn);
		Criterion c1 = Restrictions.isNotNull("actualEndDate");
		Criterion c2 = Restrictions.ge("actualEndDate", currentdate);
		LogicalExpression conjActualEndDate = Restrictions.and(c1, c2);

		Criterion c3 = Restrictions.isNull("actualEndDate");
		Criterion c4 = Restrictions.isNotNull("endDate");
		Criterion c5 = Restrictions.ge("endDate", currentdate);
		LogicalExpression logiclConj = Restrictions.and(c3, Restrictions.and(c4, c5));
		criteria.add(Restrictions.or(conjActualEndDate, logiclConj));
		
    	if(country_id == 2 ) {
    		 if(bis_id != 0) {
        		criteria.add(Restrictions.eq("bis.bis_id", bis_id));
    		}
    	}
    	else{
    		if(location != 0)
    		criteria.add(Restrictions.eq("location.stateId", location));
    	}
	}
    criteria.add(Restrictions.not(Restrictions.in("corpId",corpId)));
    List<Employee> employee = criteria.list();
   
   return employee;
  
	}

	@Override
	public BisPMOMap getBISFromPMO(int bisId) {
		
		Session session = this.sessionFactory.getCurrentSession();
		
	    Criteria criteria = session.createCriteria(BisPMOMap.class);
	    criteria.add(Restrictions.eq("bis_id", bisId));
	    BisPMOMap bisPMOMap = (BisPMOMap) criteria.uniqueResult();
		
		return bisPMOMap;
	}
	
	public void updateGithub(Employee emp) {
		logger.info(emp.getId()+"   "+emp.getCorpId());
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String status = null;
		try {
			Employee employee = session.get(Employee.class,emp.getId());
		
			if(employee != null) {
				employee.setBi(emp.isBi());
			}
			session.update(employee);
			tx.commit();
		
			}
	   catch(Exception e) {
		logger.info(e.getMessage());
		tx.rollback();
	     }
       finally {
		if(session != null && session.isOpen()) {
			session.close();
		}
	}
		
	}
	
	
	public boolean updatePSAID(Employee emp) {
		logger.info(emp.getId()+"   "+emp.getPsaId());
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//String status = null;
		boolean isExist = false;
		try {
			Employee employee = session.get(Employee.class,emp.getId());
		
		if(employee != null) {
	
			employee.setCreatedBy(emp.getCreatedBy());
			employee.setBi(emp.isBi());//new
			employee.setRequestedDate(emp.getRequestedDate());
			if(emp.getPsaId() != null && !"".equalsIgnoreCase(emp.getPsaId())) {
				employee.setPsaId(emp.getPsaId());
				employee.setGeneratedDate(emp.getGeneratedDate());
				employee.setPCAEmail(emp.getPCAEmail());
			}
			
			if(employee.getPsaLibType().getPsaLibId() != 0) { // Only if VM is requested.
				if(emp.getVmNumber() != null && !"".equalsIgnoreCase(emp.getVmNumber())) {
					employee.setVmgeneratedDate(emp.getVmgeneratedDate());
					employee.setVmrequestedDate(emp.getVmrequestedDate());
					employee.setVmNumber(emp.getVmNumber());
				}
				
			}
			
			
			
			if(emp.getComment() != null && !"".equalsIgnoreCase(emp.getComment())
					&& !emp.getComment().equalsIgnoreCase(employee.getComment())) {
				employee.setComment(emp.getComment());
			}
		}
		
		session.update(employee);
		Query query =  session.createNamedQuery("getEmpForPSAID");
		query.setParameter("corpID",emp.getCorpId());
		PreOnbEmployee preOnbEmp = null;
		PreOnbEmployee preOnbExist = null;
		List list = query.list();
		int i=0;
		if(list != null && !list.isEmpty()) {
			//mehens - code changes for RMPMO Submit date and res status for re-onboarded res - start 
			for(i=0;i<list.size();i++) {
				preOnbExist=(PreOnbEmployee)list.get(i);
				if(emp.getPreonbemp() != null) {
					if(emp.getPreonbemp().getId() == preOnbExist.getId())
					{
						preOnbEmp = (PreOnbEmployee)list.get(i);
					}
				}
				else {
					if(preOnbExist.getArchiveType() == null) {
						preOnbEmp = (PreOnbEmployee)list.get(i);
					}
				}
			}
			//mehens - code changes for RMPMO Submit date and res status for re-onboarded res - end
			//preOnbEmp = (PreOnbEmployee)list.get(0);
		}
		String resourceStatus = null;
		if(preOnbEmp != null) {
			 resourceStatus = preOnbEmp.getResourceStatus();
		}
		
		if(preOnbEmp != null) {
			if(OnboardingConstants.statusBISPMOSubmit.equals(preOnbEmp.getResourceStatus())
					&& (emp.getPsaId() == null || "".equalsIgnoreCase(emp.getPsaId()))) {  // Onboarding Initiated.
				preOnbEmp.setResourceStatus(OnboardingConstants.statusPSAIdRequested);  //add RM PMO requested date and submitted date too.- pending
			}
			/*else { // Onboarding Completed.
				
				preOnbEmp.setResourceStatus(OnboardingConstants.statusRMPMOSubmit);
				preOnbEmp.setRMPMOSubmitDT(new Date());
			}*/
			
			else if(OnboardingConstants.statusBISPMOSubmit.equals(preOnbEmp.getResourceStatus())
					&& (emp.getPsaId() != null || !("".equalsIgnoreCase(emp.getPsaId())))){ // Onboarding Completed.
				
				preOnbEmp.setResourceStatus(OnboardingConstants.statusRMPMOSubmit);
				preOnbEmp.setRMPMOSubmitDT(new Date());
				isExist = true;
			}
			else if(OnboardingConstants.statusRMPMOSubmit.equals(preOnbEmp.getResourceStatus())) {
				//no change in status if onboarding is already completed
				isExist = false;
			}
			
			session.update(preOnbEmp);
		}
		
		boolean isVMRequested = (employee.getPsaLibType().getPsaLibId()!= 0)? true : false;
		boolean isPSAIDNull = (emp.getPsaId() == null || "".equalsIgnoreCase(emp.getPsaId())) ? true : false;
		boolean isVMNull = (emp.getVmNumber() == null || "".equalsIgnoreCase(emp.getVmNumber())) ? true : false;
		
		if(preOnbEmp != null) {
			boolean isOnboardingCompleted = (resourceStatus.equals(OnboardingConstants.statusRMPMOSubmit)) ? true : false;
			if(!isOnboardingCompleted) {
				//if(OnboardingConstants.statusBISPMOSubmit.equals(preOnbEmp.getResourceStatus())) {
				if(isPSAIDNull) {
					preOnbEmp.setResourceStatus(OnboardingConstants.statusPSAIdRequested);
					isExist = false;
				}
				else if(!isPSAIDNull && isVMRequested){
					if(isVMNull) {
						preOnbEmp.setResourceStatus(OnboardingConstants.statusVMRequested);
						isExist = false;
					}else {
						preOnbEmp.setResourceStatus(OnboardingConstants.statusRMPMOSubmit);
						preOnbEmp.setRMPMOSubmitDT(new Date());
						isExist = true;
					}
				}else{
					preOnbEmp.setResourceStatus(OnboardingConstants.statusRMPMOSubmit);
					preOnbEmp.setRMPMOSubmitDT(new Date());
					isExist = true;
				}
			//}
			session.update(preOnbEmp);
			}
		}
		
		tx.commit();
		//status = preOnbEmp.getResourceStatus();
	}
	catch(Exception e) {
		logger.info(e.getMessage());
		tx.rollback();
	}
	finally {
		if(session != null && session.isOpen()) {
			session.close();
		}
	}
		return isExist;
		
	}
	
	public Employee getEmpForRmPMO(Long id) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Employee employee = new Employee();
	
		try {
			employee =  session.get(Employee.class, id);
		tx.commit();
	}
	catch(Exception e) {
		logger.info(e.getMessage());
		tx.rollback();
	}
	finally {
		if(session != null && session.isOpen()) {
			session.close();
		}
	}
		return employee;
	}

	@Override
	public Spoc getSpocFromCountry(Integer countryId) {
		
		Session session = this.sessionFactory.getCurrentSession();
		
	    Criteria criteria = session.createCriteria(Spoc.class);
	    criteria.add(Restrictions.eq("countryId", countryId ));
	    Spoc spocRec = (Spoc) criteria.uniqueResult();
		
		return spocRec;
	}
	
	@Override
	public List<Employee> getOffboardingEmpList(Date d) {
		
		Session session;
		session = this.sessionFactory.openSession();
		session.beginTransaction();
		List<Employee> OffboardingList = new ArrayList<Employee>();
		Criteria criteria = session.createCriteria(Employee.class);
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateWithoutTime;*/
		
			//dateWithoutTime = sdf.parse(sdf.format(new Date()));
			criteria.add(Restrictions.eq("actualEndDate", d));
			criteria.add(Restrictions.isNull("archiveType"));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			OffboardingList = criteria.list();
		
			
		session.close();
		//String query = "SELECT * FROM `employee` WHERE actual_end_date=date(now()) and archive_type is null";
		//TypedQuery<Employee> tquery = session.createSQLQuery(query);
		
		//OffboardingList = tquery.getResultList();
		return OffboardingList;
		
	}

	@Override
	public String bememail(int id) {
		Session session;
		session = this.sessionFactory.openSession();
		session.beginTransaction();
		String empemail = (String) session.createQuery("select emp.email from Employee emp where emp.id = :id").setInteger("id", id).uniqueResult();

		session.close();
		return empemail;
	}

	@Override
	public List<Employee> getBillableEmployeesCountryWise() {
		Session session;
		try {
			session = this.sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		TypedQuery<Employee> query = session.createSQLQuery(OnBoardingSQLQueries.ListBillableEmpCountryWise);
		return query.getResultList();
		
	}

	@Override
	public List<Employee> getBillableInternalEmployeesCountryWise() {
		Session session;
		try {
			session = this.sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		TypedQuery<Employee> query = session.createSQLQuery(OnBoardingSQLQueries.ListBillableInternalEmpCountryWise);
		return query.getResultList();
	}

	@Override
	public List<Employee> getBillableExternalEmployeesCountryWise() {
		Session session;
		try {
			session = this.sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		TypedQuery<Employee> query = session.createSQLQuery(OnBoardingSQLQueries.ListBillableExternalEmpCountryWise);
		return query.getResultList();
	}

	@Override
	public List<Employee> getDashBoardBillableEmpList() {
		Session session;
		try {
			session = this.sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		String queryString = OnBoardingSQLQueries.DashBoardGridBillableEmployee;
		TypedQuery<Employee> query = session.createSQLQuery(queryString);
		return query.getResultList();
	}
	
	@Override
	public List<RollOffType> getRollOffTypeList(){
		Session session;
		try {
			session = this.sessionFactory.openSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		Criteria criteria = session.createCriteria(RollOffType.class);
		List<RollOffType> list = criteria.list();
	
		
		/*List<RollOffType> rolloftype = new ArrayList<>();
		Iterator itr = list.iterator();
	for(int i=0; i<list.size(); i++)
		{
			list.get(i);
			if((list.get(i).getId()!=(15)))
			{
				
				rolloftype.add(list.get(i));
			
				
			}
		}
		*/
		session.close();
		return list;
	}
	
	@Override
	public List<ReplacementType> getReplacementTypeList(){
		Session session;
		try {
			session = this.sessionFactory.openSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		Criteria criteria = session.createCriteria(ReplacementType.class);
		List<ReplacementType> list = criteria.list();
		session.close();
		return list;
	}

	@Override
	public ReplacementType getReplacementTypeById(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();	
		ReplacementType replace = null;
		replace = (ReplacementType) session.load(ReplacementType.class, new Integer(id));
		logger.info("ReplacementType loaded successfully,ReplacementType details=" + replace);
		session.close();
		return replace;
	}

	@Override
	public RollOffType getRollOffTypeById(int id) {
		
		Session session = this.sessionFactory.openSession();	
		RollOffType roll = null;
		roll = (RollOffType) session.load(RollOffType.class, new Integer(id));
		logger.info("RollOffType loaded successfully,RollOffType details=" + roll);
		session.close();
		return roll;
		
	}
	
	@Override
	public List<Employee> getActiveResourcesList(){
		
		Session session = this.sessionFactory.openSession();	
		Calendar cal = Calendar.getInstance();
		Date currentdate = cal.getTime();
		Criteria criteria = session.createCriteria(Employee.class);
		Criterion c1 = Restrictions.isNotNull("actualEndDate");
		Criterion c2 = Restrictions.gt("actualEndDate", currentdate);
		LogicalExpression conjActualEndDate = Restrictions.and(c1, c2);

		Criterion c3 = Restrictions.isNull("actualEndDate");
		Criterion c4 = Restrictions.isNotNull("endDate");
		Criterion c5 = Restrictions.gt("endDate", currentdate);
		LogicalExpression logiclConj = Restrictions.and(c3, Restrictions.and(c4, c5));
		criteria.add(Restrictions.or(conjActualEndDate, logiclConj));
		List<Employee> list = criteria.list();
		session.close();
		return list;
	}

	@Override
	public String emailId(String corpId) {
		Session session;
		session = this.sessionFactory.openSession();
		session.beginTransaction();
		
		String empemail = (String) session.createQuery("select distinct(emp.email) from Employee emp where emp.corpId = :corpId").setString("corpId", corpId).uniqueResult();
		session.close();
		return empemail;
	}
	@Override
	public String corpId(String corpId) {
		Session session;
		session = this.sessionFactory.openSession();
		session.beginTransaction();
		
		String corpIds = (String) session.createQuery("select distinct(emp.corpId) from Employee emp where emp.corpId = :corpId").setString("corpId", corpId).uniqueResult();
		return corpIds;
	}
	
	@Override
	public Long buddyId(String corpId) {
		Session session;
		session = this.sessionFactory.openSession();
		session.beginTransaction();
	    Long buddyId =(Long) session.createQuery("select distinct(emp.id) from Employee emp where emp.corpId = :corpId").setString("corpId", corpId).uniqueResult();
		session.close();
		return buddyId;
	}
	@Override
	public Employee buddyIds(String email) {
		Session session;
		session = this.sessionFactory.openSession();
		session.beginTransaction();
		Calendar cal = Calendar.getInstance();
		Date currentdate = cal.getTime();
		currentdate.setHours(0);
		currentdate.setMinutes(0);
		currentdate.setSeconds(0);
		
		Employee employee= new Employee();
		String Firstname =   (String) session.createQuery("select distinct(emp.firstName) from Employee emp where emp.email = :email").setString("email", email).uniqueResult();
		//Long id =   (Long) session.createQuery("select distinct(emp.id) from Employee emp where emp.email = :email and ((emp.actual_end_date is null and emp.end_date > :currentdate) OR\n" + 
		//		" (emp.actual_end_date is not null and emp.actual_end_date > :currentdate))").setString("email", email).setDate("currentdate", currentdate).uniqueResult();
		String Lastname =   (String) session.createQuery("select distinct(emp.lastName) from Employee emp where emp.email = :email").setString("email", email).uniqueResult();
		String corpId =   (String) session.createQuery("select distinct(emp.corpId) from Employee emp where emp.email = :email").setString("email", email).uniqueResult();

		employee.setFirstName(Firstname);
		//employee.setId(id);
		employee.setLastName(Lastname);
		employee.setCorpId(corpId);
		return employee;
	
	}

	@Override
	public Employee getEmployeeByEmail(String email) {//mehens-buddy
		//Session session = this.sessionFactory.openSession();
		//session.beginTransaction();
		//List<Employee> buddy = (List) session.createQuery("select distinct(emp) from Employee emp where emp.email = :email").setString("email", email);
		//Employee  p = buddy.get(0);
		Session session = this.sessionFactory.getCurrentSession();
		TypedQuery<Employee> query = session.createNamedQuery("listBuddyByEmail");
		query.setParameter("email", email);
		List<Employee> buddy = query.getResultList();
		Employee p = buddy.get(0);
		return p;
	}
	
	
	

}
