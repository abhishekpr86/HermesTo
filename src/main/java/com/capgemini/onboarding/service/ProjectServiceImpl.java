package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.BisDao;
import com.capgemini.onboarding.dao.ProjectDao;
import com.capgemini.onboarding.model.Project;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;
	
	@Override
	@Transactional
	public List<Project> listProject() {

		return this.projectDao.listProject();
		 
	}

	@Override
	@Transactional
	public Project getProjectById(int id) {
		
		return this.projectDao.getProjectById(id);
	}

}
