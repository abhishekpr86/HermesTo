package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.OffshoreEm;

import com.capgemini.onboarding.service.OffshoreEmService;

public class IdToOffshoreEmCoverter implements Converter<String, OffshoreEm>{
	
	
	 @Autowired 
	 OffshoreEmService offshoreEmService;
	 
	 
	 @Override
	    public OffshoreEm convert(String id) {
	    	if(Objects.nonNull(id) && !id.trim().isEmpty()) {
	    		return offshoreEmService.getOffshoreById(Integer.parseInt(id));
	    	}
	    	return null;
	    }
	 

}
