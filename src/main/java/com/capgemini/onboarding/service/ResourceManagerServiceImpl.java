package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.ResourceManagerDao;
import com.capgemini.onboarding.model.ResourceManager;

@Service
public class ResourceManagerServiceImpl implements ResourceManagerService {

	@Autowired
	private ResourceManagerDao resourceMgrDao;

	@Override
	@Transactional
	public List<ResourceManager> listResourceManager(int countryId) {
		// TODO Auto-generated method stub
		return resourceMgrDao.listResourceManager(countryId);
	}
	
	@Override
	@Transactional
	public List<ResourceManager> listResourceManagerPreOnb(int countryId) {
		// TODO Auto-generated method stub
		return resourceMgrDao.listResourceManagerPreOnb(countryId);
	}

	@Override
	@Transactional
	public ResourceManager getResourceManagerById(int id) {
		// TODO Auto-generated method stub
		return resourceMgrDao.getResourceManagerById(id);
	}

	
	
	
	
	
}
