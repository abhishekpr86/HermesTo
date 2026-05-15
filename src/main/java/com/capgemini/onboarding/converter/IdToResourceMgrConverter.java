package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.ResourceManager;
import com.capgemini.onboarding.service.ResourceManagerService;



public class IdToResourceMgrConverter implements Converter<String,ResourceManager> {
	
	@Autowired
	ResourceManagerService resourceMgrService;

	@Override
	public ResourceManager convert(String id) {
		if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return resourceMgrService.getResourceManagerById(Integer.parseInt(id));
    	}
		return null;
	}

	
}