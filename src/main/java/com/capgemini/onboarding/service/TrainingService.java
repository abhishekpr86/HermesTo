package com.capgemini.onboarding.service;

import java.util.List;

import com.capgemini.onboarding.model.Training;

public interface TrainingService {

	public void addTraining(Training training);
	public List<Training> getTrainingList(int gradeId);
	public Training getNextTraining(String empId);
	public List<Training> getEmpTrainingList(String empId);
	/*public void updateTraining(Training p);
	public List<Training> listTraining();
	public Training getTrainingById(int id);
	public void removeTraining(int id);*/
}