package com.capgemini.onboarding.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "demand_type")
@Audited
public class DemandType implements Serializable, Comparable<DemandType> {

	private int id;
	private String type;
	
	@Id
	@GeneratedValue
	@Column(name = "demand_id", nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="type", nullable = false)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public int compareTo(DemandType demand) {

		return this.getType().compareTo(demand.getType());
	}
	
	
}
