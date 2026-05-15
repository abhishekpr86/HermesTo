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

import com.capgemini.onboarding.model.EmailReport;

@Repository
public class EmailReportDaoImpl implements EmailReportDao {

	private static final Logger logger = LoggerFactory.getLogger(EmailReportDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<EmailReport> getEmailReportMapping() {
		
		Session session = this.sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(EmailReport.class);
			criteria.add(Restrictions.isNull("required"));
			List<EmailReport> list = criteria.list();
			session.close();
			return list;
		}
		catch(Exception e) {
			logger.info("EmailReportDaoImpl "+e.getMessage());
			session.close();
			return null;
		}
		
	}
	@Override
	public EmailReport getEmailReportById(int id) {

		Session session = this.sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(EmailReport.class);
			criteria.add(Restrictions.eq("id", id));
			EmailReport er  = (EmailReport) criteria.uniqueResult();
			session.close();
			return er;
		}
		catch(Exception e) {
			logger.info("EmailReportDaoImpl getEmailReportById "+e.getMessage());
			session.close();
			return null;
		}
		
	}

}
