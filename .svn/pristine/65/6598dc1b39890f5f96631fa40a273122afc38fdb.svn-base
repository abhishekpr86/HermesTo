package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.onboarding.dto.MoodleEnrolDataDTO;
import com.capgemini.onboarding.model.Employee;
import com.capgemini.moodle.model.User;

@Service
public interface MoodleService {

	public void addUser(Employee emp, String auth);
	public User getUserByUsername(String username);
	public void deleteUser(User user);
	public List<MoodleEnrolDataDTO> fetchUSerEnrolments(MoodleEnrolDataDTO dto);
	public List<MoodleEnrolDataDTO> fetchMailUserEnrolments(MoodleEnrolDataDTO dto);
	public List<Employee> getPendingEnrolmentResource();
	List<MoodleEnrolDataDTO> fetchUSerEnrolmentsData(MoodleEnrolDataDTO dto);
	public void activateUser(User moodleUser);// for activation of moodle user
}
