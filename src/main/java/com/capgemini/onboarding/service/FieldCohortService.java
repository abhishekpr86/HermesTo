package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.FieldCohortMapping;
import com.capgemini.onboarding.model.PrimaryProgram;
import com.capgemini.onboarding.model.Technology;

@Service
public interface FieldCohortService {

	public List<FieldCohortMapping> getCohortForFields(int tnm, Employee emp);
	public List<FieldCohortMapping> getCohortForFields(int tnm, Employee emp,int newBis,int newEmpRole, PrimaryProgram newPP,Technology primTech);
	
}
