package com.capgemini.onboarding.dao;

import java.util.List;

import com.capgemini.onboarding.model.Project;



public interface ProjectDao {

	public List<Project> listProject();
	public Project getProjectById(int id);
	
}
