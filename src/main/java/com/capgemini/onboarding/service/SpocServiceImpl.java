package com.capgemini.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capgemini.onboarding.dao.SpocDao;
import com.capgemini.onboarding.model.Spoc;

@Service
public class SpocServiceImpl implements SpocService {

	@Autowired
	private SpocDao spocDao;

	@Override
	@Transactional
	public Spoc fetchSpoc(int countryId) {
		return spocDao.fetchSpoc(countryId);
	}

	@Override
	@Transactional
	public Spoc getSpocById(int id) {
		return spocDao.getSpocById(id);
	}

}
