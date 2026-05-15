
package com.capgemini.onboarding.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name="offshore_em")
@Audited
public class OffshoreEm implements Comparable<OffshoreEm> {

	private Integer offshoreEmId;
	private String offshoreEmName;
	private Boolean isDeleted;
	
	/**
	 * @return the offshoreEmId
	 */
	@Id
	@Column(name = "offshore_em_id", nullable = false)
	@GeneratedValue	
	public Integer getOffshoreEmId() {
		return offshoreEmId;
	}

	/**
	 * @param offshoreEmId to set
	 *           
	 */
	public void setOffshoreEmId(Integer offshoreEmId) {
 		this.offshoreEmId = offshoreEmId;
	}


	/**
	 * @return the offshoreEmName
	 */
	
	@Column(name = "offshore_em_name", nullable = false)
	public String getOffshoreEmName() {
		return offshoreEmName;
	}

	
	/**
	 * @param offshoreEmName to set
	 */
	public void setOffshoreEmName(String offshoreEmName) {
		this.offshoreEmName = offshoreEmName;
	}

	@Column(name = "isDeleted", nullable = true)
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public int compareTo(OffshoreEm offshoreEm) {
		return this.getOffshoreEmName().compareTo(offshoreEm.getOffshoreEmName());
	 
	}

	
	
}

