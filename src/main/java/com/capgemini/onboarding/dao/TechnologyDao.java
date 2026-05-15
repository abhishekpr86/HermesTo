package com.capgemini.onboarding.dao;

import java.util.List;

import com.capgemini.onboarding.model.Technology;

public interface TechnologyDao {

	public List<Technology> listTechnology();
	public Technology getTechnologyById(int id);
}
