package com.capgemini.moodle.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mdl_course")
public class MoodleCourse implements Serializable {

	private long id;
	private long category;
	private String fullname;
	
	@Id
	@Column(name="id",nullable=false)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="category",nullable=false)
	public long getCategory() {
		return category;
	}
	public void setCategory(long category) {
		this.category = category;
	}
	
	@Column(name="fullname",nullable=false)
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	
	
}
