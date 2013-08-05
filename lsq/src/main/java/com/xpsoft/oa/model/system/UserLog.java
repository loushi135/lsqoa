package com.xpsoft.oa.model.system;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * UserLog Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class UserLog extends com.xpsoft.core.model.BaseModel {

	public static final String ACTION_IN = "IN";
	public static final String ACTION_OUT = "OUT";
    protected Long id;
	protected Long userId;
	protected String userName;
	protected String fullname;
	protected String action;
	protected java.util.Date actionTime;
	protected String status;
	protected String ip;
	private String userAgentStr;


	/**
	 * Default Empty Constructor for class UserLog
	 */
	public UserLog () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class UserLog
	 */
	public UserLog (
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
	 * 用户id	 * @return Long
	 * @hibernate.property column="userId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getUserId() {
		return this.userId;
	}
	
	/**
	 * Set the userId
	 */	
	public void setUserId(Long aValue) {
		this.userId = aValue;
	}	

	/**
	 * 用户姓名	 * @return String
	 * @hibernate.property column="userName" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getUserName() {
		return this.userName;
	}
	
	/**
	 * Set the userName
	 */	
	public void setUserName(String aValue) {
		this.userName = aValue;
	}	

	/**
	 * 动作	 * @return String
	 * @hibernate.property column="action" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getAction() {
		return this.action;
	}
	
	/**
	 * Set the action
	 */	
	public void setAction(String aValue) {
		this.action = aValue;
	}	

	/**
	 * 时间	 * @return java.util.Date
	 * @hibernate.property column="actionTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getActionTime() {
		return this.actionTime;
	}
	
	/**
	 * Set the actionTime
	 */	
	public void setActionTime(java.util.Date aValue) {
		this.actionTime = aValue;
	}	

	/**
	 * 状态	 * @return String
	 * @hibernate.property column="status" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getStatus() {
		return this.status;
	}
	
	/**
	 * Set the status
	 */	
	public void setStatus(String aValue) {
		this.status = aValue;
	}	

	/**
	 * ip	 * @return String
	 * @hibernate.property column="ip" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getIp() {
		return this.ip;
	}
	
	/**
	 * Set the ip
	 */	
	public void setIp(String aValue) {
		this.ip = aValue;
	}	

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public String getUserAgentStr() {
		return userAgentStr;
	}
	public void setUserAgentStr(String userAgentStr) {
		this.userAgentStr = userAgentStr;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof UserLog)) {
			return false;
		}
		UserLog rhs = (UserLog) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.userId, rhs.userId)
				.append(this.userName, rhs.userName)
				.append(this.action, rhs.action)
				.append(this.actionTime, rhs.actionTime)
				.append(this.status, rhs.status)
				.append(this.ip, rhs.ip)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.userId) 
				.append(this.userName) 
				.append(this.action) 
				.append(this.actionTime) 
				.append(this.status) 
				.append(this.ip) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("userId", this.userId) 
				.append("userName", this.userName) 
				.append("action", this.action) 
				.append("actionTime", this.actionTime) 
				.append("status", this.status) 
				.append("ip", this.ip) 
				.toString();
	}



}
