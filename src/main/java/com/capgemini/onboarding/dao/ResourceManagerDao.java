package com.capgemini.onboarding.dao;

import java.util.List;

import com.capgemini.onboarding.model.ResourceManager;


public interface ResourceManagerDao {
	public List<ResourceManager> listResourceManager(int countryId);
	public ResourceManager getResourceManagerById(int id);
	public List<ResourceManager> listResourceManagerPreOnb(int countryId);
}
