package com.capgemini.onboarding.dao;

import java.util.List;

import com.capgemini.onboarding.model.Training;

public interface TrainingDAO {

	public void addTraining(Training training);
	public List<Training> getTrainingList(int gradeId);
	public Training getNextTraining(String empId);
	public List<Training> getEmpTrainingList(String empId);
	/*public void updatePerson(Employee p);
	public List<Employee> listPersons();
	public Employee getPersonById(int id);
	public void removePerson(int id);*/
	
}
