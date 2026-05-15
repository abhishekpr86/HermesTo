package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.Role;
import com.capgemini.onboarding.service.roleService;

public class IdToRoleConverter implements Converter<String, Role> {

	@Autowired 
    roleService roleService;
	
	@Override
    public Role convert(String id) {
    	if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return roleService.getRoleById(id); //Integer.parseInt(id)
    	}
    	return null;
    }
}
