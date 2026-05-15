package com.capgemini.onboarding.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.model.GlobalGrade;
import com.capgemini.onboarding.model.Grade;

@Repository
public class GlobalGradeDaoImpl implements GlobalGradeDao {

	private static final Logger logger = LoggerFactory.getLogger(GlobalGradeDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<GlobalGrade> listGlobalGrades() {
		Session session = this.sessionFactory.getCurrentSession();
		List<GlobalGrade> globalGradeList = session.createQuery("from GlobalGrade").list();
		//for(GlobalGrade globalGrade : globalGradeList){
			//logger.info("globalGrade List::"+globalGrade);
		//}
		return globalGradeList;
	}

	@Override
	public GlobalGrade getGlobalGradeById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		GlobalGrade globalGrade = (GlobalGrade) session.load(GlobalGrade.class, new Integer(id));
		logger.info("Grade loaded successfully, Grade details=" + globalGrade);
		return globalGrade;
	}

	@Override
	public GlobalGrade getGlobalGradeByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();		
		Criteria criteria = session.createCriteria(GlobalGrade.class);
		GlobalGrade gradeExist = null;
		criteria.add(Restrictions.like("name", name));
		if(criteria.uniqueResult() != null) {
			gradeExist = (GlobalGrade) criteria.uniqueResult();
		}
		return gradeExist;
	}

}
