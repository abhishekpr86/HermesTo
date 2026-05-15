package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.capgemini.onboarding.model.CfaoGroup;

@Service
public interface CfaoService {

	public CfaoGroup getCfaoId(int id);
	 
    public List<CfaoGroup> listCfao();
}
