package com.capgemini.onboarding.dao;

import java.util.Date;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.model.EmployeeTraining;

@Repository
public class EmployeeTrainingDAOImpl implements EmployeeTrainingDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeTrainingDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void updateAttendeddate(Date attendedDate, Integer trainingId, String empId) {
		Session session = this.sessionFactory.getCurrentSession();
		TypedQuery<EmployeeTraining> query = session.createNamedQuery("updateAttendedDate");	
		query.setParameter("attendedDate", attendedDate);
		query.setParameter("trainingId", trainingId);
		query.setParameter("empId", empId);
		query.executeUpdate();
		logger.info("Attended Date updated successfully, Attended Date = "+attendedDate);		
	}
	
}