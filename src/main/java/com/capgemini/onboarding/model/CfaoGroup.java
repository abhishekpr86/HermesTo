package com.capgemini.onboarding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cfao_group")
public class CfaoGroup implements Comparable<CfaoGroup>{

	
	private int cfaoId;
	private String cfaoName;
	private Boolean isDeleted;
	
	@Id
	@GeneratedValue
	@Column(name = "cfao_id",nullable = false)
	public int getCfaoId() {
		return cfaoId;
	}
	public void setCfaoId(int cfaoId) {
		this.cfaoId = cfaoId;
	}
	
	@Column(name ="cfao_name")
	public String getCfaoName() {
		return cfaoName;
	}
	public void setCfaoName(String cfaoName) {
		this.cfaoName = cfaoName;
	}
	
	@Column(name ="isDeleted")
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Override
	public int compareTo(CfaoGroup cfao) {
		return this.getCfaoName().compareTo(cfao.getCfaoName());
	}
	
	
}
