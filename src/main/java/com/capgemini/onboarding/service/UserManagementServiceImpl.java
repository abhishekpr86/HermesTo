package com.capgemini.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.UserManagementDao;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	UserManagementDao userManagementDao;

	@Override
	@Transactional
	public int changeUserPassword(String username, String newpassword, String oldPassword) {
		return userManagementDao.changeUserPassword(username, newpassword, oldPassword);
	}

}
