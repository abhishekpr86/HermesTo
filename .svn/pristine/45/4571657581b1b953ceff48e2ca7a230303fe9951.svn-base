package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.IndusGoals;
import com.capgemini.onboarding.service.IndusGoalsService;


public class IdToIndusGoalsConverter implements Converter<String, IndusGoals> {

	@Autowired
	private IndusGoalsService indusGoalsService;
	
	@Override
	public IndusGoals convert(String id) {
		if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return indusGoalsService.getIndusGoalsId(Integer.parseInt(id));
    	}
    	return null;
		
	}
}
