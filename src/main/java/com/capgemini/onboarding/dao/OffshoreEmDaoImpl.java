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


import com.capgemini.onboarding.model.OffshoreEm;

@Repository
public class OffshoreEmDaoImpl implements OffshoreEmDao {
	
	private static final Logger logger = LoggerFactory.getLogger(OffshoreEmDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public List<OffshoreEm> listOffshoreEm() {
		Session session = this.sessionFactory.getCurrentSession();
		List<OffshoreEm> offshoreEmList = session.createQuery("from OffshoreEm").list();
		
		Collections.sort(offshoreEmList);
		return offshoreEmList;

	}
	
	@Override
	public List<OffshoreEm> listOffshoreEmPreonb(){
		Session session = this.sessionFactory.getCurrentSession();
		List<OffshoreEm> offshoreEmList = session.createQuery("from OffshoreEm where isDeleted is null").list();
		
		
		//for (OffshoreEm offshoreEm : offshoreEmList){
			//logger.info("offshoreEmList List::" + offshoreEm);
		//}
		Collections.sort(offshoreEmList);
		return offshoreEmList;
		
	}

	@Override
	public OffshoreEm getOffshoreById(int id) {
	//Session session = this.sessionFactory.getCurrentSession();
		Session session = this.sessionFactory.openSession(); 
		session.beginTransaction();
		Criteria criteria =session.createCriteria(OffshoreEm.class);
		criteria.add(Restrictions.eq("offshoreEmId", id));
		OffshoreEm offshoreEm = (OffshoreEm) criteria.uniqueResult();
		session.close();
	//OffshoreEm offshoreEm = (OffshoreEm) session.load(OffshoreEm.class, new Integer(id));
	logger.info("OffshoreEm loaded successfully, OffshoreEm details=" + offshoreEm);
	return offshoreEm;

	}

}
