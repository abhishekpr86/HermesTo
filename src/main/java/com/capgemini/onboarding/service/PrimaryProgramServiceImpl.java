package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.PrimaryProgramDao;
import com.capgemini.onboarding.model.PrimaryProgram;

@Service
public class PrimaryProgramServiceImpl implements PrimaryProgramService{

	@Autowired
	private PrimaryProgramDao primaryProgramDao;
	
	
	@Override
	@Transactional
	public List<PrimaryProgram> listPrimaryProgram(Boolean onlyOnGoining) {
		return this.primaryProgramDao.listPrimaryProgram(onlyOnGoining);
	}

	@Override
	@Transactional
	public PrimaryProgram getPrimaryProgramById(int id) {
		return this.primaryProgramDao.getPrimaryProgramById(id);
	}
	

}
