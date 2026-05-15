package com.capgemini.onboarding.converter;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.Bis;
import com.capgemini.onboarding.service.BisService;

public class IdToBisConverter implements Converter<String,Bis>{

	@Autowired
	BisService bisService;

	@Override
	public Bis convert(String id) {
		if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return bisService.getBisById(Integer.parseInt(id));
    	}
		return null;
	}
	
}
