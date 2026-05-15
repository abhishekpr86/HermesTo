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

import com.capgemini.onboarding.model.IndusGoals;
import com.capgemini.onboarding.model.RoleTech;
import com.capgemini.onboarding.dao.RoleTechDao;

@Repository
public class RoleTechDaoImpl implements RoleTechDao {

	private static final Logger logger = LoggerFactory.getLogger(RoleTechDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public RoleTech getRoleTechId(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(RoleTech.class);
		criteria.add(Restrictions.eq("roleTechId", id));
		RoleTech roleTech = (RoleTech) criteria.uniqueResult();
		//logger.info("IndusGoals loaded successfully, IndusGoals details=" + indusGoals);
				
		return roleTech;
	}

	@Override
	public List<RoleTech> listRoleTech() {
		Session session = this.sessionFactory.getCurrentSession();
		List<RoleTech> roleTechList = session.createQuery("from RoleTech where isDeleted is null").list();
		
		for(RoleTech roleTech : roleTechList){
		logger.info("roleTechList List :: " + roleTech );
		}
		
		Collections.sort(roleTechList);
		return roleTechList;
	}

}
