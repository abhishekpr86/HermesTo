package com.capgemini.onboarding.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.onboarding.model.ResourceCohortMapping;

@Service
public interface ResourceCohortService {

	public int insert(List<ResourceCohortMapping> cohortList);
	public ResourceCohortMapping getCohortMappingById(int id);
	public List<ResourceCohortMapping> getCohortsforRange(Date startDt, Date endDt);
}
