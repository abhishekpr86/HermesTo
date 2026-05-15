package com.capgemini.onboarding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.hibernate.envers.Audited;

@Entity
@Table(name="Technology")
@Audited
@Proxy(lazy = false)
public class Technology implements Comparable<Technology>{
	
	private Integer technologyId;	
	private String technologyName;
	private String technologyValue;
	
	/**
	 * @return the technologyId
	 */
	@Id
	@Column(name="tech_id", nullable=false)
	@GeneratedValue
	public Integer getTechnologyId() {
		return technologyId;
	}
	/**
	 * @param technologyId the technologyId to set
	 */
	public void setTechnologyId(Integer technologyId) {
		this.technologyId = technologyId;
	}
	/**
	 * @return the technologyName
	 */
	@Column(name="tech_name", nullable=false)
	public String getTechnologyName() {
		return technologyName;
	}
	/**
	 * @param technologyName the technologyName to set
	 */
	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}
	
	@Column(name="tech_value", nullable=true)
	public String getTechnologyValue() {
		return technologyValue;
	}
	public void setTechnologyValue(String technologyValue) {
		this.technologyValue = technologyValue;
	}

	@Override
    public int compareTo(Technology technology) {
		return this.getTechnologyName().compareTo(technology.getTechnologyName());
      }
}
