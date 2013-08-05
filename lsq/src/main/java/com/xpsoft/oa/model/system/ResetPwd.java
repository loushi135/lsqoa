package com.xpsoft.oa.model.system;


import com.google.gson.annotations.Expose;

/**
 * ResetPwd Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ResetPwd extends com.xpsoft.core.model.BaseModel {

	@Expose
    protected Long id;  
	@Expose
	protected String userFullName;
	@Expose
	protected String userLoginName;
	@Expose
	protected String resetPWD;
	@Expose
	protected java.util.Date timeCreate;
	@Expose
	protected AppUser opeaterUser;

	/**
	 * Default Empty Constructor for class ResetPwd
	 */
	public ResetPwd () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ResetPwd
	 */
	public ResetPwd (
		 Long in_id
        ) {
		this.setId(in_id);
    }

    

	/**
	 * 	 * @return Long
     * @hibernate.id column="id" type="java.lang.Long" generator-class="native"
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Set the id
	 */	
	public void setId(Long aValue) {
		this.id = aValue;
	}	

	/**
	 * 用户姓名	 * @return String
	 * @hibernate.property column="userFullName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getUserFullName() {
		return this.userFullName;
	}
	
	/**
	 * Set the userFullName
	 */	
	public void setUserFullName(String aValue) {
		this.userFullName = aValue;
	}	

	/**
	 * 用户名	 * @return String
	 * @hibernate.property column="userLoginName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getUserLoginName() {
		return this.userLoginName;
	}
	
	/**
	 * Set the userLoginName
	 */	
	public void setUserLoginName(String aValue) {
		this.userLoginName = aValue;
	}	

	/**
	 * 重置的密码	 * @return String
	 * @hibernate.property column="resetPWD" type="java.lang.String" length="10" not-null="false" unique="false"
	 */
	public String getResetPWD() {
		return this.resetPWD;
	}
	
	/**
	 * Set the resetPWD
	 */	
	public void setResetPWD(String aValue) {
		this.resetPWD = aValue;
	}	

	/**
	 * 创建时间	 * @return java.util.Date
	 * @hibernate.property column="timeCreate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getTimeCreate() {
		return this.timeCreate;
	}
	
	/**
	 * Set the timeCreate
	 */	
	public void setTimeCreate(java.util.Date aValue) {
		this.timeCreate = aValue;
	}

	public AppUser getOpeaterUser() {
		return opeaterUser;
	}

	public void setOpeaterUser(AppUser opeaterUser) {
		this.opeaterUser = opeaterUser;
	}	
}
