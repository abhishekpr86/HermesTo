package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capgemini.onboarding.dao.CfaoDao;
import com.capgemini.onboarding.model.CfaoGroup;

@Service
public class CfaoServiceImpl implements CfaoService{
	
	@Autowired
	private CfaoDao cfaoDao;
	

	@Override
	@Transactional
	public CfaoGroup getCfaoId(int id) {
		return this.cfaoDao.getCfaoId(id);
	}

	@Override
	@Transactional
	public List<CfaoGroup> listCfao() {
		return this.cfaoDao.listCfao();
	}

}
