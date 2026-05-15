package com.capgemini.onboarding.dao;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.criteria.internal.compile.CriteriaQueryTypeQueryAdapter;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.dto.MoodleEnrolDataDTO;
import com.capgemini.onboarding.model.Bis;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.moodle.model.MoodleCohortMembers;
import com.capgemini.moodle.model.User;

@Repository
public class MoodleDaoImpl implements MoodleDao {

	private static final Logger logger = LoggerFactory.getLogger(MoodleDaoImpl.class);
	
	@Autowired
	private SessionFactory moodleSessionFactory;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addUser(Employee emp, String auth) {
		
		Session session = this.moodleSessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			
			User moodleUser = getUserByUsername(emp.getCorpId().toLowerCase());
			if(moodleUser == null) {
				moodleUser = new User();
				moodleUser.setUsername(emp.getCorpId().toLowerCase());
				moodleUser.setFirstName(emp.getFirstName());
				moodleUser.setLastName(emp.getLastName());
				moodleUser.setEmail(emp.getEmail());
				moodleUser.setCity(emp.getLocation().getStateName());
				session.save(moodleUser);
				System.out.println("User added to Moodle ");
			}else {
				moodleUser.setDeleted(0);
				session.saveOrUpdate(moodleUser);
				System.out.println("Existing User updated to Moodle by deleted field 0");
			}
			 
			tx.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
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
	public User getUserByUsername(String username) {
		
		Session session = this.moodleSessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		User user = (User) criteria.uniqueResult();
		tx.commit();
		if(session != null && session.isOpen()) {
			session.close();
		}
		return user;
	}


	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		Session session = this.moodleSessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			user.setDeleted(1);
			session.saveOrUpdate(user);
			tx.commit();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			logger.info(e.getMessage());
			logger.error(" Some Problem occurs :- "+e.getMessage());
			tx.rollback();
		}finally {
			if(session != null && session.isOpen()) {
				session.close();
			}
		}
		
	}
	
	@Override
	public void activateUser(User user) {//To activate user in moodle

		Session session = this.moodleSessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			user.setDeleted(0);
			session.saveOrUpdate(user);
			tx.commit();
			logger.info("user activated in moodle"+user.getUsername());
		}catch(Exception e) {
			System.out.println(e.getMessage());
			logger.info(e.getMessage());
			logger.error(" Some Problem occurs :- "+e.getMessage());
			tx.rollback();
		}finally {
			if(session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public List<MoodleEnrolDataDTO> fetchUSerEnrolments(MoodleEnrolDataDTO dto){
		Session session = this.moodleSessionFactory.openSession();
		Session onboardingSession =  this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Transaction onbTx = onboardingSession.beginTransaction();
	  try {
		String hqlQuery = "SELECT new com.capgemini.onboarding.dto.MoodleEnrolDataDTO(u.username,u.firstName as firstName,u.lastName, c.fullname as coursename, ue.timecreated as userEnrolDate, cc.timecompleted as courseCompletionDate, " + 
				"CASE When cc.timecompleted is null then 'PENDING' else 'COMPLETED' END as status) " + 
				"FROM UserEnrolments ue " + 
				"inner join MoodleEnrolment e on ue.enrolid=e.id " + 
				"inner join User u on ue.userid=u.id " + 
				"inner join MoodleCourse c on e.courseid=c.id " + 
				"left join CourseCompletion cc on (cc.userid=u.id and cc.course=c.id) ";
		if(dto.getUsername() == null) {
			
			
		}else if(!dto.getUsername().isEmpty()) {
			hqlQuery = hqlQuery+"where u.username='"+dto.getUsername()+"'";
		}
		
		//TypedQuery<MoodleEnrolDataDTO> query = session.createQuery(hqlQuery, MoodleEnrolDataDTO.class);
		logger.info(hqlQuery);
		List<MoodleEnrolDataDTO> results =  session.createQuery(hqlQuery, MoodleEnrolDataDTO.class).getResultList();
		
		logger.info("Moodle join query data " +results.size());
		
//		Map<String, Tuple> postCountByYearMap = onboardingSession.createQuery("select lower(e.corpId) as corp_Id, e.joiningDate as onbDate,e.actualEndDate as offDate, b.bis_Name as bisname, Manager.firstName as MFN, Manager.lastName as MLN, em.firstName as FN, em.lastName as LN, bem.bundleEmName as BEM, e.email as Email, e.managerEmail as MEmail, pp.primaryProgramName as ppName from Employee e "
		String onbHqlQuery = "select lower(e.corpId) as corp_Id, e.joiningDate as onbDate,e.actualEndDate as offDate, b.bis_Name as bisname, Manager.firstName as MFN, Manager.lastName as MLN, em.firstName as FN, em.lastName as LN, bem.bundleEmName as BEM, e.email as Email, e.managerEmail as MEmail, pp.primaryProgramName as ppName from Employee e "
				+"left join Bis b on b.bis_id=e.bis "
				+"left join Employee Manager on Manager.id=e.manager "
				+"left join Employee em on em.id=e.EM "
				+"left join BundleEm bem on bem.bundleEmId=e.bundleEM "
				+"left join PrimaryProgram pp on pp.primaryProgramId=e.primaryprogram "
//				+"where e.archiveType is null and e.corpId not in('')", Tuple.class)
				+"where e.archiveType is null and e.corpId not in('')";
		if("active".equalsIgnoreCase(dto.getResStatus())) {
			onbHqlQuery = onbHqlQuery + " and (e.actualEndDate is null or e.actualEndDate >= CURRENT_DATE)";
		}
		Map<String, Tuple> postCountByYearMap = onboardingSession.createQuery(onbHqlQuery, Tuple.class)
				.getResultList()
				.stream().distinct()
				.collect(
				    Collectors.toMap(
				        Tuple -> ((String) Tuple.get("corp_Id")),
				       
				        Tuple -> (Tuple)
				        
				    )
				); // removed e.endDate >= CURDATE() condition - Valerie
		
		
		
		
		List<MoodleEnrolDataDTO> finalResults = new ArrayList<MoodleEnrolDataDTO>();//results;
		for(int i=0; i < results.size(); i++) {
			if(postCountByYearMap.get(results.get(i).getUsername()) != null) {
				
				results.get(i).setBis((String) postCountByYearMap.get(results.get(i).getUsername()).get("bisname"));
				results.get(i).setEM((String)postCountByYearMap.get(results.get(i).getUsername()).get("FN")+" "+(String)postCountByYearMap.get(results.get(i).getUsername()).get("LN")); 
				results.get(i).setBM((String)postCountByYearMap.get(results.get(i).getUsername()).get("BEM"));
				results.get(i).setOnboardingDate((Date)postCountByYearMap.get(results.get(i).getUsername()).get("onbDate"));
			if(postCountByYearMap.get(results.get(i).getUsername()).get("offDate") != null) {
				results.get(i).setOffboardingDate((Date)postCountByYearMap.get(results.get(i).getUsername()).get("offDate"));
			}
			results.get(i).setManager((String)postCountByYearMap.get(results.get(i).getUsername()).get("MFN")+" "+(String)postCountByYearMap.get(results.get(i).getUsername()).get("MLN"));
			results.get(i).setUserEmail((String)postCountByYearMap.get(results.get(i).getUsername()).get("Email"));
			results.get(i).setManagerEmail((String)postCountByYearMap.get(results.get(i).getUsername()).get("MEmail")); 
			results.get(i).setPrimaryProgram((String)postCountByYearMap.get(results.get(i).getUsername()).get("ppName"));
			finalResults.add(results.get(i));
			}
			else {
				//finalResults.remove(i);
				
			}
		}
			
		
		return finalResults;
	  }
	  catch(Exception e) {
		  System.out.println(e.getMessage());
		  e.printStackTrace();
			logger.info(e.getMessage());
			logger.error(" Some Problem occurs :- "+e.getMessage());
			tx.rollback();
			return null;
	  }
	  finally {
			if(session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	

	@Override
	@ManyToMany(fetch = FetchType.EAGER)
	public List<MoodleEnrolDataDTO> fetchUSerEnrolmentsData(MoodleEnrolDataDTO dto){
		Session session = this.moodleSessionFactory.openSession();
		Session onboardingSession =  this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Transaction onbTx = onboardingSession.beginTransaction();
	  try {
		String hqlQuery = "SELECT new com.capgemini.onboarding.dto.MoodleEnrolDataDTO(u.username,u.firstName as firstName,u.lastName, c.fullname as coursename, ue.timecreated as userEnrolDate, cc.timecompleted as courseCompletionDate, " + 
				"CASE When cc.timecompleted is null then 'PENDING' else 'COMPLETED' END as status) " + 
				"FROM UserEnrolments ue " + 
				"inner join MoodleEnrolment e on ue.enrolid=e.id " + 
				"inner join User u on ue.userid=u.id " + 
				"inner join MoodleCourse c on e.courseid=c.id " + 
				"left join CourseCompletion cc on (cc.userid=u.id and cc.course=c.id) ";
		
		
		//TypedQuery<MoodleEnrolDataDTO> query = session.createQuery(hqlQuery, MoodleEnrolDataDTO.class);
		System.out.println(hqlQuery);
		List<MoodleEnrolDataDTO> results =  session.createQuery(hqlQuery, MoodleEnrolDataDTO.class).getResultList();
		
		System.out.println("Moodle join query data " +results.size());
		Map<String, Tuple> postCountByYearMap = onboardingSession.createQuery("select lower(e.corpId) as corp_Id, e.joiningDate as onbDate,e.actualEndDate as offDate, b.bis_Name as bisname, Manager.firstName as MFN, Manager.lastName as MLN, em.firstName as FN, em.lastName as LN, bem.bundleEmName as BEM, e.email as Email, e.managerEmail as MEmail, pp.primaryProgramName as ppName from Employee e "
				+"left join Bis b on b.bis_id=e.bis "
				+"left join Employee Manager on Manager.id=e.manager "
				+"left join Employee em on em.id=e.EM "
				+"left join BundleEm bem on bem.bundleEmId=e.bundleEM "
				+"left join PrimaryProgram pp on pp.primaryProgramId=e.primaryprogram "
				+"where e.archiveType is null and e.corpId not in('')", Tuple.class)
				.getResultList()
				.stream().distinct()
				.collect(
				    Collectors.toMap(
				        Tuple -> ((String) Tuple.get("corp_Id")),
				        Tuple -> (Tuple)
				    )
				    
				    
				); // removed e.endDate >= CURDATE() condition - Valerie
		List<MoodleEnrolDataDTO> finalResults = new ArrayList<MoodleEnrolDataDTO>();//results;
		for(int i=0; i < results.size(); i++) {
			if(postCountByYearMap.get(results.get(i).getUsername()) != null) {
				
				results.get(i).setBis((String) postCountByYearMap.get(results.get(i).getUsername()).get("bisname"));
				results.get(i).setEM((String)postCountByYearMap.get(results.get(i).getUsername()).get("FN")+" "+(String)postCountByYearMap.get(results.get(i).getUsername()).get("LN")); 
				results.get(i).setBM((String)postCountByYearMap.get(results.get(i).getUsername()).get("BEM"));
				results.get(i).setOnboardingDate((Date)postCountByYearMap.get(results.get(i).getUsername()).get("onbDate"));
			if(postCountByYearMap.get(results.get(i).getUsername()).get("offDate") != null) {
				results.get(i).setOffboardingDate((Date)postCountByYearMap.get(results.get(i).getUsername()).get("offDate"));
			}
			results.get(i).setManager((String)postCountByYearMap.get(results.get(i).getUsername()).get("MFN")+" "+(String)postCountByYearMap.get(results.get(i).getUsername()).get("MLN"));
			results.get(i).setUserEmail((String)postCountByYearMap.get(results.get(i).getUsername()).get("Email"));
			results.get(i).setManagerEmail((String)postCountByYearMap.get(results.get(i).getUsername()).get("MEmail")); 
			results.get(i).setPrimaryProgram((String)postCountByYearMap.get(results.get(i).getUsername()).get("ppName"));
			finalResults.add(results.get(i));
			}
			else {
				//finalResults.remove(i);
				
			}
		}
			
		
		
			
		
		return finalResults;
	  }
	  catch(Exception e) {
		  System.out.println(e.getMessage());
		  e.printStackTrace();
			logger.info(e.getMessage());
			logger.error(" Some Problem occurs :- "+e.getMessage());
			tx.rollback();
			return null;
	  }
	  finally {
			if(session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	

	
	@Override
	public List<MoodleEnrolDataDTO> fetchMailUserEnrolments(MoodleEnrolDataDTO dto){
		Session session = this.moodleSessionFactory.openSession();
		Session onboardingSession =  this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
	  try {
		String hqlQuery = "SELECT new com.capgemini.onboarding.dto.MoodleEnrolDataDTO(u.username,u.firstName as firstName,u.lastName, c.fullname as coursename, ue.timecreated as userEnrolDate, cc.timecompleted as courseCompletionDate, " + 
				"CASE When cc.timecompleted is null then 'PENDING' else 'COMPLETED' END as status) " + 
				"FROM UserEnrolments ue " + 
				"inner join MoodleEnrolment e on ue.enrolid=e.id " + 
				"inner join User u on ue.userid=u.id " + 
				"inner join MoodleCourse c on e.courseid=c.id " + 
				"left join CourseCompletion cc on (cc.userid=u.id and cc.course=c.id) ";
		if(dto.getUsername() == null) {
			
		}else if(!dto.getUsername().isEmpty()) {
			hqlQuery = hqlQuery+"where u.username='"+dto.getUsername()+"'";
		}

		List<MoodleEnrolDataDTO> results =  session.createQuery(hqlQuery, MoodleEnrolDataDTO.class).getResultList();
		
		System.out.println("Moodle join query data " +results.size());
		
		Map<String, Tuple> postCountByYearMap = onboardingSession.createQuery("select lower(e.corpId) as corp_Id, e.joiningDate as onbDate, b.bis_Name as bisname, Manager.firstName as MFN, Manager.lastName as MLN, em.firstName as FN, em.lastName as LN, bem.bundleEmName as BEM, e.email as Email, e.managerEmail as MEmail, pp.primaryProgramName as ppName from Employee e "
				+"left join Bis b on b.bis_id=e.bis "
				+"left join Employee Manager on Manager.id=e.manager "
				+"left join Employee em on em.id=e.EM "
				+"left join BundleEm bem on bem.bundleEmId=e.bundleEM "
				+"left join PrimaryProgram pp on pp.primaryProgramId=e.primaryprogram "
				+"where e.archiveType is null and ((e.actualEndDate >= CURDATE()) or (e.actualEndDate is null and e.endDate >= CURDATE())) and e.corpId not in('')", Tuple.class)
				.getResultList()
				.stream().distinct()
				.collect(
				    Collectors.toMap(
				        Tuple -> ((String) Tuple.get("corp_Id")),
				        Tuple -> (Tuple)
				    )
				);
		List<MoodleEnrolDataDTO> finalResults = results;
		for(int i=0; i < results.size(); i++) {
			if(postCountByYearMap.get(results.get(i).getUsername()) != null) {
			finalResults.get(i).setBis((String) postCountByYearMap.get(results.get(i).getUsername()).get("bisname"));
			finalResults.get(i).setEM((String)postCountByYearMap.get(results.get(i).getUsername()).get("FN")+" "+(String)postCountByYearMap.get(results.get(i).getUsername()).get("LN")); 
			finalResults.get(i).setBM((String)postCountByYearMap.get(results.get(i).getUsername()).get("BEM"));
			finalResults.get(i).setOnboardingDate((Date)postCountByYearMap.get(results.get(i).getUsername()).get("onbDate"));
			finalResults.get(i).setManager((String)postCountByYearMap.get(results.get(i).getUsername()).get("MFN")+" "+(String)postCountByYearMap.get(results.get(i).getUsername()).get("MLN"));
			finalResults.get(i).setUserEmail((String)postCountByYearMap.get(results.get(i).getUsername()).get("Email"));
			finalResults.get(i).setManagerEmail((String)postCountByYearMap.get(results.get(i).getUsername()).get("MEmail")); 
			finalResults.get(i).setPrimaryProgram((String)postCountByYearMap.get(results.get(i).getUsername()).get("ppName"));
			}
		}
		logger.info("moodle reminder list size " + finalResults.size()+" " +postCountByYearMap.size());
		return finalResults;
	  }
	  catch(Exception e) {
		  System.out.println(e.getMessage());
		  e.printStackTrace();
			logger.info(e.getMessage());
			logger.error(" Some Problem occurs :- "+e.getMessage());
			tx.rollback();
			return null;
	  }
	  finally {
			if(session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	@Override
	public List<Employee> getPendingEnrolmentResource(){
		Session session,moodleSession;
		try {
			session = this.sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		try {
			moodleSession = this.moodleSessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			moodleSession = moodleSessionFactory.openSession();
		}
		
		try {
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.add(Restrictions.isNull("welcomeEmailFlag"));
			
			LocalDate todayMinusTen = LocalDate.now().minusDays(10); 
			Instant instant = todayMinusTen.atStartOfDay(ZoneId.systemDefault()).toInstant();	
			Date bispmots = Date.from(instant);
			//DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			List<Employee> empList = criteria.list();
			
			long dateGreaterThan = instant.toEpochMilli()/1000;
			logger.info("dateGreaterThan " +dateGreaterThan+ " "+dateGreaterThan/1000+" - "+empList.size());
			
			String hqlQuery = "SELECT distinct(u.username) as username " + 
					"FROM UserEnrolments ue " + 
					"inner join User u on ue.userid=u.id " + 
					"where ";
			
			hqlQuery = hqlQuery+"u.username in ('',";
			for(int i=0; i<empList.size(); i++) {
				if(empList.get(i).getPreonbemp().getBISPMOSubmitDT().after(bispmots)) {
					hqlQuery=hqlQuery+"'"+empList.get(i).getCorpId().toLowerCase()+"',";	
				}
			}
			hqlQuery = hqlQuery.substring(0, hqlQuery.length()-1);
			hqlQuery = hqlQuery+")";
			
			logger.info(hqlQuery);
			Map<String, Tuple> moodleUserEnrolmentResults =  moodleSession.createQuery(hqlQuery, Tuple.class).getResultList()
					.stream().distinct()
					.collect(
					    Collectors.toMap(
					        Tuple -> ((String) Tuple.get("username")),
					        Tuple -> (Tuple)
					    )
					);
			//<String, Employee> 
			
			//List<String> moodleUserEnrolmentResults = 
			logger.info("moodleUserEnrolmentResults - "+moodleUserEnrolmentResults.size()+ " "+moodleUserEnrolmentResults);
			List<Employee> pendingWelcomeEmailList = new ArrayList<Employee>();
			
			for (Employee employee : empList) {
				logger.info("employee username "+moodleUserEnrolmentResults.get(employee.getCorpId().toLowerCase()));
				if(moodleUserEnrolmentResults.get(employee.getCorpId().toLowerCase()) != null) {
					pendingWelcomeEmailList.add(employee);
				}
			}
			
			logger.info("pendingWelcomeEmailList "+pendingWelcomeEmailList.size());
			return pendingWelcomeEmailList;
		}
		catch(Exception e) {
			logger.info("error in moodleDaoimpl - "+e.getMessage());
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<MoodleCohortMembers> fetchCohortMembersList(MoodleEnrolDataDTO dto){
		return null;
	}
}
