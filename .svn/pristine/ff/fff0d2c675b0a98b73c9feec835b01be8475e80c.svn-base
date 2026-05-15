package com.capgemini.onboarding.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "psalibtype")
public class PSALibType implements Comparable<PSALibType> {

	private Integer psaLibId;
	private String psaLibName;
	
	@Id
	@GeneratedValue
	@Column(name = "psalib_id", nullable = false)
	public Integer getPsaLibId() {
		return psaLibId;
	}


	public void setPsaLibId(Integer psaLibId) {
		this.psaLibId = psaLibId;
	}

	@Column(name = "psalib_name", nullable = false)
	public String getPsaLibName() {
		return psaLibName;
	}


	public void setPsaLibName(String psaLibName) {
		this.psaLibName = psaLibName;
	}


	@Override
	public int compareTo(PSALibType psaLibType) {
		
		return this.getPsaLibName().compareTo(psaLibType.getPsaLibName());
		
	}
	
}
