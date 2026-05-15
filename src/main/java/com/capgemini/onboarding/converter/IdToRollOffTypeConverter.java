package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.RollOffType;
import com.capgemini.onboarding.service.EmployeeService;

public class IdToRollOffTypeConverter implements Converter<String, RollOffType>{

	@Autowired
	private EmployeeService employeeService;
	@Override
	public RollOffType convert(String id) {
		// TODO Auto-generated method stub
		if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return employeeService.getRollOffTypeById(Integer.parseInt(id));
    	}
		return null;
	}
	


}
