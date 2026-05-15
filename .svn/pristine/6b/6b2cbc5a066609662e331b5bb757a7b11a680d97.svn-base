package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.TrainingDAO;
import com.capgemini.onboarding.model.Training;

@Service
public class TrainingServiceImpl implements TrainingService {
	
	@Autowired
	private TrainingDAO trainingDAO;

	@Override
	@Transactional
	public void addTraining(Training training) {
		this.trainingDAO.addTraining(training);
	}
	
	@Override
	@Transactional
	public List<Training> getTrainingList(int gradeId) {
		return this.trainingDAO.getTrainingList(gradeId);
	}

	@Override
	@Transactional
	public Training getNextTraining(String empId) {
		return this.trainingDAO.getNextTraining(empId);
	}

	@Override
	@Transactional
	public List<Training> getEmpTrainingList(String empId) {
		return this.trainingDAO.getEmpTrainingList(empId);
	}

}
