package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.PSALibType;
import com.capgemini.onboarding.service.PSALibTypeService;

public class IdToPSALibTypeConverter implements Converter<String, PSALibType> {

	@Autowired
	PSALibTypeService psaLibTypeService;
	
	@Override
	public PSALibType convert(String id) {
		if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return psaLibTypeService.getPSALibTypeById(Integer.parseInt(id));
    	}
		return null;
	}
}
