package com.capgemini.onboarding.dao;

import java.util.List;

import com.capgemini.onboarding.model.DemandType;

public interface DemandTypeDao {

	public List<DemandType> demandTypeList();
	public DemandType getDemandTypeById(int id);
}
