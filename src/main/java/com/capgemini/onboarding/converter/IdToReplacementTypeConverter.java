package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.ReplacementType;
import com.capgemini.onboarding.service.EmployeeService;

public class IdToReplacementTypeConverter implements Converter<String, ReplacementType>{

	@Autowired
	private EmployeeService employeeService;
	@Override
	public ReplacementType convert(String id) {
		// TODO Auto-generated method stub
		if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return employeeService.getReplacementTypeById(Integer.parseInt(id));
    	}
		return null;
	}

}
