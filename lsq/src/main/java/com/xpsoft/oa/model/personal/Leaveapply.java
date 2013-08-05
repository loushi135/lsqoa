package com.xpsoft.oa.model.personal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.xpsoft.core.model.BaseModel;

/**
 * Leaveapply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class Leaveapply extends BaseModel {

    protected Long id;
	protected Long userId;
	protected String userName;
	protected String deptName;
	protected Long deptId;
	protected java.util.Date startTime;
	protected String startAmOrPm;
	protected java.util.Date endTime;
	protected String endAmOrPm;
	protected String totalDays;
	protected String type;
	protected String other;
	protected Long applyUser;
	protected java.util.Date applyTime;
	protected Long processRunId;

	/**
	 * Default Empty Constructor for class Leaveapply
	 */
	public Leaveapply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class Leaveapply
	 */
	public Leaveapply (
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
	 * @hibernate.property column="userName" type="java.lang.String" length="128" not-null="true" unique="false"
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
	 * @hibernate.property column="deptName" type="java.lang.String" length="128" not-null="true" unique="false"
	 */
	public String getDeptName() {
		return this.deptName;
	}
	
	/**
	 * Set the deptName
	 * @spring.validator type="required"
	 */	
	public void setDeptName(String aValue) {
		this.deptName = aValue;
	}	

	/**
	 * 	 * @return Long
	 * @hibernate.property column="deptId" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public Long getDeptId() {
		return this.deptId;
	}
	
	/**
	 * Set the deptId
	 * @spring.validator type="required"
	 */	
	public void setDeptId(Long aValue) {
		this.deptId = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="startTime" type="java.util.Date" length="10" not-null="true" unique="false"
	 */
	public java.util.Date getStartTime() {
		return this.startTime;
	}
	
	/**
	 * Set the startTime
	 * @spring.validator type="required"
	 */	
	public void setStartTime(java.util.Date aValue) {
		this.startTime = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="startAmOrPm" type="java.lang.String" length="20" not-null="true" unique="false"
	 */
	public String getStartAmOrPm() {
		return this.startAmOrPm;
	}
	
	/**
	 * Set the startAmOrPm
	 * @spring.validator type="required"
	 */	
	public void setStartAmOrPm(String aValue) {
		this.startAmOrPm = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="endTime" type="java.util.Date" length="10" not-null="true" unique="false"
	 */
	public java.util.Date getEndTime() {
		return this.endTime;
	}
	
	/**
	 * Set the endTime
	 * @spring.validator type="required"
	 */	
	public void setEndTime(java.util.Date aValue) {
		this.endTime = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="endAmOrPm" type="java.lang.String" length="20" not-null="true" unique="false"
	 */
	public String getEndAmOrPm() {
		return this.endAmOrPm;
	}
	
	/**
	 * Set the endAmOrPm
	 * @spring.validator type="required"
	 */	
	public void setEndAmOrPm(String aValue) {
		this.endAmOrPm = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="totalDays" type="java.lang.String" length="20" not-null="true" unique="false"
	 */
	public String getTotalDays() {
		return this.totalDays;
	}
	
	/**
	 * Set the totalDays
	 * @spring.validator type="required"
	 */	
	public void setTotalDays(String aValue) {
		this.totalDays = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="type" type="java.lang.String" length="20" not-null="true" unique="false"
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Set the type
	 * @spring.validator type="required"
	 */	
	public void setType(String aValue) {
		this.type = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="other" type="java.lang.String" length="50" not-null="false" unique="false"
	 */
	public String getOther() {
		return this.other;
	}
	
	/**
	 * Set the other
	 */	
	public void setOther(String aValue) {
		this.other = aValue;
	}	

	/**
	 * 	 * @return Long
	 * @hibernate.property column="applyUser" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public Long getApplyUser() {
		return this.applyUser;
	}
	
	/**
	 * Set the applyUser
	 * @spring.validator type="required"
	 */	
	public void setApplyUser(Long aValue) {
		this.applyUser = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="applyTime" type="java.util.Date" length="19" not-null="true" unique="false"
	 */
	public java.util.Date getApplyTime() {
		return this.applyTime;
	}
	
	/**
	 * Set the applyTime
	 * @spring.validator type="required"
	 */	
	public void setApplyTime(java.util.Date aValue) {
		this.applyTime = aValue;
	}	

	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Leaveapply)) {
			return false;
		}
		Leaveapply rhs = (Leaveapply) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.userId, rhs.userId)
				.append(this.userName, rhs.userName)
				.append(this.deptName, rhs.deptName)
				.append(this.deptId, rhs.deptId)
				.append(this.startTime, rhs.startTime)
				.append(this.startAmOrPm, rhs.startAmOrPm)
				.append(this.endTime, rhs.endTime)
				.append(this.endAmOrPm, rhs.endAmOrPm)
				.append(this.totalDays, rhs.totalDays)
				.append(this.type, rhs.type)
				.append(this.other, rhs.other)
				.append(this.applyUser, rhs.applyUser)
				.append(this.applyTime, rhs.applyTime)
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
				.append(this.deptName) 
				.append(this.deptId) 
				.append(this.startTime) 
				.append(this.startAmOrPm) 
				.append(this.endTime) 
				.append(this.endAmOrPm) 
				.append(this.totalDays) 
				.append(this.type) 
				.append(this.other) 
				.append(this.applyUser) 
				.append(this.applyTime) 
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
				.append("deptName", this.deptName) 
				.append("deptId", this.deptId) 
				.append("startTime", this.startTime) 
				.append("startAmOrPm", this.startAmOrPm) 
				.append("endTime", this.endTime) 
				.append("endAmOrPm", this.endAmOrPm) 
				.append("totalDays", this.totalDays) 
				.append("type", this.type) 
				.append("other", this.other) 
				.append("applyUser", this.applyUser) 
				.append("applyTime", this.applyTime) 
				.toString();
	}



}
