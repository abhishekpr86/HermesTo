package com.capgemini.onboarding.dao;

import java.util.List;

import com.capgemini.onboarding.model.Role;
import com.capgemini.onboarding.model.UserRoles;
import com.capgemini.onboarding.model.Users;


public interface UsersDao {

	public void addUsers(Users users);
	public void updateUsers(Users users);
	public Users getUsersById(String id);
	public List<UserRoles> searchhUsers(UserRoles users);
	public boolean checkUserNameExists(String userName , String userNameHidden);
	public void deleteUserRole(UserRoles users);
	public List<Role> addRoleList(String userName);
	public void addUserRole(UserRoles userRole);
	public void disableUser(String corpid);

}
