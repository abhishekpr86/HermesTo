package com.capgemini.onboarding.dao;

import java.util.List;

import com.capgemini.onboarding.model.EmployeeRoles;

public interface EmployeeRoleDao {
	
	public List<EmployeeRoles> listEmployeeRoles();

	public EmployeeRoles getEmployeeRolesId(int id);

}
