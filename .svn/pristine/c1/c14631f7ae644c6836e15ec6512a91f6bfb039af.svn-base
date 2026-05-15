package com.capgemini.onboarding.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@NamedQueries(value = {		
		@NamedQuery(name="listPMOByBis",
		query="from BisPMOMap b where b.bis_id =:bis_id"
		)	
	})
@Entity
@Table(name = "bis_pmomap")
public class BisPMOMap {
	
	private int pmo_id;
	private int  bis_id;
	private String pmo_name;
	private String pmo_email;
	
	@Id
	@Column(name = "pmo_id", nullable = false)
	public int getPmo_id() {
		return pmo_id;
	}
	public void setPmo_id(int pmo_id) {
		this.pmo_id = pmo_id;
	}
	
	@Column(name = "bis_id", nullable = false)
	public int getBis_id() {
		return bis_id;
	}
	public void setBis_id(int bis_id) {
		this.bis_id = bis_id;
	}
	
	@Column(name = "pmo_name", nullable = false)
	public String getPmo_name() {
		return pmo_name;
	}
	public void setPmo_name(String pmo_name) {
		this.pmo_name = pmo_name;
	}
	
	@Column(name = "pmo_email", nullable = false)
	public String getPmo_email() {
		return pmo_email;
	}
	public void setPmo_email(String pmo_email) {
		this.pmo_email = pmo_email;
	}
	

}
