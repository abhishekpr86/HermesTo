package com.capgemini.moodle.converter;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.capgemini.moodle.model.User;
import com.capgemini.onboarding.service.MoodleService;

public class UsernameToUserConverter implements Converter<String, User> {
	
	@Autowired
	MoodleService moodleService;
	
	public User convert(String username) {
		if(Objects.isNull(username)) {
			return this.moodleService.getUserByUsername(username);
		}
		return null;
	}

}
