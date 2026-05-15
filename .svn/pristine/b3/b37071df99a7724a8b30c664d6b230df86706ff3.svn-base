package com.capgemini.onboarding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "REPLACEMENT_TYPE")
@Audited
public class ReplacementType implements java.io.Serializable, Comparable<ReplacementType>{

	private int id;
	private String type;
	
	@Id
	@Column(name="id",nullable=false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="type",nullable=false)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public int compareTo(ReplacementType o) {
		
		return this.getType().compareTo(o.getType());
	}
}
