package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.BasDao;
import com.capgemini.onboarding.model.BusinessApplicationService;

@Service
public class BasServiceImpl implements BasService{
	
	@Autowired
	private BasDao basDao;

	@Override
	@Transactional
	public BusinessApplicationService getBasId(int id) {
		return this.basDao.getBasId(id);
	}

	@Override
	@Transactional
	public List<BusinessApplicationService> listBas() {
		return this.basDao.listBas();
	}

	
}
