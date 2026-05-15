package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.GlobalGrade;
import com.capgemini.onboarding.service.GlobalGradeService;

public class IdToGlobalGradeConverter implements Converter<String, GlobalGrade>{
    @Autowired 
    GlobalGradeService globalGradeService;
    
    @Override
    public GlobalGrade convert(String id) {
    	if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return globalGradeService.getGlobalGradeById(Integer.parseInt(id));
    	}
    	return null;
    }
}