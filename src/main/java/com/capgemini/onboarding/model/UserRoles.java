package com.capgemini.onboarding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.capgemini.onboarding.model.Role;

@Entity
@Table(name = "user_roles")
public final class UserRoles implements java.io.Serializable, Comparable<UserRoles>{

	private String userName;
	private Role role_id;
	//private String roleName;
	
	@Id
	@Column(name = "username")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Id
	@ManyToOne
	@JoinColumn(name = "role_id")
	public Role getRole_id() {
		return role_id;
	}
	public void setRole_id(Role role_id) {
		this.role_id = role_id;
	}
	
	/*@Transient
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}*/
	
	@Override
    public int compareTo(UserRoles users) {
		return this.getUserName().compareTo(users.getUserName());
      }
	
}
