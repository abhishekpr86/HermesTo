package com.capgemini.onboarding.dao;

import java.util.Date;
import java.util.List;

import com.capgemini.onboarding.model.ResourceCohortMapping;

public interface ResourceCohortDao {

	public int insert(List<ResourceCohortMapping> cohortList);
	public ResourceCohortMapping getCohortMappingById(int id);
	public List<ResourceCohortMapping> getCohortsforRange(Date startDt, Date endDt);
	
}
