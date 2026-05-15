package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.GlobalGradeDao;
import com.capgemini.onboarding.model.GlobalGrade;

@Service
public class GlobalGradeServiceImpl implements GlobalGradeService {

	@Autowired
	private GlobalGradeDao globalGradeDao;
	
	@Override
	@Transactional
	public List<GlobalGrade> listGlobalGrades() {
		return globalGradeDao.listGlobalGrades();
	}

	@Override
	@Transactional
	public GlobalGrade getGlobalGradeById(int id) {
		return globalGradeDao.getGlobalGradeById(id);
	}
	
	@Override
	@Transactional
	public GlobalGrade getGlobalGradeByName(String name) {
		return globalGradeDao.getGlobalGradeByName(name);
	}
}
