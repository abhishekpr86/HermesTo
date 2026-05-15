package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.BundleEm;
import com.capgemini.onboarding.service.BundleEmService;

public class IdToBundleEmConverter implements Converter<String, BundleEm> {

	@Autowired 
	private BundleEmService bundleEmService;

	@Override
	public BundleEm convert(String id) {
		if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return bundleEmService.getBundleEmById(Integer.parseInt(id));
    	}
    	return null;
		
	}
	
	

	

}
