package com.capgemini.onboarding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email_reports")
public class EmailReport implements java.io.Serializable{
	
	//private static final long serialVersionUID = 1L;
	private Integer id;
	private String report_name;
	private Boolean required;
	
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "report_name", nullable = false)
	public String getReport_name() {
		return report_name;
	}
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
	
	@Column(name = "required", nullable = false)
	public Boolean getRequired() {
		return required;
	}
	public void setRequired(Boolean required) {
		this.required = required;
	}
	
	

}
