package com.capgemini.onboarding.dao;

public interface UserManagementDao {

	
	public int changeUserPassword(String username, String newpassword, String oldPassword);
}
