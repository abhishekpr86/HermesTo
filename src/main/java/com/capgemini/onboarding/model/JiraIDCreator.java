package com.capgemini.onboarding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name = "JiraID_Creator")

	public class JiraIDCreator implements java.io.Serializable{

		private static final long serialVersionUID = 1L;
		private Integer id;
		private String name;
		private String email;


		@Id
		@Column(name = "id", nullable = false)
		@GeneratedValue
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
		
		@Column(name = "name", nullable = false)
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Column(name = "email", nullable = false)
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}	