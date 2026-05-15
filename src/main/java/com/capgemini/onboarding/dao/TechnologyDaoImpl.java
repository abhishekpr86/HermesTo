package com.capgemini.onboarding.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.model.Technology;

@Repository
public class TechnologyDaoImpl implements TechnologyDao {

	private static final Logger logger = LoggerFactory.getLogger(TechnologyDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Technology> listTechnology() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Technology> technologyList = session.createQuery("from Technology").list();
		//for(Technology technology : technologyList){
			//logger.info("technology List::"+technology);
		//}
		Collections.sort(technologyList);
		return technologyList;
	}

	@Override
	public Technology getTechnologyById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Technology technology = (Technology) session.load(Technology.class, new Integer(id));
		logger.info("technology loaded successfully, technology details=" + technology);
		return technology;
	}

}
