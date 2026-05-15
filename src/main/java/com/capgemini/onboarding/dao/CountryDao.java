package com.capgemini.onboarding.dao;

import java.util.List;

import com.capgemini.onboarding.model.Country;
import com.capgemini.onboarding.model.EmployeeRoles;

public interface CountryDao {

	public List<Country> listCountry();
	public Country getCountryById(int id);
	public List<Country> getCountryListOrderWise();
}
