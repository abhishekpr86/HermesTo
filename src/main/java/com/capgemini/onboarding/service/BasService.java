package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.onboarding.model.BusinessApplicationService;

@Service
public interface BasService {

	public BusinessApplicationService getBasId(int id);
	 
    public List<BusinessApplicationService> listBas();
}
