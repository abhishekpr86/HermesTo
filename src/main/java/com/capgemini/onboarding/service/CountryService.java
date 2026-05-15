package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.onboarding.model.Country;

@Service
public interface CountryService {
	
	public List<Country> listCountry();

	public Country getCountryById(int id);
	
	public List<Country> getCountryListOrderWise();
}
