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

import com.capgemini.onboarding.model.ResourceManager;
import com.capgemini.onboarding.model.Vendor;

@Repository
public class VendorDaoImpl implements VendorDao {

	private static final Logger logger = LoggerFactory.getLogger(VendorDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Vendor> listVendor(int countryId, boolean isBundleEMReq) {
		Session session = this.sessionFactory.getCurrentSession();
		/*TypedQuery<Vendor> query = session.createNamedQuery("listVendorByCountry");		
		query.setParameter("countryId", countryId);
		query.setParameter("isDeleted", null);
		List<Vendor> listVendor = query.getResultList();
		Collections.sort(listVendor);
		return listVendor;*/
		
		Criteria criteria = session.createCriteria(Vendor.class);
		criteria.add(Restrictions.isNull("isDeleted"));
		criteria.add(Restrictions.eq("countryId", countryId));
		
		List<Vendor> listVendor = criteria.list(); //query.getResultList();
		Collections.sort(listVendor);
		return listVendor;
	}

	@Override
	public Vendor getVendorById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Vendor vendor = (Vendor) session.load(Vendor.class, new Integer(id));
		logger.info("vendor loaded successfully, state details=" + vendor);
		return vendor;
	}

}
