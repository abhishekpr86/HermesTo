package com.capgemini.onboarding.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;
import org.springframework.security.core.userdetails.User;

/*
@NamedQueries(value = {@NamedQuery(name = "listAllUsers", query = "from users")})*/


@Entity
@Table(name = "users")


public class Users implements java.io.Serializable, Comparable<Users> {

	/*
	 * @EmbeddedId private Users ;
	 */

	private String userName;
	private String userPassword;
	private int enabled;
	private Boolean isFirstLogin;//
	//private String role_id;
	//private String roleName;
	private String userNamehidden;


	/**
	 * @return the username
	 */
	@Id
	@Column(name = "username")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	

	/**
	 * @return the password
	 */
	@Column(name = "password")


	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the enabled
	 */

	@Column(name = "enabled")
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * @return the role_id
	 */

	/*@Id
	@Column(name = "role_id")
	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}*/
	
	

	@Transient
	public String getUserNamehidden() {
		return userNamehidden;
	}

	public void setUserNamehidden(String userNamehidden) {
		this.userNamehidden = userNamehidden;
	}

	/*@Transient
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}*/
	@Column(name = "isFirstLogin")
	public Boolean getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(Boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	@Override
    public int compareTo(Users users) {
		return this.getUserName().compareTo(users.getUserName());
      }




}
