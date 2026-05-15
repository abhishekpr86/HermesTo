package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.Technology;
import com.capgemini.onboarding.service.TechnologyService;

public class IdToTechnologyConverter implements Converter<String, Technology>{
    @Autowired 
    TechnologyService technologyService;
    
    @Override
    public Technology convert(String id) {
    	if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    	return technologyService.getTechnologyById(Integer.parseInt(id));
    	}
    	return null;
    }
}