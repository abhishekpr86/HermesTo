package com.capgemini.onboarding.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.constants.OnBoardingSQLQueries;
import com.capgemini.onboarding.constants.OnboardingConstants;
import com.capgemini.onboarding.model.BundleEm;
import com.capgemini.onboarding.model.DLList;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.JiraIDCreator;
import com.capgemini.onboarding.model.PreOnbEmployee;
import com.capgemini.onboarding.model.RmPMO;
import com.capgemini.onboarding.model.Role;
import com.capgemini.onboarding.model.UserRoles;
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
public class PreOnbEmployeeDAOImpl implements PreOnbEmployeeDAO {

	private static final Logger logger = LoggerFactory.getLogger(PreOnbEmployeeDAOImpl.class);

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
	
	@Autowired(required = true)
	private EmployeeDAO empdao;
	
	@Override
	public void addPreOnbEmployee(PreOnbEmployee p) {
		/*logger.info("p : ");
		Session session = this.sessionFactory.getCurrentSession();
		session.clear();
		logger.info("p : "+p);
		//session.save(p);
		session.saveOrUpdate(p);
		logger.info("employee saved successfully, Employee Details=" + p);*/
		
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<PreOnbEmployee> existingEmpList = new ArrayList<>();
		try {
			Criteria criteria = session.createCriteria(PreOnbEmployee.class);
			criteria.add(Restrictions.eq("corpId", p.getCorpId()));
			criteria.add(Restrictions.eq("resourceStatus", OnboardingConstants.statusRMPMOSubmit));
			criteria.add(Restrictions.isNull("archiveType"));
			existingEmpList = criteria.list();
			if(existingEmpList != null && !existingEmpList.isEmpty()) {
				for(int i=0; i< existingEmpList.size(); i++) {
					PreOnbEmployee prevEmp = existingEmpList.get(i);
					prevEmp.setArchiveType(1);
					session.update(prevEmp);
				}
				session.save(p);
			}else {
				session.saveOrUpdate(p);
			}
			//session.save(p);
			
			
			tx.commit();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
			logger.error(" Some Problem occurs :- "+e.getMessage());
			tx.rollback();
			throw e;
		}
		finally {
			if(session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public void updatePreOnbEmployee(PreOnbEmployee p) {
		
		Session session = this.sessionFactory.getCurrentSession();
		session.clear();
		
		session.saveOrUpdate(p);
	

		logger.info("employee updated successfully, Employee Details=" + p.toString());

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

		String queryString = OnBoardingSQLQueries.ListOfActiveManager;
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
		//Collections.sort(managerList);

		return managerList;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> listBuddy() {
		List<Employee> listEmployees = new ArrayList<>();
		Session session = this.sessionFactory.getCurrentSession();
		/*
		 * TypedQuery<Employee> query =
		 * session.createNamedQuery("listManagers");
		 */

		String queryString = OnBoardingSQLQueries.ListOfActiveManager;
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
		//Collections.sort(managerList);

		return managerList;
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

	/*@Override
	public List<Employee> listManagersByEm() {
		List<Employee> listEmployees = new ArrayList<>();
		Session session = this.sessionFactory.getCurrentSession();
		
		 * TypedQuery<Employee> query =
		 * session.createNamedQuery("listManagers");
		 

		String queryString = OnBoardingSQLQueries.ListOfManagersByEm;
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
	}*/


	@Override
	public PreOnbEmployee getPreOnbEmployeeById(Long id) {
		Session session = this.sessionFactory.getCurrentSession();
		PreOnbEmployee p = (PreOnbEmployee) session.get(PreOnbEmployee.class, id);
		logger.info("Person loaded successfully, Person details=" + p);
		session.clear();
	
		return p;
	}

	@Override
	public void removePreOnbEmployee(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		PreOnbEmployee p = (PreOnbEmployee) session.load(PreOnbEmployee.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
		logger.info("Person deleted successfully, person details=" + p);
	}

	@Override
	public List<PreOnbEmployee> getPreOnbEmployeeByGrade(List<Integer> gradeList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PreOnbEmployee> getAllPreOnbEmployee() {
		Session session = this.sessionFactory.getCurrentSession();
		TypedQuery<PreOnbEmployee> query = session.createNamedQuery("listAllPreOnbEmployees");
		return query.getResultList();
		
	}

	@Override
	public List<PreOnbEmployee> searchPreOnbEmployee(PreOnbEmployee emp) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PreOnbEmployee.class);
		//String empId = emp.getEmpId();
		String corpId = emp.getCorpId();
		String firstName = emp.getFirstName();
		String lastName = emp.getLastName();
		
		 /* if(empId!= null ){ criteria.add(Restrictions.eq("empId", empId)); }
		 
		if (empId != null && !empId.equalsIgnoreCase("")) {
			criteria.add(Restrictions.ilike("empId", empId, MatchMode.ANYWHERE));
		}*/
		if (corpId != null && !corpId.equalsIgnoreCase("")) {
			criteria.add(Restrictions.ilike("corpId", corpId, MatchMode.ANYWHERE));
		}
		if (firstName != null && !firstName.equalsIgnoreCase("")) {
			criteria.add(Restrictions.ilike("firstName", firstName, MatchMode.ANYWHERE));
		}
		if (lastName != null && !lastName.equalsIgnoreCase("")) {
			criteria.add(Restrictions.ilike("lastName", lastName, MatchMode.ANYWHERE));
		}

		criteria.add(Restrictions.ne("id", new Long(0)));

		return criteria.list();
		
	}

	@Override
	public boolean checkCorpIdExists(String corpId, String empId, String empType, String pageType) {
		Session session = this.sessionFactory.getCurrentSession();
		/*Criteria criteria = session.createCriteria(PreOnbEmployee.class);
		boolean isExist = false;
		
		if (corpId != null && !corpId.equalsIgnoreCase("")) {
			criteria.add(Restrictions.ilike("corpId", corpId));
		}
		List<Employee> emplist = criteria.list();
		if (emplist != null && !emplist.isEmpty()) {
			isExist = true;
		}*/
		
		boolean isExist = false;
		String hql = "FROM Employee e where e.corpId=:corpId and e.archiveType is null and (e.actualEndDate is null or e.actualEndDate > now())";
		
		Query query = session.createQuery(hql);
		query.setParameter("corpId",corpId);
		List<Employee> emplist = query.list();
		
		if (emplist != null && !emplist.isEmpty()) {
			isExist = true;
		}
		
		String hqlPreOnb = "From PreOnbEmployee pe where pe.corpId=:corpId and pe.resourceStatus in ('EMSubmitted','RMApproved','RMRejected')";
		Query preOnbQuery = session.createQuery(hqlPreOnb);
		preOnbQuery.setParameter("corpId",corpId);
		List<PreOnbEmployee> preOnbList = preOnbQuery.list();
		if(!isExist) {
			if(preOnbList != null && !preOnbList.isEmpty()) {
				isExist = true;
			}
		}
		return isExist;
	}

	@Override
	public boolean checkEmployeeEmailExists(String email, String empId, String empType) { //R9.3

		Session session = this.sessionFactory.getCurrentSession();
		logger.info("checkEmployeeEmailExists123");
		Criteria criteria = session.createCriteria(Employee.class);
		boolean isExits = false;
		Calendar cal = Calendar.getInstance();
		Date currentdate = cal.getTime();

		if (email != null && !email.equalsIgnoreCase("")) {
			criteria.add(Restrictions.ilike("email", email));
			Criterion c1 = Restrictions.isNotNull("actualEndDate");
			Criterion c2 = Restrictions.ge("actualEndDate", currentdate);
			LogicalExpression conjActualEndDate = Restrictions.and(c1, c2);

			Criterion c3 = Restrictions.isNull("actualEndDate");
			Criterion c4 = Restrictions.isNotNull("endDate");
			Criterion c5 = Restrictions.ge("endDate", currentdate);
			LogicalExpression logiclConj = Restrictions.and(c3, Restrictions.and(c4, c5));
			criteria.add(Restrictions.or(conjActualEndDate, logiclConj));
			
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
	public PreOnbEmployee getPreOnbEmployeeByCorpId(String corpID) {

		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PreOnbEmployee.class);
		criteria.add(Restrictions.eq("corpId", corpID));
		List<PreOnbEmployee> p = criteria.list();
		PreOnbEmployee emp = null;
		if (p != null) {
			emp = p.get(0);
		}
		return emp;
	
	}

	@Override
	public List<PreOnbEmployee> getAllEmployees() {

		Session session = this.sessionFactory.getCurrentSession();
		TypedQuery<PreOnbEmployee> query = session.createNamedQuery("listAllEmployees");
		List<PreOnbEmployee> preOnbAllEmp = query.getResultList();
		return preOnbAllEmp;
	}

	@Override
	public Employee getResourceMgrEmpRec(Integer resourceMgrId) {

		String resId = resourceMgrId.toString(resourceMgrId);
		
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("id", resourceMgrId.longValue()));
		Employee e = (Employee) criteria.list().get(0);
		return e;
	}

	@Override
	public boolean checkPreOnbEmployeeExists(String empId, String empType) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(PreOnbEmployee.class);
		boolean isExist = false;
		if (empId != null && !empId.equalsIgnoreCase("")) {
			if (empType.equalsIgnoreCase("Internal")) {
				criteria.add(Restrictions.ilike("empId", empId));
			}/* else {
				criteria.add(Restrictions.ilike("psaId", empId));
			}*/

		}
		List<PreOnbEmployee> empList = criteria.list();
		if (empList != null && !empList.isEmpty()) {
			isExist = true;
		}
		return isExist;
	}
	
	//Mohini added
		

		@Override
		public Employee getEmpFromPreOnboarding(String corpId) {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			Employee employee = new Employee();
		try {
			Criteria criteria = session.createCriteria(PreOnbEmployee.class);
			
			ProjectionList proList = Projections.projectionList();
			proList.add(Projections.property("empId"),"empId");
			proList.add(Projections.property("vendor"),"vendor");
			proList.add(Projections.property("corpId"),"corpId");
			proList.add(Projections.property("firstName"),"firstName");
			proList.add(Projections.property("lastName"),"lastName");
			proList.add(Projections.property("email"),"email");
			proList.add(Projections.property("managerEmail"),"managerEmail");
			proList.add(Projections.property("EM"),"EM");
			proList.add(Projections.property("dob"),"dob");
			proList.add(Projections.property("bundleEM"),"bundleEM");
			proList.add(Projections.property("bis"),"bis");
			proList.add(Projections.property("primaryprogram"),"primaryprogram");
			proList.add(Projections.property("empType"),"empType");
			proList.add(Projections.property("country"),"country");
			proList.add(Projections.property("location"),"location");
			proList.add(Projections.property("grade"),"grade");
			proList.add(Projections.property("primaryTechnology"),"primaryTechnology");
			proList.add(Projections.property("secondaryTechnology"),"secondaryTechnology");//mehens
			proList.add(Projections.property("criticality"),"criticality");//mehens
			proList.add(Projections.property("vco"),"vco");//dkaushik
			proList.add(Projections.property("manager"),"manager");
			proList.add(Projections.property("empSex"),"empSex");
			proList.add(Projections.property("endDate"),"endDate");
			proList.add(Projections.property("billingDate"),"billingDate"); 
			proList.add(Projections.property("resourceManager"),"resourceManager");
			proList.add(Projections.property("comment"),"comment");
			proList.add(Projections.property("joiningDate"),"joiningDate");
			proList.add(Projections.property("resourceStatus"),"resourceStatus");
			proList.add(Projections.property("psaLibType"),"psaLibType");
			proList.add(Projections.property("indus"),"indus");
			proList.add(Projections.property("staffingRR"),"staffingRR");
			proList.add(Projections.property("role"),"role");
			proList.add(Projections.property("PSAIdReq"),"PSAIdReq");
			//proList.add(Projections.property("HRAReq"),"HRAReq"); 
			proList.add(Projections.property("ggId"),"ggId"); 
			proList.add(Projections.property("globalGrade"),"globalGrade"); 
			proList.add(Projections.property("jiraNumber"),"jiraNumber");
			proList.add(Projections.property("isVLANmailDone"),"isVLANmailDone");
			proList.add(Projections.property("pcSerialNumber"),"pcSerialNumber");
			//proList.add(Projections.property("macAddress"),"macAddress");
			//proList.add(Projections.property("upperVCycle"),"upperVCycle");
			//proList.add(Projections.property("bi"),"bi");//mehens
			//proList.add(Projections.property("timeNMaterial"),"timeNMaterial");
			proList.add(Projections.property("timeMat"),"timeMat");//mehens
			
			proList.add(Projections.property("cgEntity"),"cgEntity");
			proList.add(Projections.property("roleid"),"roleid");
			//proList.add(Projections.property("preonbemp"),"id");
			proList.add(Projections.property("requestor"),"requestor");
			proList.add(Projections.property("demandType"),"demandType");
			proList.add(Projections.property("workingBis"),"workingBis");
			proList.add(Projections.property("capManager"),"capManager");
			proList.add(Projections.property("capManagerEmail"),"capManagerEmail");
			proList.add(Projections.property("capSupervisor"),"capSupervisor");
			proList.add(Projections.property("capSupervisorEmail"),"capSupervisorEmail");
			proList.add(Projections.property("allocation"),"allocation");
			proList.add(Projections.property("project"),"project");
			//proList.add(Projections.property("coreTeam"),"coreTeam");
			proList.add(Projections.property("onboardingEmail"),"onboardingEmail");
		
			//Engg - Start
		//	proList.add(Projections.property("isEngg"),"isEngg");
			proList.add(Projections.property("orderG"),"orderG");
			proList.add(Projections.property("sdm"),"sdm");
			proList.add(Projections.property("otherAs"),"otherAs");
			proList.add(Projections.property("businessAs"),"businessAs");
			proList.add(Projections.property("cfao"),"cfao");
			//Engg - End
			
			criteria.setProjection(proList);
			criteria.add(Restrictions.eq("corpId", corpId));
			criteria.add(Restrictions.isNull("archiveType"));
			List<Employee> empList=  criteria.setResultTransformer(Transformers.aliasToBean(Employee.class)).list();
			
			if(empList != null && !empList.isEmpty()) {
				employee = empList.get(0);
			}
			
			tx.commit();
			return employee; 
		}
		catch(Exception e) {
			logger.info(e.getMessage());
			tx.rollback();
			return null;
		}
		finally {
			if(session != null && session.isOpen()) {
				session.close();
			}
		}
		
		}
		
		
		@SuppressWarnings({ "unchecked", "deprecation" })
		public void insertEmployee(Employee emp) {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			List<Employee> existingEmpList = new ArrayList<>();
		try {
			existingEmpList = session.createCriteria(Employee.class)
								.add(Restrictions.eq("corpId", emp.getCorpId()))
								.list();
					
			if(!emp.getPSAIdReq()) {
				emp.setPsaId("NO PSA ID");
			}
			if(emp.getPsaLibTypeID() == 0) {//mehens 10nov NO-VM
                emp.setVmNumber("NO VM");
            }
			if(existingEmpList != null && !existingEmpList.isEmpty()) {
				for(int i=0; i< existingEmpList.size(); i++) {
					Employee prevEmp = existingEmpList.get(i);
					prevEmp.setArchiveType(1);
					session.update(prevEmp);
				}
			}

			session.save(emp);
			/*PreOnbEmployee preOnbEmp = (PreOnbEmployee) session.createCriteria(PreOnbEmployee.class)
										.add(Restrictions.eq("corpId", emp.getCorpId()))
										.add(Restrictions.eq("resourceStatus", OnboardingConstants.statusRMApproved)).uniqueResult();
			*/
			
			Query query =  session.createNamedQuery("getRMApprovedEmp");
			query.setParameter("corpID",emp.getCorpId());
			PreOnbEmployee preOnbEmp = null;
			List list = query.list();
			if(list != null && !list.isEmpty()) {
				preOnbEmp = (PreOnbEmployee)list.get(0);
				if(preOnbEmp.isPSAIdReq()) {
					preOnbEmp.setResourceStatus(OnboardingConstants.statusBISPMOSubmit);
				}else {
					preOnbEmp.setResourceStatus(OnboardingConstants.statusRMPMOSubmit);
				}
				
				preOnbEmp.setBISPMOSubmitDT(new Date());
				session.update(preOnbEmp);
			}
			
			tx.commit();
		}
		catch(Exception e) {
			logger.info(e.getMessage());
			logger.error(" Some Problem occurs :- "+e.getMessage());
			tx.rollback();
			throw e;
		}
		finally {
			if(session != null && session.isOpen()) {
				session.close();
			}
		}
		
		}

		//Mohini added end
		
		@Override
		public PreOnbEmployee getPreOnbEmployee(String corpId) {
			
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			try {
				Criteria criteria = session.createCriteria(PreOnbEmployee.class);
				criteria.add(Restrictions.eq("corpId", corpId));
				criteria.add(Restrictions.isNull("archiveType"));
				PreOnbEmployee preOnbEmp = (PreOnbEmployee) criteria.uniqueResult();
				session.close();
				return preOnbEmp;
			}catch(Exception e) { //NonUniqueResultException
				logger.info(e.getMessage());
				tx.rollback();
				session.close();
				return null;
			}
			
		}
		
		
		@Override
		public Employee getPreOnbEmployees(String corpId) {
			

			Employee emp = new Employee();
			//String s1 = "I have (1000)";
		  
		   // if(sp!=null ) {
		   // String sps=sp[2];

		   // sps=sps.replace("(", "");
		    //sps=sps.replace(")", "");
		  emp = empdao.buddyIds(corpId);
			
		     
			
		
			
			return emp;
		    }
			
		
			
			
		
		
		@SuppressWarnings("unchecked")
		@Override
		public List<PreOnbEmployee> preOnboardingSearch(Employee emp, String roleId) {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(PreOnbEmployee.class);
			String empId = emp.getEmpId();
			String corpId = emp.getCorpId();
			String firstName = emp.getFirstName();
			String lastName = emp.getLastName();
			emp.setArchiveType(null);
			
			if(emp.getResourceStatus() != null) {
				if(emp.getResourceStatus().equalsIgnoreCase("EM Submitted")) {
					emp.setResourceStatus("EMSubmitted");
				}else if(emp.getResourceStatus().equalsIgnoreCase("RM Approved")) {
					emp.setResourceStatus("RMApproved");
				}else if(emp.getResourceStatus().equalsIgnoreCase("RM Rejected")) {
					emp.setResourceStatus("RMRejected");
				}else if(emp.getResourceStatus().equalsIgnoreCase("BIS-PMO Submitted")) {
					emp.setResourceStatus("BISPMOSubmitted");
				}else if(emp.getResourceStatus().equalsIgnoreCase("Onboarding Completed")) {
					emp.setResourceStatus("OnboardingCompleted");
				}else if(emp.getResourceStatus().equalsIgnoreCase("Onboarding Initiated")) {
					emp.setResourceStatus("OnboardingInitiated");
				}
				
			}
			
			String res_status = emp.getResourceStatus();
			
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
			if(res_status != null && !res_status.equalsIgnoreCase("")) {
				criteria.add(Restrictions.ilike("resourceStatus", res_status, MatchMode.ANYWHERE));
			}

			criteria.add(Restrictions.ne("id", new Long(0)));
			
			if (roleId.equalsIgnoreCase(OnboardingConstants.UserManagement)) {
				criteria.add(Restrictions.eq("resourceStatus", OnboardingConstants.statusRMApproved));
				//criteria.add(Restrictions.eq("resourceStatus", OnboardingConstants.statusRMApproved));
			}
			else if(roleId.equalsIgnoreCase(OnboardingConstants.RM_PMO)){
				
				criteria.add(Restrictions.or(Restrictions.eq("resourceStatus", OnboardingConstants.statusBISPMOSubmit),
						Restrictions.eq("resourceStatus", OnboardingConstants.statusRMPMOSubmit)));
			}
		//	logger.info("added  distinct ");
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List list = criteria.list();
			tx.commit();
			return list;
		}
		catch(Exception e) {
			logger.info(e.getMessage());
			tx.rollback();
			throw e;
			//return null;
		}
		finally {
			if(session != null && session.isOpen()) {
			session.close();
			}
		}
	}
		
		
		public RmPMO getRmPmoDetails() {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			RmPMO rmPmo = new RmPMO();
			try {
			
		List list =  session.createCriteria(RmPMO.class).list();
				if(list != null && !list.isEmpty()) {
					rmPmo = (RmPMO) list.get(0);
				}
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
			return rmPmo;
		} 
		
		public JiraIDCreator getJiraCreatorDetails() {
			Session session = this.sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			JiraIDCreator jiraCreator = new JiraIDCreator();
			try {
			
		List list =  session.createCriteria(JiraIDCreator.class).list();
				if(list != null && !list.isEmpty()) {
					jiraCreator = (JiraIDCreator) list.get(0);
				}
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
			return jiraCreator;
		}
		@Override
		public List<Role> roleList(String username) {
			//List<Role> roleList = null;
			List<Role> user = null;
			try {
			//List roleList = new ArrayList<>();
			
			Session session = this.sessionFactory.openSession();

			Criteria criteria = session.createCriteria(UserRoles.class);
			criteria.setProjection(Projections.projectionList().add(Projections.property("role_id")));
			criteria.add(Restrictions.eq("userName", username));
			user = (ArrayList<Role>) criteria.list(); // check this is integer or string
			/*Criteria criteriaRole = session.createCriteria(Role.class);
			criteriaRole.add(Restrictions.in("role_id", user));
			roleList = (ArrayList<Role>) criteriaRole.list();*/
			/*String queryString = OnBoardingSQLQueries.roleMappingQuery +username +"')";
			TypedQuery<Employee> query = session.createSQLQuery(queryString);
			TypedQuery query = session.createSQLQuery(queryString);
			roleList = query.getResultList();*/
			session.close();
			
			}
			catch(Exception e) {
				logger.info(e.getMessage());
				logger.error(" Some Problem occurs :- "+e.getMessage());
				
			}
			return user;//roleList;
		}
		
		@Override
		public Boolean isFirstLogin(String username) {
			
			Session session = this.sessionFactory.openSession();

			Criteria criteria = session.createCriteria(Users.class);
			criteria.add(Restrictions.eq("userName", username));
			Users user = (Users) criteria.uniqueResult();
			return user.getIsFirstLogin();		
		}

		@Override
		public List<Role> roleList() {
			
			List<Role> user = null;
			try {
				
				Session session = this.sessionFactory.openSession();
	
				Criteria criteria = session.createCriteria(Role.class);
				//criteria.setProjection(Projections.projectionList().add(Projections.property("role_id")));
				user = (ArrayList<Role>) criteria.list();
				session.close();
			}
			catch(Exception e) {
				logger.info(e.getMessage());
				logger.error(" Some Problem occurs :- "+e.getMessage());
			}
			return user;
			
		}

		@Override
		public DLList getDLListbyRole(String role) {

			Session session = this.sessionFactory.openSession();
			Criteria criteria = session.createCriteria(DLList.class);
			criteria.add(Restrictions.eq("role", role));
			DLList dlDetails = (DLList) criteria.uniqueResult();
			return dlDetails;
		}

		@Override
		public List<DLList> getMultipleDLListbyRole(String role) {
			
			Session session = this.sessionFactory.openSession();
			Criteria criteria = session.createCriteria(DLList.class);
			criteria.add(Restrictions.eq("role", role));
			List<DLList> dlDetails = new ArrayList<>();
			dlDetails = criteria.list();
			return dlDetails;
		}


		@Override
		public List<PreOnbEmployee> searchPreOnbEmployees(PreOnbEmployee emp) {
			Session session = this.sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(PreOnbEmployee.class);
			//String empId = emp.getEmpId();
			String corpId = emp.getCorpId();
			String firstName = emp.getFirstName();
			String lastName = emp.getLastName();
			
			 /* if(empId!= null ){ criteria.add(Restrictions.eq("empId", empId)); }
			 
			if (empId != null && !empId.equalsIgnoreCase("")) {
				criteria.add(Restrictions.ilike("empId", empId, MatchMode.ANYWHERE));
			}*/
			if (corpId != null && !corpId.equalsIgnoreCase("")) {
				criteria.add(Restrictions.ilike("corpId", corpId, MatchMode.ANYWHERE));
			}
			if (firstName != null && !firstName.equalsIgnoreCase("")) {
				criteria.add(Restrictions.ilike("firstName", firstName, MatchMode.ANYWHERE));
			}
			if (lastName != null && !lastName.equalsIgnoreCase("")) {
				criteria.add(Restrictions.ilike("lastName", lastName, MatchMode.ANYWHERE));
			}

			criteria.add(Restrictions.ne("id", new Long(0)));

			return criteria.list();
			
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
			Collections.sort(managerList);

			return managerList;
		}

		//new method for onboarding request report
		@Override
		public List<PreOnbEmployee> searchPreOnbEmpReport(PreOnbEmployee emp, Integer limit) {
		    Session session = null;
		    List<PreOnbEmployee> listEmployees = new ArrayList<>();
		    
		    try {
		        session = this.sessionFactory.openSession();
		        String queryString = "select emp from PreOnbEmployee emp where id != 0 ";
		        String empType = emp.getEmpType();
		        
		        if (emp.getCountry() != null) {
		            queryString += " and emp.country.id = :countryId";
		        }
		        
		        if (!OnboardingConstants.ALL.equalsIgnoreCase(empType)) {
		        	queryString += " and emp.empType = :empType";
		            //queryString += " and emp.empType in :empType";
		        }
		        
		        if (emp.getBis() != null) {
		            queryString += " and emp.bis.id = :bisId";
		        }

		        TypedQuery<PreOnbEmployee> query = session.createQuery(queryString, PreOnbEmployee.class);

		        // Setting parameters if available
		        if (emp.getCountry() != null) {
		            query.setParameter("countryId", emp.getCountry().getCountryId());
		        }
		        if (!OnboardingConstants.ALL.equalsIgnoreCase(empType)) {
		            query.setParameter("empType", empType);
		        }
		        if (emp.getBis() != null) {
		            query.setParameter("bisId", emp.getBis().getBis_id());
		        }

		        // If a limit is provided, apply it
		        if (limit != null) {
		            query.setMaxResults(limit);
		        }

		        listEmployees = query.getResultList();
		    } catch (Exception e) {
		        logger.error("Some problem occurs: " + e.getMessage(), e);
		    } finally {
		        if (session != null) {
		            session.close();
		        }
		    }

		    return listEmployees;
		}
		//end
}
