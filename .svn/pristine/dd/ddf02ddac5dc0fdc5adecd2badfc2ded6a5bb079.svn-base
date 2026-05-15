package com.capgemini.onboarding.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.model.IndusGoals;

@Repository
public class IndusGoalsDaoImpl implements IndusGoalsDao {
	
	private static final Logger logger = LoggerFactory.getLogger(IndusGoalsDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public IndusGoals getIndusGoalsId(int id) {
		
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(IndusGoals.class);
		criteria.add(Restrictions.eq("indusGoalId", id));
		IndusGoals indusGoals = (IndusGoals) criteria.uniqueResult();
		//logger.info("IndusGoals loaded successfully, IndusGoals details=" + indusGoals);
				
		return indusGoals;
		
	}

	@Override
	public List<IndusGoals> listIndusGoals() {
		Session session = this.sessionFactory.getCurrentSession();
		List<IndusGoals> indusGoalsList = session.createQuery("from IndusGoals").list();
		
		for(IndusGoals indusGoals : indusGoalsList){
		logger.info("indusGoalsList List :: " + indusGoals );
		}
		
		Collections.sort(indusGoalsList);
		return indusGoalsList;
	}
	
	@Override
	public List<IndusGoals> listIndusGoalsPreOnb(){
		Session session = this.sessionFactory.getCurrentSession();
		List<IndusGoals> indusGoalsList = session.createQuery("from IndusGoals where isDeleted is null").list();
		
		for(IndusGoals indusGoals : indusGoalsList){
		logger.info("indusGoalsList List :: " + indusGoals );
		}
		
		Collections.sort(indusGoalsList);
		return indusGoalsList;
	}

}
