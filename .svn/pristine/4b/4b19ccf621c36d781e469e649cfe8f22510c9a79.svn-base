package com.capgemini.onboarding.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.model.Training;


@Repository
public class TrainingDaoImpl implements TrainingDAO {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public void addTraining(Training training) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(training);
		logger.info("Training details saved successfully, Training Details="+training);
	}
	
	public List<Training> getTrainingList(int gradeId){
		Session session = this.sessionFactory.getCurrentSession();
		TypedQuery<Training> query = session.createNamedQuery("listTrainings");		
		query.setParameter("gradeId", gradeId);
		return query.getResultList();
	}

	@Override
	public Training getNextTraining(String empId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("displayTrainings");		
		query.setParameter("empId", empId);
		List<Training> trainingList = query.getResultList();
		Training training = null;
		if(trainingList!=null && !trainingList.isEmpty()) {
			training = trainingList.get(0);
		}
		return training;				
	}
	
	@Override
	public List<Training> getEmpTrainingList(String empId) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("displayTrainings");		
		query.setParameter("empId", empId);
		List<Training> trainingList = query.getResultList();
		return trainingList;	
	}

}
