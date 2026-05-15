package com.capgemini.onboarding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name="CG_ENTITY")
@Audited
public class CGEntityDetail implements Comparable<CGEntityDetail> {

	private Integer cgEntityId;
	private String cgEntityName;
	//private String corpid;
	

	/**
	 * @return the cgEntityId
	 */
	@Id
	@Column(name = "cg_entity_id", nullable = false)
	@GeneratedValue
	public Integer getCgEntityId() {
		return cgEntityId;
	}

	/**
	 * @param cgEntityId
	 *            the capgemini entityId to set
	 */
	public void setCgEntityId(Integer cgEntityId) {
		this.cgEntityId = cgEntityId;
	}

	/**
	 * @return the cgEntityName
	 */
	@Column(name = "cg_entity_name", nullable = false)
	public String getCgEntityName() {
		return cgEntityName;
	}

	/**
	 * @param cgEntityName
	 *            the capgemini entityName to set
	 */
	public void setCgEntityName(String cgEntityName) {
		this.cgEntityName = cgEntityName;
	}
	
	/*@Column(name = "corpid", nullable = false)
	public String getCorpid() {
		return corpid;
	}

	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}*/

	@Override
	public int compareTo(CGEntityDetail cgEntityDetail) {
		return this.getCgEntityName().compareTo(cgEntityDetail.getCgEntityName());
	}

}
