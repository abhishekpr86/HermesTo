package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.GradeDao;
import com.capgemini.onboarding.model.Grade;

@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeDao gradeDao;
	
 	@Override
	@Transactional
	public List<Grade> listGrades(int countryId) {
		
		return gradeDao.listGrades(countryId);
		
	}

	@Override
	@Transactional
	public Grade getGradeById(int id) {
		return gradeDao.getGradeById(id);
	}

}
