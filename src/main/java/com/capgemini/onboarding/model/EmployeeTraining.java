package com.capgemini.onboarding.model;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;

@NamedQueries(value = {
		@NamedQuery(name="updateAttendedDate",
				query="update EmployeeTraining set attendedDate =:attendedDate where employeeTrainingId.training.trainingId = :trainingId and employeeTrainingId.employee.empId = :empId"
				)})


@Entity
@Table(name="EMPLOYEE_TRAINING")
@AssociationOverrides({
	@AssociationOverride(name = "employeeTrainingId.employee", 
		joinColumns = @JoinColumn(name = "emp_id")),
	@AssociationOverride(name = "employeeTrainingId.training", 
		joinColumns = @JoinColumn(name = "training_id")) })
@Audited
public class EmployeeTraining  implements java.io.Serializable  {
	
	private EmployeeTrainingId employeeTrainingId = new EmployeeTrainingId();
	private Date allocationDate;
	private Date attendedDate;	
	
	@EmbeddedId
	public EmployeeTrainingId getEmployeeTrainingId() {
		return employeeTrainingId;
	}

	public void setEmployeeTrainingId(EmployeeTrainingId employeeTrainingId) {
		this.employeeTrainingId = employeeTrainingId;
	}

	@Transient
	public Employee getEmployee() {
		return employeeTrainingId.getEmployee();
	}
	
	
	public void setEmployee(Employee employee) {
		getEmployeeTrainingId().setEmployee(employee);
	}
	
	@Transient
	public Training getTraining() {
		return employeeTrainingId.getTraining();
	}
	
	public void setTraining(Training training) {
		getEmployeeTrainingId().setTraining(training);
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ALLOCATION_DATE", nullable = false, length = 10)	
	public Date getAllocationDate() {
		return allocationDate;
	}

	public void setAllocationDate(Date allocationDate) {
		this.allocationDate = allocationDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ATTENDED_DATE", length = 10)
	public Date getAttendedDate() {
		return this.attendedDate;
	}	

	public void setAttendedDate(Date attendedDate) {
		this.attendedDate = attendedDate;
	}	

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		EmployeeTraining that = (EmployeeTraining) o;

		if (getEmployeeTrainingId() != null ? !getEmployeeTrainingId().equals(that.getEmployeeTrainingId())
				: that.getEmployeeTrainingId() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getEmployeeTrainingId() != null ? getEmployeeTrainingId().hashCode() : 0);
	}
}
