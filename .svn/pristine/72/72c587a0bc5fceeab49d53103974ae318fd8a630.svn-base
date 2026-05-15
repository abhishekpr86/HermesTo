package com.capgemini.onboarding.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "field_cohort_mapping")
@Audited
public class FieldCohortMapping implements Serializable {

	private int id;
	private String fieldName;
	private int fieldValue;
	private String Cohort;
	private Boolean isDeleted;
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "fieldName", nullable = false)
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	@Column(name = "fieldValue", nullable = false)
	public int getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(int fieldValue) {
		this.fieldValue = fieldValue;
	}
	
	@Column(name = "cohortName", nullable = false)
	public String getCohort() {
		return Cohort;
	}
	public void setCohort(String cohort) {
		Cohort = cohort;
	}
	
	@Column(name = "isDeleted", nullable = true)
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
		
}
