package com.capgemini.onboarding.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.onboarding.dao.BisRotationDao;
import com.capgemini.onboarding.model.BisRotation;

@Service
public class BisRotationServiceImpl implements BisRotationService {

	@Autowired
	BisRotationDao bisRotationDao;
	
	@Override
	public List<BisRotation> getBisRotationFromDate(LocalDate fromDate, String corpid, LocalDate toDate) {
		// TODO Auto-generated method stub
		return this.bisRotationDao.getBisRotationFromDate(fromDate, corpid, toDate);
	}

	@Override
	public void insertBisRotation(BisRotation b) {
		// TODO Auto-generated method stub
		this.bisRotationDao.insertBisRotation(b);
	}

}
