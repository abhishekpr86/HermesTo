package com.capgemini.onboarding.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.model.DemandType;

@Repository
public class DemandTypeDaoImpl implements DemandTypeDao {

	
	private static final Logger logger = LoggerFactory.getLogger(DemandTypeDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<DemandType> demandTypeList() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(DemandType.class);
		List<DemandType> demandList = criteria.list();
		return demandList;
	}

	@Override
	public DemandType getDemandTypeById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(DemandType.class);
		Criterion idCriterion = Restrictions.eq("id", id);
		criteria.add(idCriterion);
		DemandType demandType = (DemandType) criteria.uniqueResult();
		return demandType;
	}

}
