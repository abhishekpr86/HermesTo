package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.onboarding.model.Project;

@Service
public interface ProjectService {

	public List<Project> listProject();
	
	public Project getProjectById(int id);
	
}
