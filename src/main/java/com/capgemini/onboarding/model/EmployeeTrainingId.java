package com.capgemini.onboarding.model;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;

@Embeddable
@Audited
public class EmployeeTrainingId  implements java.io.Serializable  {
	
	private Employee employee;
	private Training training;
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	public Training getTraining() {
		return training;
	}
	
	public void setTraining(Training training) {
		this.training = training;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeTrainingId that = (EmployeeTrainingId) o;

        if (employee != null ? !employee.equals(that.employee) : that.employee != null) return false;
        if (training != null ? !training.equals(that.training) : that.training != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (employee != null ? employee.hashCode() : 0);
        result = 31 * result + (training != null ? training.hashCode() : 0);
        return result;
    }
}
