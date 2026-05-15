package com.capgemini.onboarding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rm_pmo")

public class RmPMO implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer rmPmoId;
	private String rmPmoName;
	private String email;

	
	@Id
	@Column(name = "rm_pmo_id", nullable = false)
	@GeneratedValue
	public Integer getRmPmoId() {
		return rmPmoId;
	}

	public void setRmPmoId(Integer rmPmoId) {
		this.rmPmoId = rmPmoId;
	}

	@Column(name = "rm_pmo_name", nullable = false)
	public String getRmPmoName() {
		return rmPmoName;
	}

	public void setRmPmoName(String rmPmoName) {
		this.rmPmoName = rmPmoName;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
