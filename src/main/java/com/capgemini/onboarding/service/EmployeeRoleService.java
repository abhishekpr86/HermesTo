package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.onboarding.model.EmployeeRoles;
import com.capgemini.onboarding.model.RoleTech;

@Service
public interface EmployeeRoleService {

	public EmployeeRoles getEmployeeRolesId(int id);

	public List<EmployeeRoles> listEmployeeRoles();
}
