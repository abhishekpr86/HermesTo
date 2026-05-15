package com.capgemini.onboarding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name="domainlist")
@Audited
public class EmployeeRoles implements Comparable<EmployeeRoles>{

	
	private Integer ref_id;
	private String ref_name;
	private String ref_value;
	private String domain_name;
	private Boolean active;
	
	@Id
	@GeneratedValue
	@Column(name="Ref_Id", nullable=false)
	public Integer getRef_id() {
		return ref_id;
	}

	public void setRef_id(Integer ref_id) {
		this.ref_id = ref_id;
	}

	@Column(name="Ref_Name", nullable=false)
	public String getRef_name() {
		return ref_name;
	}

	public void setRef_name(String ref_name) {
		this.ref_name = ref_name;
	}

	@Column(name="Ref_value", nullable=false)
	public String getRef_value() {
		return ref_value;
	}

	public void setRef_value(String ref_value) {
		this.ref_value = ref_value;
	}
	
	@Column(name="Domain_Name", nullable=false)
	public String getDomain_name() {
		return domain_name;
	}

	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}

	@Column(name="Active", nullable=false)
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public int compareTo(EmployeeRoles o) {
		return this.getRef_name().compareTo(o.getRef_name());
	}
	
	
}
