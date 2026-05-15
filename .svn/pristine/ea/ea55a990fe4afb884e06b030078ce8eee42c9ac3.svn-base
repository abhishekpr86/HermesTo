package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.DemandTypeDao;
import com.capgemini.onboarding.model.DemandType;

@Service
public class DemandTypeServiceImpl implements DemandTypeService {

	@Autowired
	private DemandTypeDao demandTypeDao;
	
	
	@Override
	@Transactional
	public List<DemandType> demandTypeList() {
		
		return this.demandTypeDao.demandTypeList();
	}

	@Override
	@Transactional
	public DemandType getDemandTypeById(int id) {
		
		return this.demandTypeDao.getDemandTypeById(id);
	}

}
