package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.BisDao;
import com.capgemini.onboarding.model.Bis;



@Service
public class BisServiceImpl implements BisService {

	@Autowired
	private BisDao bisDao;
	
	@Override
	@Transactional
	public List<Bis> bisList(int bundleEmId) {
		return this.bisDao.listBis(bundleEmId);
	}

	@Override
	@Transactional
	public Bis getBisById(int id) {
		// TODO Auto-generated method stub
		return this.bisDao.getBisById(id);
	}

	@Override
	@Transactional
	public List<Bis> fullListOfBis() {
		return this.bisDao.fullListOfBis();
	}

	@Override
	@Transactional
	public List<Bis> fullListOfBis(Boolean type) {
		
		return this.bisDao.fullListOfBis(type);
	}
	
	

}
