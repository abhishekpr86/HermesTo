package com.capgemini.onboarding.dao;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.model.Spoc;

@Repository
public class SpocDaoImpl implements SpocDao {

	private static final Logger logger = LoggerFactory.getLogger(SpocDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Spoc fetchSpoc(int countryId) {
		Session session = this.sessionFactory.getCurrentSession();
		Spoc spoc = null;
		TypedQuery<Spoc> query = session.createNamedQuery("SpocByCountry");		
		query.setParameter("countryId", countryId);
		if(query.getResultList() != null && query.getResultList().size() > 0) {
			spoc = query.getResultList().get(0);
		}
		return spoc;
	}

	@Override
	public Spoc getSpocById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Spoc spoc = (Spoc) session.load(Spoc.class, new Integer(id));
		logger.info("spoc loaded successfully, state details=" + spoc);
		return spoc;
	}

}
