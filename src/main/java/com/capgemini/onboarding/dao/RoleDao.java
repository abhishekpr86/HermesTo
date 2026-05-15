package com.capgemini.onboarding.dao;

import java.util.List;


import com.capgemini.onboarding.model.Role;

public interface RoleDao {

	public List<Role> listRole();
	public Role getRoleById(String id);
}
