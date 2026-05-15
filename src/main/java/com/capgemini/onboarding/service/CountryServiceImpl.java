package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.CountryDao;
import com.capgemini.onboarding.model.Country;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDao countryDao;

	@Override
	@Transactional
	public Country getCountryById(int id) {
		return countryDao.getCountryById(id);
	}

	@Override
	@Transactional
	public List<Country> listCountry() {
		return countryDao.listCountry();
	}
	
	

	@Override
	@Transactional
	public List<Country> getCountryListOrderWise() {
		return countryDao.getCountryListOrderWise();
	}

}
