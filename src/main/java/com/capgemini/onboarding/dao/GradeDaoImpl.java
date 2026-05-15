package com.capgemini.onboarding.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.model.Grade;

@Repository
public class GradeDaoImpl implements GradeDao {

	private static final Logger logger = LoggerFactory.getLogger(GradeDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Grade> listGrades(int countryId) {
		Session session = this.sessionFactory.getCurrentSession();
		TypedQuery<Grade> query = session.createNamedQuery("listGradeByCountry");		
		query.setParameter("countryId", countryId);
		List<Grade> listGrades = query.getResultList();
		Collections.sort(listGrades);
		return listGrades;	
	}
	
	@Override
	public Grade getGradeById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Grade grade = (Grade) session.load(Grade.class, new Integer(id));
		logger.info("Grade loaded successfully, Grade details=" + grade);
		return grade;
		
	}

}
