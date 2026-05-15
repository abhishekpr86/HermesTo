package com.capgemini.onboarding.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

/*@NamedQueries(value = {
		@NamedQuery(name = "listProjectByID",
				query = "from ResourceManager r where r.countryId =:countryId")})
*/
@Entity
@Table(name = "project")
@Audited
public class Project implements Serializable, Comparable<Project> {

	private Integer projectId;
	private String projectName;
	
	
	@Id
	@Column(name = "project_id", nullable = false)
	@GeneratedValue
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}


	@Column(name = "project_name", nullable = false)
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	

	@Override
	public int compareTo(Project arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
