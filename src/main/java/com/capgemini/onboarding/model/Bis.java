package com.capgemini.onboarding.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "bis")
@Audited
public class Bis implements java.io.Serializable, Comparable<Bis>{
	
	private int bis_id;
	private String bis_Name;
	private String bundle_name;
	private Boolean isDeleted;

	/*@ManyToMany(mappedBy = "bis")
	@JsonIgnore
	private List <PrimaryProgram> ppList;*/
	
	
	@Id
	@GeneratedValue
	@Column(name = "bis_id", nullable = true)
	public int getBis_id() {
		return bis_id;
	}

	public void setBis_id(int bis_id) {
		this.bis_id = bis_id;
	}

	
	@Column(name = "bis_name", nullable = true)
	public String getBis_Name() {
		return bis_Name;
	}

	/**
	 * @param bisName
	 *            the bisName to set
	 */
	public void setBis_Name(String bis_Name) {
		this.bis_Name = bis_Name;
	}
	
	@Column(name = "bundle_name", nullable = true)
	public String getBundle_name() {
		return bundle_name;
	}

	public void setBundle_name(String bundle_name) {
		this.bundle_name = bundle_name;
	}
	
	@Column(name = "isDeleted", nullable = true)
	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	/*public List<PrimaryProgram> getPpList() {
		return ppList;
	}

	public void setPpList(List<PrimaryProgram> ppList) {
		this.ppList = ppList;
	}*/

	@Override
	public int compareTo(Bis bis) {
		return this.getBis_Name().compareTo(bis.getBis_Name());
	
	}


	
}
