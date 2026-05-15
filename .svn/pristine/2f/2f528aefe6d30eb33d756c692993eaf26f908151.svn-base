package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.CGEntityDetail;
import com.capgemini.onboarding.service.CgEntityService;

public class IdToCGEntityConverter implements Converter<String, CGEntityDetail>{
    @Autowired 
    CgEntityService cgEntityService;
    
    @Override
    public CGEntityDetail convert(String id) {
    	if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return cgEntityService.getCgEntityById(Integer.parseInt(id));
    	}
    	return null;
    }
}