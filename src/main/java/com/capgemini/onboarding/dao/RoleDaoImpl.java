package com.capgemini.onboarding.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.model.Project;
import com.capgemini.onboarding.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

private static final Logger logger = LoggerFactory.getLogger(RoleDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Role> listRole() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Role> roleList = session.createQuery("from Role").list();
		
		for(Role role : roleList){
		logger.info("roleList List :: " + role );
		}
		
		Collections.sort(roleList);
		return roleList;
	}

	@Override
	public Role getRoleById(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		//session.beginTransaction();
		Criteria criteria = session.createCriteria(Role.class);
		criteria.add(Restrictions.eq("role_id", id));
		Role role = (Role) criteria.uniqueResult();
				
		return role;
	}
	
	

}
