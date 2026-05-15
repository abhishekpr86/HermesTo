package com.capgemini.onboarding.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.onboarding.model.Vendor;
import com.capgemini.onboarding.service.VendorService;

public class IdToVendorConverter implements Converter<String, Vendor>{
    @Autowired 
    VendorService vendorService;
    
    @Override
    public Vendor convert(String id) {
    	if(Objects.nonNull(id) && !id.trim().isEmpty()) {
    		return vendorService.getVendorById(Integer.parseInt(id));    		
    	}    	
    	return null;
    }
}