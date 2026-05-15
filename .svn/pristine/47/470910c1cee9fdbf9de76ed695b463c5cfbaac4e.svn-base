package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.onboarding.dao.FieldCohortDao;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.onboarding.model.FieldCohortMapping;
import com.capgemini.onboarding.model.PrimaryProgram;
import com.capgemini.onboarding.model.Technology;

@Service
public class FieldCohortServiceImpl implements FieldCohortService {
	
	@Autowired
	private FieldCohortDao fieldCohortDao;

	@Override
	public List<FieldCohortMapping> getCohortForFields(int tnm, Employee emp) {
		// TODO Auto-generated method stub
		return this.fieldCohortDao.getCohortForFields(tnm, emp);
	}

	@Override
	public List<FieldCohortMapping> getCohortForFields(int tnm, Employee emp, int newBis, int newEmpRole,
			PrimaryProgram newPP, Technology primTech) {
		// TODO Auto-generated method stub
		return this.fieldCohortDao.getCohortForFields(tnm, emp, newBis, newEmpRole, newPP, primTech);
	}
	
	

}
