package com.xpsoft.oa.model.system;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * UserMessage Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */

public class LoginSituation extends com.xpsoft.core.model.BaseModel {

    protected Long id;
	protected String message;
	protected Long userId;
	protected String userName;
	protected String ip;
	protected java.util.Date actionTime;


	/**
	 * Default Empty Constructor for class UserMessage
	 */
	public LoginSituation () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class UserMessage
	 */
	public LoginSituation (
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
	 * 	 * @return String
	 * @hibernate.property column="message" type="java.lang.String" length="500" not-null="true" unique="false"
	 */
	public String getMessage() {
		return this.message;
	}
	
	/**
	 * Set the message
	 * @spring.validator type="required"
	 */	
	public void setMessage(String aValue) {
		this.message = aValue;
	}	

	/**
	 * 	 * @return Long
	 * @hibernate.property column="userId" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public Long getUserId() {
		return this.userId;
	}
	
	/**
	 * Set the userId
	 * @spring.validator type="required"
	 */	
	public void setUserId(Long aValue) {
		this.userId = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="userName" type="java.lang.String" length="500" not-null="true" unique="false"
	 */
	public String getUserName() {
		return this.userName;
	}
	
	/**
	 * Set the userName
	 * @spring.validator type="required"
	 */	
	public void setUserName(String aValue) {
		this.userName = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="ip" type="java.lang.String" length="500" not-null="false" unique="false"
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

	/**
	 * 	 * @return java.util.Date
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
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof LoginSituation)) {
			return false;
		}
		LoginSituation rhs = (LoginSituation) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.message, rhs.message)
				.append(this.userId, rhs.userId)
				.append(this.userName, rhs.userName)
				.append(this.ip, rhs.ip)
				.append(this.actionTime, rhs.actionTime)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.message) 
				.append(this.userId) 
				.append(this.userName) 
				.append(this.ip) 
				.append(this.actionTime) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("message", this.message) 
				.append("userId", this.userId) 
				.append("userName", this.userName) 
				.append("ip", this.ip) 
				.append("actionTime", this.actionTime) 
				.toString();
	}



}
