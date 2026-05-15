package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.PSALibTypeDao;
import com.capgemini.onboarding.model.PSALibType;

@Service
public class PSALibTypeServiceImpl implements PSALibTypeService {

	@Autowired
	private PSALibTypeDao psaLibTypeDao;
	
	@Override
	@Transactional
	public PSALibType getPSALibTypeById(Integer id) {
		
		return this.psaLibTypeDao.getPSALibTypeById(id);
	}

	@Override
	@Transactional
	public List<PSALibType> fullListOfPSALibType() {
		
		return this.psaLibTypeDao.fullListOfPSALibType();
	}

}
