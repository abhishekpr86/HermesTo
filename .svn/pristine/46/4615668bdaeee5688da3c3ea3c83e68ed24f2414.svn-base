package com.capgemini.onboarding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.envers.Audited;

@Entity
@Table(name="indus_goals")
public class IndusGoals implements Comparable<IndusGoals>{
	
	private int indusGoalId;
	private String indusGoalName;
	
	@Id
	@Column(name = "indus_goal_id",nullable = false)
	@GeneratedValue
	public int getIndusGoalId() {
		return indusGoalId;
	}
	public void setIndusGoalId(int indusGoalId) {
		this.indusGoalId = indusGoalId;
	}
	
	@Column(name = "indus_goal_name", nullable = false)
	public String getIndusGoalName() {
		return indusGoalName;
	}
	public void setIndusGoalName(String indusGoalName) {
		this.indusGoalName = indusGoalName;
	}
	
	@Override
	public int compareTo(IndusGoals indusGoal) {
		return this.getIndusGoalName().compareTo(indusGoal.getIndusGoalName());
	}

}
