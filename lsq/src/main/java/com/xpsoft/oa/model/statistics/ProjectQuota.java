package com.xpsoft.oa.model.statistics;


import java.util.Date;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * ProjectQuota Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ProjectQuota extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;  
	@Expose
	protected java.math.BigDecimal quota;
	@Expose
	protected ProjectNew project;
	@Expose
	protected Date createtime;
	@Expose
	protected AppUser createUser;

	/**
	 * Default Empty Constructor for class ProjectQuota
	 */
	public ProjectQuota () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ProjectQuota
	 */
	public ProjectQuota (
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
	 * 额度	 * @return java.math.BigDecimal
	 * @hibernate.property column="quota" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getQuota() {
		return this.quota;
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
	 * Set the quota
	 */	
	public void setQuota(java.math.BigDecimal aValue) {
		this.quota = aValue;
	}	

}
