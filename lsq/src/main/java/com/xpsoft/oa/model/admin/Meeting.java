package com.xpsoft.oa.model.admin;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.xpsoft.core.model.BaseModel;

/**
 * Meeting Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class Meeting extends BaseModel {

    protected Long id;
	protected String meetingNo;
	protected String department;
	protected String applyUser;
	protected Long userId;
	protected java.util.Date startTime;
	protected java.util.Date entTime;
	protected String meetingTitle;
	protected Integer personNum;
	protected Short meetingType;
	protected String meetingRoom;
	protected String meetingRequire;
	protected Long processRunId;

	/**
	 * Default Empty Constructor for class Meeting
	 */
	public Meeting () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class Meeting
	 */
	public Meeting (
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
	 * @hibernate.property column="meetingNo" type="java.lang.String" length="64" not-null="true" unique="false"
	 */
	public String getMeetingNo() {
		return this.meetingNo;
	}
	
	/**
	 * Set the meetingNo
	 * @spring.validator type="required"
	 */	
	public void setMeetingNo(String aValue) {
		this.meetingNo = aValue;
	}	

	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
	}

	/**
	 * 	 * @return String
	 * @hibernate.property column="department" type="java.lang.String" length="64" not-null="true" unique="false"
	 */
	public String getDepartment() {
		return this.department;
	}
	
	/**
	 * Set the department
	 * @spring.validator type="required"
	 */	
	public void setDepartment(String aValue) {
		this.department = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="applyUser" type="java.lang.String" length="64" not-null="true" unique="false"
	 */
	public String getApplyUser() {
		return this.applyUser;
	}
	
	/**
	 * Set the applyUser
	 * @spring.validator type="required"
	 */	
	public void setApplyUser(String aValue) {
		this.applyUser = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="startTime" type="java.util.Date" length="19" not-null="true" unique="false"
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
	 * 	 * @return java.util.Date
	 * @hibernate.property column="entTime" type="java.util.Date" length="19" not-null="true" unique="false"
	 */
	public java.util.Date getEntTime() {
		return this.entTime;
	}
	
	/**
	 * Set the entTime
	 * @spring.validator type="required"
	 */	
	public void setEntTime(java.util.Date aValue) {
		this.entTime = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="meetingTitle" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getMeetingTitle() {
		return this.meetingTitle;
	}
	
	/**
	 * Set the meetingTitle
	 * @spring.validator type="required"
	 */	
	public void setMeetingTitle(String aValue) {
		this.meetingTitle = aValue;
	}	

	/**
	 * 	 * @return Integer
	 * @hibernate.property column="personNum" type="java.lang.Integer" length="10" not-null="true" unique="false"
	 */
	public Integer getPersonNum() {
		return this.personNum;
	}
	
	/**
	 * Set the personNum
	 * @spring.validator type="required"
	 */	
	public void setPersonNum(Integer aValue) {
		this.personNum = aValue;
	}	

	/**
	 * 	 * @return Short
	 * @hibernate.property column="meetingType" type="java.lang.Short" length="5" not-null="true" unique="false"
	 */
	public Short getMeetingType() {
		return this.meetingType;
	}
	
	/**
	 * Set the meetingType
	 * @spring.validator type="required"
	 */	
	public void setMeetingType(Short aValue) {
		this.meetingType = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="meetingRoom" type="java.lang.String" length="64" not-null="true" unique="false"
	 */
	public String getMeetingRoom() {
		return this.meetingRoom;
	}
	
	/**
	 * Set the meetingRoom
	 * @spring.validator type="required"
	 */	
	public void setMeetingRoom(String aValue) {
		this.meetingRoom = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="meetingRequire" type="java.lang.String" length="1000" not-null="false" unique="false"
	 */
	public String getMeetingRequire() {
		return this.meetingRequire;
	}
	
	/**
	 * Set the meetingRequire
	 */	
	public void setMeetingRequire(String aValue) {
		this.meetingRequire = aValue;
	}	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Meeting)) {
			return false;
		}
		Meeting rhs = (Meeting) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.meetingNo, rhs.meetingNo)
				.append(this.department, rhs.department)
				.append(this.applyUser, rhs.applyUser)
				.append(this.startTime, rhs.startTime)
				.append(this.entTime, rhs.entTime)
				.append(this.meetingTitle, rhs.meetingTitle)
				.append(this.personNum, rhs.personNum)
				.append(this.meetingType, rhs.meetingType)
				.append(this.meetingRoom, rhs.meetingRoom)
				.append(this.meetingRequire, rhs.meetingRequire)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.meetingNo) 
				.append(this.department) 
				.append(this.applyUser) 
				.append(this.startTime) 
				.append(this.entTime) 
				.append(this.meetingTitle) 
				.append(this.personNum) 
				.append(this.meetingType) 
				.append(this.meetingRoom) 
				.append(this.meetingRequire) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("meetingNo", this.meetingNo) 
				.append("department", this.department) 
				.append("applyUser", this.applyUser) 
				.append("startTime", this.startTime) 
				.append("entTime", this.entTime) 
				.append("meetingTitle", this.meetingTitle) 
				.append("personNum", this.personNum) 
				.append("meetingType", this.meetingType) 
				.append("meetingRoom", this.meetingRoom) 
				.append("meetingRequire", this.meetingRequire) 
				.toString();
	}



}
