package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.BundleEmDao;
import com.capgemini.onboarding.model.BundleEm;

@Service
public class BundleEmServiceImpl implements BundleEmService{
	
	@Autowired
	private BundleEmDao bundleEmDao;

	@Override
	@Transactional
	public BundleEm getBundleEmById(int id) {
		return this.bundleEmDao.getBundleEmById(id);
	}

	@Override
	@Transactional
	public List<BundleEm> listBundleEm() {
		return this.bundleEmDao.listBundleEm();
	}
	
	@Override
	@Transactional
	public List<BundleEm> listBundleEmPreOnb() {
		return this.bundleEmDao.listBundleEmPreOnb();
	}
	
	@Override
	@Transactional
	public BundleEm getBundleEMListForBIS(int bis_id){
		return this.bundleEmDao.getBundleEMListForBIS(bis_id);
	}
	
	
}
