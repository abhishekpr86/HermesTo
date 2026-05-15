package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.EmployeeRoleDao;
import com.capgemini.onboarding.model.EmployeeRoles;

@Service
public class EmployeeRoleServiceImpl implements EmployeeRoleService {

	@Autowired
	private EmployeeRoleDao emproleDao;
	@Override
	@Transactional
	public List<EmployeeRoles> listEmployeeRoles() {
		return emproleDao.listEmployeeRoles();
	}
	
	
	@Override
	@Transactional
	public EmployeeRoles getEmployeeRolesId(int id) {
		// TODO Auto-generated method stub
		return emproleDao.getEmployeeRolesId(id);
	}

}
