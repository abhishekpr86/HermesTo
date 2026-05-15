package com.capgemini.onboarding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name="ENTITY")
@Audited
public class EntityDetail implements Comparable<EntityDetail>{
	
	private Integer entityId;	
	private String entityName;
	
	/**
	 * @return the entityId
	 */
	@Id
	@Column(name="entity_id", nullable=false)
	@GeneratedValue
	public Integer getEntityId() {
		return entityId;
	}
	/**
	 * @param entityId the entityId to set
	 */
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}
	/**
	 * @return the entityName
	 */
	@Column(name="entity_name", nullable=false)
	public String getEntityName() {
		return entityName;
	}
	/**
	 * @param entityName the entityName to set
	 */
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}	
	
	@Override
    public int compareTo(EntityDetail entityDetail) {
		return this.getEntityName().compareTo(entityDetail.getEntityName());
      }
}
