package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.onboarding.model.GlobalGrade;

@Service
public interface GlobalGradeService {

	public List<GlobalGrade> listGlobalGrades();	
	public GlobalGrade getGlobalGradeById(int id);
	public GlobalGrade getGlobalGradeByName(String name);
}
