package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onboarding.dao.RoleDao;
import com.capgemini.onboarding.model.Role;

@Service
public class RoleServiceImpl implements roleService {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	@Transactional
	public List<Role> listRole() {
		
		return this.roleDao.listRole();
	}

	@Override
	@Transactional
	public Role getRoleById(String id) {
		// TODO Auto-generated method stub
		return this.roleDao.getRoleById(id);
	}

}
