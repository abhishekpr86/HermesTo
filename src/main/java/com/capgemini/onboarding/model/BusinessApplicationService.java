package com.capgemini.onboarding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="business_app_serv")
public class BusinessApplicationService implements Comparable<BusinessApplicationService>{
	
	private int basId;
	private String basName;
	private Boolean isDeleted;
	
	@Id
	@GeneratedValue
	@Column(name = "bas_id",nullable = false)
	public int getBasId() {
		return basId;
	}
	public void setBasId(int basId) {
		this.basId = basId;
	}
	
	@Column(name ="bas_name")
	public String getBasName() {
		return basName;
	}
	public void setBasName(String basName) {
		this.basName = basName;
	}
	
	@Column(name ="isDeleted")
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Override
	public int compareTo(BusinessApplicationService bas) {
		return this.getBasName().compareTo(bas.getBasName());
	} 
	
	

}
