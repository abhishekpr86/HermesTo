package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.moodle.model.User;
import com.capgemini.onboarding.dao.MoodleDao;
import com.capgemini.onboarding.dto.MoodleEnrolDataDTO;
import com.capgemini.onboarding.model.Employee;

@Service
public class MoodleServiceImpl implements MoodleService {

	@Autowired
	private MoodleDao moodleDao;
	
	@Override
	//@Transactional
	public void addUser(Employee emp, String auth) {
		
		this.moodleDao.addUser(emp, auth);
		
	}

	@Override
	//@Transactional
	public User getUserByUsername(String username) {
		
		return this.moodleDao.getUserByUsername(username);
	}

	@Override
	public void deleteUser(User user) {
		
		this.moodleDao.deleteUser(user);
	}
	
	@Override
	public void activateUser(User user) {//moodle user 
		
		this.moodleDao.activateUser(user);
	}
	
	@Override
	public List<MoodleEnrolDataDTO> fetchUSerEnrolments(MoodleEnrolDataDTO dto){
		return this.moodleDao.fetchUSerEnrolments(dto);
	}

	@Override
	public List<MoodleEnrolDataDTO> fetchMailUserEnrolments(MoodleEnrolDataDTO dto) {
		
		return this.moodleDao.fetchMailUserEnrolments(dto);
	}

	@Override
	public List<Employee> getPendingEnrolmentResource() {
		// TODO Auto-generated method stub
		return this.moodleDao.getPendingEnrolmentResource();
	}
	@Override
	public List<MoodleEnrolDataDTO> fetchUSerEnrolmentsData(MoodleEnrolDataDTO dto){
		return this.moodleDao.fetchUSerEnrolmentsData(dto);
	}
}
