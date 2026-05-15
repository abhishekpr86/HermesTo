package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.service.EmployeeService;

public class IdToEmployeeConverter implements Converter<String, Employee>{
    @Autowired 
    EmployeeService empService;
    
    @Override
    public Employee convert(String id) {
    	if(Objects.nonNull(id)) {
    		return empService.getEmployeeById(Long.valueOf(id));
    	}
    	return null;
    }
}