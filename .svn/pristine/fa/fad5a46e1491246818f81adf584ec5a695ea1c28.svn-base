package com.capgemini.onboarding.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.capgemini.onboarding.model.CfaoGroup;

@Repository
public class CfaoDaoImpl implements CfaoDao{

	private static final Logger logger = LoggerFactory.getLogger(CfaoDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public CfaoGroup getCfaoId(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(CfaoGroup.class);
		criteria.add(Restrictions.eq("cfaoId", id));
		CfaoGroup cfao = (CfaoGroup) criteria.uniqueResult();
		logger.info("CFAO loaded successfully, CFAO details=" + cfao);
				
		return cfao;
	}

	@Override
	public List<CfaoGroup> listCfao() {
		Session session = this.sessionFactory.getCurrentSession();
		List<CfaoGroup> cfao = session.createQuery("from CfaoGroup where isDeleted is null").list();
		
		logger.info("CFAO List :: " + cfao.size() );

		return cfao;
	}

}
