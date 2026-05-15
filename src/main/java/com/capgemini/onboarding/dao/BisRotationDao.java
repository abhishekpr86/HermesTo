package com.capgemini.onboarding.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.capgemini.onboarding.model.BisRotation;

public interface BisRotationDao {

	public List<BisRotation> getBisRotationFromDate(LocalDate fromDate, String corpid, LocalDate toDate);
	public void insertBisRotation(BisRotation b);
}
