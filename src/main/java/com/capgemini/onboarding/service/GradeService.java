package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.onboarding.model.Grade;

@Service
public interface GradeService {

	public List<Grade> listGrades(int countryId);

	public Grade getGradeById(int id);
}
