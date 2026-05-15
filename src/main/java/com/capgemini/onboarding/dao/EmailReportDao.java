package com.capgemini.onboarding.dao;

import java.util.List;

import com.capgemini.onboarding.model.EmailReport;

public interface EmailReportDao {

	public List<EmailReport> getEmailReportMapping();
	public EmailReport getEmailReportById(int id);
}
