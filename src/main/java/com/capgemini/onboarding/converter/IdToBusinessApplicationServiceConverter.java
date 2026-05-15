package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import com.capgemini.onboarding.model.BusinessApplicationService;
import com.capgemini.onboarding.service.BasService;

public class IdToBusinessApplicationServiceConverter implements Converter<String, BusinessApplicationService>{

	@Autowired
	private BasService basService;
	
	@Override
	public BusinessApplicationService convert(String id) {
		if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return basService.getBasId(Integer.parseInt(id));
    	}
    	return null;
	}

}
