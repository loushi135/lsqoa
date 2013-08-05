package com.xpsoft.oa.model.statistics;


import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * ProjectPercentage Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ProjectPercentage extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;  
	@Expose
	protected java.math.BigDecimal percentage;
	@Expose
	protected ProjectNew project;
	@Expose
	protected Date createtime;
	@Expose
	protected AppUser createUser;
	/**
	 * Default Empty Constructor for class ProjectPercentage
	 */
	public ProjectPercentage () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ProjectPercentage
	 */
	public ProjectPercentage (
		 Long id
        ) {
		this.setId(id);
    }


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProjectNew getProject() {
		return project;
	}

	public void setProject(ProjectNew project) {
		this.project = project;
	}

	/**
	 * 费率	 * @return java.math.BigDecimal
	 * @hibernate.property column="percentage" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getPercentage() {
		return this.percentage;
	}
	
	/**
	 * Set the percentage
	 */	
	public void setPercentage(java.math.BigDecimal aValue) {
		this.percentage = aValue;
	}	

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public AppUser getCreateUser() {
		return createUser;
	}

	public void setCreateUser(AppUser createUser) {
		this.createUser = createUser;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ProjectPercentage)) {
			return false;
		}
		ProjectPercentage rhs = (ProjectPercentage) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.percentage, rhs.percentage)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.percentage) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("percentage", this.percentage) 
				.toString();
	}



}
