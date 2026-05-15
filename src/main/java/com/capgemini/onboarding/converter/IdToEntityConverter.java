package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.EntityDetail;
import com.capgemini.onboarding.service.EntityService;

public class IdToEntityConverter implements Converter<String, EntityDetail>{
    @Autowired 
    EntityService entityService;
    
    @Override
    public EntityDetail convert(String id) {
    	if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return entityService.getEntityById(Integer.parseInt(id));
    	}
    	return null;
    }
}