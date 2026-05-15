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

import com.capgemini.onboarding.model.BusinessApplicationService;

@Repository
public class BasDaoImpl implements BasDao{

	private static final Logger logger = LoggerFactory.getLogger(BasDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public BusinessApplicationService getBasId(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		//session.beginTransaction();
		Criteria criteria = session.createCriteria(BusinessApplicationService.class);
		criteria.add(Restrictions.eq("basId", id));
		BusinessApplicationService bas = (BusinessApplicationService) criteria.uniqueResult();
		logger.info("BusinessApplicationService loaded successfully, BAS details=" + bas);
				
		return bas;
	}

	@Override
	public List<BusinessApplicationService> listBas() {
		Session session = this.sessionFactory.getCurrentSession();
		List<BusinessApplicationService> basList = session.createQuery("from BusinessApplicationService where isDeleted is null").list();
		
		/*for(BusinessApplicationService bas : basList){
		logger.info("BusinessApplicationService List :: " + bas );
		}*/
		
		logger.info("BusinessApplicationService List size:: " + basList.size() );
		//Collections.sort(basList);
		return basList;
	}

}
