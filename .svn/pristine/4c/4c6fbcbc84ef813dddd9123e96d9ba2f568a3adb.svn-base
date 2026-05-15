package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.PrimaryProgram;
import com.capgemini.onboarding.service.PrimaryProgramService;

public class IdToPrimaryProgramConverter implements Converter<String, PrimaryProgram>{

	@Autowired
	PrimaryProgramService primaryProgramService;
	
	@Override
	public PrimaryProgram convert(String id) {
		if(Objects.nonNull(id) && !id.trim().isEmpty()) {
			return primaryProgramService.getPrimaryProgramById(Integer.parseInt(id));
    	}
		return null;
	}
	

}
