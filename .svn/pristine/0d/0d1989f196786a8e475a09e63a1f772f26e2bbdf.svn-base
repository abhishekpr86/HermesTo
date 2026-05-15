package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.Grade;
import com.capgemini.onboarding.service.GradeService;

public class IdToGradeConverter implements Converter<String, Grade>{
    @Autowired 
    GradeService gradeService;
    
    @Override
    public Grade convert(String id) {
    	if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return gradeService.getGradeById(Integer.parseInt(id));
    	}
    	return null;
    }
}