package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.Spoc;
import com.capgemini.onboarding.service.SpocService;

public class IdToSpocConverter implements Converter<String, Spoc>{
    @Autowired 
    SpocService spocService;
    
    @Override
    public Spoc convert(String id) {
    	if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return spocService.getSpocById(Integer.parseInt(id));
    	}
    	return null;
    }
}