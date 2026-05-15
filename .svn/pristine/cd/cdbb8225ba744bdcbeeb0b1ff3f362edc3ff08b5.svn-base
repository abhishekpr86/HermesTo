package com.capgemini.onboarding.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.ResourceCohortDao;
import com.capgemini.onboarding.model.ResourceCohortMapping;

@Service
public class ResourceCohortServiceImpl implements ResourceCohortService {

	@Autowired
	private ResourceCohortDao resourceCohortDao;
	
	@Override
	@Transactional
	public int insert(List<ResourceCohortMapping> cohortList) {
		// TODO Auto-generated method stub
		return this.resourceCohortDao.insert(cohortList);
	}

	@Override
	@Transactional
	public ResourceCohortMapping getCohortMappingById(int id) {
		// TODO Auto-generated method stub
		return this.resourceCohortDao.getCohortMappingById(id);
	}

	@Override
	@Transactional
	public List<ResourceCohortMapping> getCohortsforRange(Date startDt, Date endDt) {
		// TODO Auto-generated method stub
		return this.resourceCohortDao.getCohortsforRange(startDt, endDt);
	}

}
