package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.DemandType;
import com.capgemini.onboarding.service.DemandTypeService;;

public class IdToDemandTypeConverter implements Converter<String,DemandType>{

	@Autowired
		DemandTypeService demandTypeService;

	@Override
	public DemandType convert(String id) {
		if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return demandTypeService.getDemandTypeById(Integer.parseInt(id));
    	}
		return null;
	}
	
}
