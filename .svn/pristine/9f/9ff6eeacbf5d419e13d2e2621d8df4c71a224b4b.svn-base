package com.capgemini.onboarding.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "role_mapping")
@Audited
public class Role implements Serializable, Comparable<Role> {

	private String role_id;
	private String role;
	private String role_name;
	
	@Id
	@Column(name = "role_id", nullable = false)
	@GeneratedValue
	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}


	@Column(name = "role", nullable = false)
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	@Column(name = "role_name", nullable = false)
	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}



	@Override
	public int compareTo(Role r) {
		// TODO Auto-generated method stub
		return 0;
	}
}
