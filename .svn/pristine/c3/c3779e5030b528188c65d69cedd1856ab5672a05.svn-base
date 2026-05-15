package com.capgemini.onboarding.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.model.Bis;
import com.capgemini.onboarding.model.Bis_bundle_Mapping;
import com.capgemini.onboarding.model.BundleEm;

@Repository
public class BundleEmDaoImpl implements BundleEmDao{
	
	private static final Logger logger = LoggerFactory.getLogger(OffshoreEmDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<BundleEm> listBundleEm() {
		Session session = this.sessionFactory.getCurrentSession();
		List<BundleEm> bundleEmList = session.createQuery("from BundleEm").list();
		
		//for(BundleEm bundleEm : bundleEmList){
		logger.info("bundleEmList List :: " + bundleEmList );
		//}
		
		Collections.sort(bundleEmList);
		return bundleEmList;
	}
	
	@Override
	public List<BundleEm> listBundleEmPreOnb() {
		Session session = this.sessionFactory.getCurrentSession();
		List<BundleEm> bundleEmList = session.createQuery("from BundleEm where isDeleted is null").list();
		
		//for(BundleEm bundleEm : bundleEmList){
		logger.info("bundleEmList List :: " + bundleEmList );
		//}
		
		Collections.sort(bundleEmList);
		return bundleEmList;
	}

	@Override
	public BundleEm getBundleEmById(int id) {
		Session session = this.sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(BundleEm.class);
		criteria.add(Restrictions.eq("bundleEmId", id));
		BundleEm bundleEm = (BundleEm) criteria.uniqueResult();
		logger.info("BundleEm loaded successfully, BundleEm details=" + bundleEm);
		session.close();		
		return bundleEm;
	}
	
	@Override
	public BundleEm getBundleEMListForBIS(int bis_id) {
		
		BundleEm bundleEM = null;
		//String hqlQuery = "SELECT * from bundle_em WHERE bundleEm_id in (SELECT bis_bundleEm_id FROM `bis_bundleem` WHERE bis_id=";
		Session session = this.sessionFactory.getCurrentSession();
		//session.beginTransaction();
		Criteria criteriaMap = session.createCriteria(Bis_bundle_Mapping.class);
		criteriaMap.add(Restrictions.eq("bis_id", bis_id));
		criteriaMap.add(Restrictions.isNull("isDeleted"));
		Bis_bundle_Mapping bisBundleMap = (Bis_bundle_Mapping) criteriaMap.uniqueResult();
		
		Criteria criteria = session.createCriteria(BundleEm.class);
		criteria.add(Restrictions.eq("bundleEmId", bisBundleMap.getBis_bundleEm_id()));
		bundleEM = (BundleEm) criteria.uniqueResult();
		//session.close();
		return bundleEM;	
		
	}

}
