package com.capgemini.onboarding.dao;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.moodle.model.MoodleCohortMembers;
import com.capgemini.onboarding.constants.OnboardingConstants;
import com.capgemini.onboarding.dto.MoodleEnrolDataDTO;
import com.capgemini.onboarding.model.ResourceCohortMapping;

@Repository
public class ResourceCohortDaoImpl implements ResourceCohortDao {

	private static final Logger logger = LoggerFactory.getLogger(ResourceCohortMapping.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private SessionFactory moodleSessionFactory;
	
	@Override
	public int insert(List<ResourceCohortMapping> cohortList) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		try{
			//Criteria criteria = session.createCriteria(ResourceCohortMapping.class);
			for (ResourceCohortMapping r : cohortList){
				session.save(r);
				
			}
			tx.commit();
			return 1;
		}
		catch(Exception e) {
			logger.info(e.getMessage());
			logger.error(" Some Problem occurs :- "+e.getMessage());
			e.printStackTrace();
			tx.rollback();
			//throw e;
		}finally {
			if(session != null && session.isOpen()) {
				session.close();
			}
		}
		return 0;
	}

	@Override
	public ResourceCohortMapping getCohortMappingById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResourceCohortMapping> getCohortsforRange(Date startDt, Date endDt) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Session moodleSession = this.moodleSessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//default time zone
		ZoneId defaultZoneId = ZoneId.systemDefault();
		
		List<ResourceCohortMapping> resourceCohortMapRecords = new ArrayList<>();
		try {
			
			LocalDate localDate = LocalDate.now();
			localDate = localDate.minusDays(30);
			
			Criteria criteria = session.createCriteria(ResourceCohortMapping.class);
			if(startDt != null && endDt !=null) {
				criteria.add(Restrictions.between("creationDate", startDt, endDt));
			}else if(startDt != null && endDt == null){
				criteria.add(Restrictions.ge("creationDate", startDt));
			}else if(startDt == null && endDt == null) {
				
				Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
				logger.info("Date to be considered: "+date);
				criteria.add(Restrictions.ge("creationDate", date));
			}
			resourceCohortMapRecords = criteria.list();
			logger.info("resourceCohortMapRecords size: "+resourceCohortMapRecords.size());
			//make moodle db call - bhavna
			
			
		    long enrolDate = (localDate.atStartOfDay(defaultZoneId).toInstant().toEpochMilli())/1000;
		    //System.out.println("enrolDate: "+ enrolDate);
		    //logger.info("enrolDate - Bhavna "+ enrolDate);
		    Criteria criteriaCohortMembers = moodleSession.createCriteria(MoodleCohortMembers.class);
		    criteriaCohortMembers.add(Restrictions.ge("enrolmentTime", enrolDate));
		    List<MoodleCohortMembers> corpidList = criteriaCohortMembers.list();
		    logger.info("MoodleCohortMembers List size: "+ corpidList.size()+" and enrolDate: "+enrolDate);
		    
		    Map<String, MoodleCohortMembers> cohortMembersMap = corpidList.stream().collect(Collectors.toMap(x -> (String)(x.getUserid().getUsername()+x.getCohortid().getCohortName()),x -> (MoodleCohortMembers)x ));
		    //logger.info("cohortMembersMap - Bhavna "+cohortMembersMap);
		    List<ResourceCohortMapping> finalResults = new ArrayList<ResourceCohortMapping>();//results;	
		    
		    for(int i=0; i < resourceCohortMapRecords.size(); i++) {
		    	
		    	if(cohortMembersMap.get(resourceCohortMapRecords.get(i).getCorp_id().toLowerCase()+resourceCohortMapRecords.get(i).getCohort()) == null) {
		    		//logger.info("cohort: "+resourceCohortMapRecords.get(i).getCorp_id().toLowerCase()+resourceCohortMapRecords.get(i).getCohort());
		    		//logger.info("combination key - Bhavna "+ resourceCohortMapRecords.get(i).getCorp_id().toLowerCase()+resourceCohortMapRecords.get(i).getCohort());
		    		finalResults.add(resourceCohortMapRecords.get(i));
		    	}
		    	
		    }
		    		
		    
			
			// end
			
			logger.info("returning final result: "+finalResults.size());
			return finalResults;
			//return resourceCohortMapRecords;
		}
		catch(Exception e) {
			logger.info(e.getMessage());
			logger.error(" Some Problem occurs :- "+e.getMessage());
			tx.rollback();
			//throw e;
		}
		finally {
			if(session != null && session.isOpen()) {
				session.close();
			}
		}
		
		return null;
	}

}
