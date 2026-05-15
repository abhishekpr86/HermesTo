package com.capgemini.onboarding.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.OffshoreEmDao;

import com.capgemini.onboarding.model.OffshoreEm;


@Service
public class OffshoreEmServiceImpl implements OffshoreEmService{
	
	@Autowired
	private OffshoreEmDao offshoreEmDao;

	@Override
	@Transactional
	public List<OffshoreEm> listOffshoreEm() {
		return offshoreEmDao.listOffshoreEm();
	}

	@Override
	@Transactional
	public OffshoreEm getOffshoreById(int id) {
		return offshoreEmDao.getOffshoreById(id);
	}
	
	@Override
	@Transactional
	public List<OffshoreEm> listOffshoreEmPreonb(){
		return offshoreEmDao.listOffshoreEmPreonb();
	}


}
