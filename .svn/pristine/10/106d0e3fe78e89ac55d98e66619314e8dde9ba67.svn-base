package com.capgemini.onboarding.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;


@NamedQueries(value = {		
		@NamedQuery(name="listGradeByCountry",
		query="from Grade g where g.countryId =:countryId"
		)	
	})
@Entity
@Table(name="GRADE")
@Audited
public class Grade  implements java.io.Serializable, Comparable<Grade>  {
	
	private Integer gradeId;	
	private String name;	
	private Integer order;	
	//private Set<Employee> employees;
	@JsonIgnore
	private Set<Training> trainings = new HashSet<Training>(0);
	private Integer countryId;
	private Integer globalGradeId;
	
	/**//**
	 * @return the employees
	 *//*
	@OneToMany(mappedBy="grade")
	public Set<Employee> getEmployees() {
		return employees;
	}

	*//**
	 * @param employees the employees to set
	 *//*
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
*/
	@Id
	@Column(name="grade_id", nullable=false)
	@GeneratedValue
	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	@Column(name="grade_name", nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the countryId
	 */
	@Column(name="country_id", nullable=false)
	public Integer getCountryId() {
		return countryId;
	}
	/**
	 * @param countryId the countryId to set
	 */	
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}	
	
	/**
	 * @return the globalGradeId
	 */
	@Column(name="global_grade_id", nullable=false)
	public Integer getGlobalGradeId() {
		return globalGradeId;
	}

	/**
	 * @param globalGradeId the globalGradeId to set
	 */
	public void setGlobalGradeId(Integer globalGradeId) {
		this.globalGradeId = globalGradeId;
	}

	@Column(name="grade_order", nullable=false)
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "grades")
	
	public Set<Training> getTrainings() {
		return trainings;
	}

	public void setTrainings(Set<Training> trainings) {
		this.trainings = trainings;
	}
	
	@Override
    public int compareTo(Grade grade) {
		return this.getName().compareTo(grade.getName());
      }
}
