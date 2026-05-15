package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.EmployeeRoles;
import com.capgemini.onboarding.service.EmployeeRoleService;

public class IdToEmpRolesConverter implements Converter<String, EmployeeRoles> {

	@Autowired
	private EmployeeRoleService employeeroleservice;
	
	@Override
	public EmployeeRoles convert(String id) {
		if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return employeeroleservice.getEmployeeRolesId(Integer.parseInt(id));
    	}
    	return null;
	}

	
	}


