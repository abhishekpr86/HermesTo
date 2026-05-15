package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.PreOnbEmployee;
import com.capgemini.onboarding.service.PreOnbEmployeeService;

public class IdToPreOnbEmployeeConverter implements Converter<String, PreOnbEmployee>{

	@Autowired
	private PreOnbEmployeeService preOnbEmployeeDAOService;
	
	@Override
	public PreOnbEmployee convert(String id) {
		if(Objects.nonNull(id)) {
    		return preOnbEmployeeDAOService.getPreOnbEmployeeDAOById(Long.valueOf(id));
    	}
    	return null;
	}

}
