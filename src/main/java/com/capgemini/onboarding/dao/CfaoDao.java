package com.capgemini.onboarding.dao;

import java.util.List;

import com.capgemini.onboarding.model.CfaoGroup;


public interface CfaoDao {
	
	public CfaoGroup getCfaoId(int id);
	 
    public List<CfaoGroup> listCfao();

}
