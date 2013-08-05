package com.xpsoft.oa.model.system;


import com.google.gson.annotations.Expose;

/**
 * AppUserUpdate Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class AppUserUpdate extends com.xpsoft.core.model.BaseModel {

	@Expose
    protected Long id;  
	@Expose
	protected AppUser appUser;
	@Expose
	protected java.util.Date updateTime;


	/**
	 * Default Empty Constructor for class AppUserUpdate
	 */
	public AppUserUpdate () {
		super();
	}
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="updateTime" type="java.util.Date" length="19" not-null="true" unique="false"
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	
	/**
	 * Set the updateTime
	 * @spring.validator type="required"
	 */	
	public void setUpdateTime(java.util.Date aValue) {
		this.updateTime = aValue;
	}	

}
