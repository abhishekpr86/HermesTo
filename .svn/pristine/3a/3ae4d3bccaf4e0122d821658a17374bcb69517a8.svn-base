package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import com.capgemini.onboarding.model.CfaoGroup;
import com.capgemini.onboarding.service.CfaoService;


public class IdToCfaoGroupConverter implements Converter<String, CfaoGroup>{

	
	@Autowired
	private CfaoService cfaoService;
	
	@Override
	public CfaoGroup convert(String id) {
		if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return cfaoService.getCfaoId(Integer.parseInt(id));
    	}
    	return null;
	}
}
