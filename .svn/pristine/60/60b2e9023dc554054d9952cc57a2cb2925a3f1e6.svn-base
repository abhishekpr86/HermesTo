package com.capgemini.onboarding.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;


@NamedQueries(value = {
		@NamedQuery(name="listTrainings",
				query="select t from Training t join t.grades g where g.gradeId=:gradeId"
				),
		@NamedQuery(name="displayTrainings",
		query="select t from Training t join t.employeeTrainings et where et.employeeTrainingId.employee.empId=:empId and et.attendedDate is null order by t.order"
		)
	})

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author capgemini
 *
 */
@Entity
@Table(name="TRAINING")
@Audited
public class Training  implements java.io.Serializable  {

	private Integer trainingId;	
	private String name;	
	private String type;	
	private Boolean mandatory;	
	private Integer order;	
	private String docURL;		
	private Date startDate;	
	private Date endDate;	
	private String description;	
	private List<EmployeeTraining> employeeTrainings = new ArrayList<EmployeeTraining>(0);
	private Set<Grade> grades = new HashSet<Grade>(0);
	
	@Id
	@Column(name="training_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(Integer trainingId) {
		this.trainingId = trainingId;
	}

	@Column(name="name",nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="type",nullable=false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name="mandatory",nullable=false)
	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	@Column(name="training_order",nullable=false)
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Column(name="doc_url",nullable=false)
	public String getDocURL() {
		return docURL;
	}

	public void setDocURL(String docURL) {
		this.docURL = docURL;
	}

	@Column(name="start_date",nullable=false)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name="end_date",nullable=false)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name="description",nullable=false)
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinTable(name = "training_grade", joinColumns = { 
			@JoinColumn(name = "training_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "grade_id", 
					nullable = false, updatable = false) })		
	public Set<Grade> getGrades() {
		return grades;
	}

	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}

	/**
	 * 
	 * @return trainings of an employee
	 */
	@OneToMany(mappedBy = "employeeTrainingId.training", cascade=CascadeType.ALL, targetEntity = EmployeeTraining.class)
	public List<EmployeeTraining> getEmployeeTrainings() {
		return this.employeeTrainings;
	}

	/**
	 * 
	 * @param employeeTrainings trainings of an employee
	 */
	public void setEmployeeTrainings(List<EmployeeTraining> employeeTrainings) {
		this.employeeTrainings = employeeTrainings;
	}
}
