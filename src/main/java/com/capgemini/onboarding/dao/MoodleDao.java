package com.capgemini.onboarding.dao;

import java.util.List;

import com.capgemini.moodle.model.User;
import com.capgemini.onboarding.dto.MoodleEnrolDataDTO;
import com.capgemini.onboarding.model.Employee;

public interface MoodleDao {

	public void addUser(Employee emp, String auth);
	public User getUserByUsername(String username);
	public void deleteUser(User user);
	public List<MoodleEnrolDataDTO> fetchUSerEnrolments(MoodleEnrolDataDTO dto);
	public List<MoodleEnrolDataDTO> fetchMailUserEnrolments(MoodleEnrolDataDTO dto);
	public List<Employee> getPendingEnrolmentResource();
	List<MoodleEnrolDataDTO> fetchUSerEnrolmentsData(MoodleEnrolDataDTO dto);
	public void activateUser(User user);// for activation od moodle user
}
