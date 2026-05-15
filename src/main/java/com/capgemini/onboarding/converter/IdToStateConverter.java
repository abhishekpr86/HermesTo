package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.State;
import com.capgemini.onboarding.service.StateService;

public class IdToStateConverter implements Converter<String, State>{
    @Autowired 
    StateService stateService;
    
    @Override
    public State convert(String id) {
    	if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return stateService.getStateById(Integer.parseInt(id));
    	}
    	return null;
    }
}