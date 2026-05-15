package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.RoleTech;
import com.capgemini.onboarding.service.RoleTechService;

public class IdToRoleTechConverter implements Converter<String, RoleTech> {

	@Autowired
	private RoleTechService roleTechService;
	
	@Override
	public RoleTech convert(String id) {
		if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return roleTechService.getRoleTechId(Integer.parseInt(id));
    	}
    	return null;
		
	}
	
}
