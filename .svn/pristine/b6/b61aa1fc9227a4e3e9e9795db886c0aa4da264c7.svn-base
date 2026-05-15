package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.Country;
import com.capgemini.onboarding.service.CountryService;

public class IdToCountryConverter implements Converter<String, Country>{
    @Autowired 
    CountryService countryService;
    
    @Override
    public Country convert(String id) {
    	if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return countryService.getCountryById(Integer.parseInt(id));
    	}
    	return null;
    }
}