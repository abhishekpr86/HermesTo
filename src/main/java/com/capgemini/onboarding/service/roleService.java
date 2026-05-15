package com.capgemini.onboarding.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.onboarding.model.Role;

@Service
public interface roleService {

	public List<Role> listRole();
	public Role getRoleById(String id);
	
}
