package com.capgemini.onboarding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pp_bis_mapping")
public class PPBisMapping {

	private int primaryProgramId;
	private int bis_id;
	
	@Id
	@GeneratedValue
	@Column(name = "primary_program_id",nullable = false)
	public int getPrimaryProgramId() {
		return primaryProgramId;
	}

	public void setPrimaryProgramId(int primaryProgramId) {
		this.primaryProgramId = primaryProgramId;
	}

	@Column(name = "bis_id",nullable = false)
	public int getBis_id() {
		return bis_id;
	}

	public void setBis_id(int bis_id) {
		this.bis_id = bis_id;
	}
	
	
}
