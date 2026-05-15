package com.capgemini.onboarding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.envers.Audited;

@Entity
@Table(name="role_tech")
public class RoleTech implements Comparable<RoleTech>{
	
	private int roleTechId;
	private String roleTechName;
	private Boolean isDeleted; 
	
	@Id
	@GeneratedValue
	@Column(name = "role_tech_id",nullable = false)
	public int getRoleTechId() {
		return roleTechId;
	}
	public void setRoleTechId(int roleTechId) {
		this.roleTechId = roleTechId;
	}
	
	@Column(name = "role_tech_name", nullable = false)
	public String getRoleTechName(){
		return roleTechName;
	}
	public void setRoleTechName(String roleTechName){
		this.roleTechName = roleTechName;
	}
	
	@Column(name = "isDeleted", nullable = true)
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Override
	public int compareTo(RoleTech roleTech) {
		return this.getRoleTechName().compareTo(roleTech.getRoleTechName());
	}

}
