package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.TechnologyDao;
import com.capgemini.onboarding.model.Technology;

@Service
public class TechnologyServiceImpl implements TechnologyService {

	@Autowired
	private TechnologyDao technologyDao;

	@Override
	@Transactional
	public List<Technology> listTechnology() {
		return technologyDao.listTechnology();
	}

	@Override
	@Transactional
	public Technology getTechnologyById(int id) {
		return technologyDao.getTechnologyById(id);
	}

}
