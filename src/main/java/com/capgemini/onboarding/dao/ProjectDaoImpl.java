package com.capgemini.onboarding.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.model.Project;
import com.capgemini.onboarding.model.RoleTech;

@Repository
public class ProjectDaoImpl implements ProjectDao {

	private static final Logger logger = LoggerFactory.getLogger(ProjectDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Project> listProject() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Project> projectList = session.createQuery("from Project").list();
		
		for(Project project : projectList){
		logger.info("projectList List :: " + project );
		}
		
		Collections.sort(projectList);
		return projectList;
	}

	@Override
	public Project getProjectById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Project.class);
		criteria.add(Restrictions.eq("projectId", id));
		Project project = (Project) criteria.uniqueResult();
				
		return project;
	}

}
