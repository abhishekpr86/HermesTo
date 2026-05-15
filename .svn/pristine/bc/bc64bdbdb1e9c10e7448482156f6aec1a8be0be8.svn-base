package com.capgemini.onboarding.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.model.EntityDetail;

@Repository
public class EntityDaoImpl implements EntityDao {

	private static final Logger logger = LoggerFactory.getLogger(EntityDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<EntityDetail> listEntity() {
		Session session = this.sessionFactory.getCurrentSession();
		List<EntityDetail> entityDetailsList = session.createQuery("from EntityDetail").list();
		//for(EntityDetail entityDetail : entityDetailsList){
			//logger.info("entityDetailsList List::"+entityDetail);
		//}
		Collections.sort(entityDetailsList);
		return entityDetailsList;	
	}
	
	@Override
	public EntityDetail getEntityById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		EntityDetail entityDetail = (EntityDetail) session.load(EntityDetail.class, new Integer(id));
		logger.info("Grade loaded successfully, Grade details=" + entityDetail);
		return entityDetail;
		
	}

}
