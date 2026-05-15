package com.capgemini.onboarding.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.capgemini.onboarding.model.Bis;


@Service
public interface BisService {
	
	public List<Bis> bisList(int bundleEmId);
	
	public Bis getBisById(int id);
	public List<Bis> fullListOfBis();
	public List<Bis> fullListOfBis(Boolean type);

}
