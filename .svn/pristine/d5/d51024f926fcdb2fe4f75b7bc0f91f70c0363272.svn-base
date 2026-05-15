package com.capgemini.onboarding.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.EmployeeTrainingDAO;

@Service
public class EmployeeTrainingServiceImpl implements EmployeeTrainingService {
	
	@Autowired
	private EmployeeTrainingDAO employeeTrainingDAO;

	@Override
	@Transactional
	public void updateAttendedDate(Date attendedDate, Integer trainingId, String empId) {
		employeeTrainingDAO.updateAttendeddate(attendedDate, trainingId, empId);
		
	}
}
