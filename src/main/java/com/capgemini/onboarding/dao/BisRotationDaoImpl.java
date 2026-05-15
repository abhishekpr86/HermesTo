package com.capgemini.onboarding.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.onboarding.model.BisRotation;

@Repository
public class BisRotationDaoImpl implements BisRotationDao {

	private static final Logger logger = LoggerFactory.getLogger(BisRotationDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<BisRotation> getBisRotationFromDate(LocalDate fromDate, String corpid, LocalDate toDate) {
		// TODO Auto-generated method stub
		
		List<BisRotation> bisRotate = null;
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean isConjunctionReq = false;
		try {
			Criteria criteria = session.createCriteria(BisRotation.class);
			
			String hqlQuery = "SELECT new com.capgemini.onboarding.model.BisRotation(r.id, r.idCorpId, r.corpId, r.prevBundle, r.newBundle, r.rotationDate, e.firstName, e.lastName, e.globalGrade, e.roleid, e.primaryprogram, e.country, e.location) " + 
					"FROM BisRotation r " + 
					"left join Employee e on e.id=r.idCorpId ";
			
			if(toDate != null || fromDate != null || !corpid.isEmpty()) {
				hqlQuery = hqlQuery+"where ";
			}
			if(fromDate != null) {
				hqlQuery = hqlQuery+ " rotationDate >= '"+fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"' ";
				isConjunctionReq = true;
			}
			if(!corpid.isEmpty()) {
				if(isConjunctionReq)
					hqlQuery = hqlQuery+ "and ";
				hqlQuery = hqlQuery+ "corpId ='"+corpid+"' ";
				//criteria.add(Restrictions.eq("corpId", corpid));
			}
			if(toDate != null) {
				if(isConjunctionReq)
					hqlQuery = hqlQuery+ "and ";
				hqlQuery = hqlQuery+ " rotationDate <= '"+toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"'";
				//criteria.add(Restrictions.le("rotationDate", toDate));
			}
			//hqlQuery = hqlQuery+ ";";
			
			List<BisRotation> rotationList =  session.createQuery(hqlQuery, BisRotation.class).getResultList();
			
			return rotationList;
		}catch(Exception e) {
			logger.info("Rotation Date fetch Error ");
			tx.rollback();
			throw e;
		}
		finally {
			logger.info("fianlly block");
			if(session != null && session.isOpen()) {
				session.close();
			}
		}
		
	}

	@Override
	public void insertBisRotation(BisRotation b) {
		// TODO Auto-generated method stub

		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			//tx.begin();
			Criteria criteria = session.createCriteria(BisRotation.class);
			session.save(b);
			tx.commit();
		}catch(Exception e) {
			logger.info("Insert Rotation Date Error "+ e.getMessage());
			tx.rollback();
			throw e;
			
		}
		finally {
			logger.info("Insertion Bis Rotation fianlly block");
			if(session != null && session.isOpen()) {
				session.close();
			}
		}
	}

}
