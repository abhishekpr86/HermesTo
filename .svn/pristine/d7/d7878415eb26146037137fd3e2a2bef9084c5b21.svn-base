package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.EmailReportDao;
import com.capgemini.onboarding.model.EmailReport;

@Service
public class EmailReportServiceImpl implements EmailReportService {
	
	@Autowired
	private EmailReportDao emailReportDao;

	@Override
	@Transactional
	public List<EmailReport> getEmailReportMapping() {
		// TODO Auto-generated method stub
		return this.emailReportDao.getEmailReportMapping();
	}

	@Override
	@Transactional
	public EmailReport getEmailReportById(int id) {
		// TODO Auto-generated method stub
		return this.emailReportDao.getEmailReportById(id);
	}

}
