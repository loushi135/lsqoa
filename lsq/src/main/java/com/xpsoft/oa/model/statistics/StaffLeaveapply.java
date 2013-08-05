package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.xpsoft.core.model.BaseModel;

/**
 * StaffLeaveapply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class StaffLeaveapply extends BaseModel {

    protected Long id;
	protected String applyName;
	protected String companyName;
	protected String sex;
	protected String deptName;
	protected String workPosition;
	protected java.util.Date comeInDate;
	protected String leaveReason;
	protected java.util.Date applyDate;
	protected java.util.Date leaveDate;
	protected String signName;
	protected java.util.Date signDate;
	protected Long processRunId;

	/**
	 * Default Empty Constructor for class StaffLeaveapply
	 */
	public StaffLeaveapply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class StaffLeaveapply
	 */
	public StaffLeaveapply (
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
	 * 申请人	 * @return String
	 * @hibernate.property column="applyName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getApplyName() {
		return this.applyName;
	}
	
	/**
	 * Set the applyName
	 */	
	public void setApplyName(String aValue) {
		this.applyName = aValue;
	}	

	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
	}

	/**
	 * 公司名称	 * @return String
	 * @hibernate.property column="companyName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getCompanyName() {
		return this.companyName;
	}
	
	/**
	 * Set the companyName
	 */	
	public void setCompanyName(String aValue) {
		this.companyName = aValue;
	}	


	/**
	 * 部门	 * @return String
	 * @hibernate.property column="deptName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getDeptName() {
		return this.deptName;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * Set the deptName
	 */	
	public void setDeptName(String aValue) {
		this.deptName = aValue;
	}	

	/**
	 * 职位	 * @return String
	 * @hibernate.property column="workPosition" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getWorkPosition() {
		return this.workPosition;
	}
	
	/**
	 * Set the workPosition
	 */	
	public void setWorkPosition(String aValue) {
		this.workPosition = aValue;
	}	

	/**
	 * 进公司日期	 * @return java.util.Date
	 * @hibernate.property column="comeInDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getComeInDate() {
		return this.comeInDate;
	}
	
	/**
	 * Set the comeInDate
	 */	
	public void setComeInDate(java.util.Date aValue) {
		this.comeInDate = aValue;
	}	

	/**
	 * 离职原因	 * @return String
	 * @hibernate.property column="leaveReason" type="java.lang.String" length="1000" not-null="false" unique="false"
	 */
	public String getLeaveReason() {
		return this.leaveReason;
	}
	
	/**
	 * Set the leaveReason
	 */	
	public void setLeaveReason(String aValue) {
		this.leaveReason = aValue;
	}	

	/**
	 * 离职申请日期	 * @return java.util.Date
	 * @hibernate.property column="applyDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getApplyDate() {
		return this.applyDate;
	}
	
	/**
	 * Set the applyDate
	 */	
	public void setApplyDate(java.util.Date aValue) {
		this.applyDate = aValue;
	}	

	/**
	 * 正式离职日期	 * @return java.util.Date
	 * @hibernate.property column="leaveDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getLeaveDate() {
		return this.leaveDate;
	}
	
	/**
	 * Set the leaveDate
	 */	
	public void setLeaveDate(java.util.Date aValue) {
		this.leaveDate = aValue;
	}	

	/**
	 * 签名	 * @return String
	 * @hibernate.property column="signName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getSignName() {
		return this.signName;
	}
	
	/**
	 * Set the signName
	 */	
	public void setSignName(String aValue) {
		this.signName = aValue;
	}	

	/**
	 * 签名日期	 * @return java.util.Date
	 * @hibernate.property column="signDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getSignDate() {
		return this.signDate;
	}
	
	/**
	 * Set the signDate
	 */	
	public void setSignDate(java.util.Date aValue) {
		this.signDate = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof StaffLeaveapply)) {
			return false;
		}
		StaffLeaveapply rhs = (StaffLeaveapply) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.applyName, rhs.applyName)
				.append(this.companyName, rhs.companyName)
				.append(this.sex, rhs.sex)
				.append(this.deptName, rhs.deptName)
				.append(this.workPosition, rhs.workPosition)
				.append(this.comeInDate, rhs.comeInDate)
				.append(this.leaveReason, rhs.leaveReason)
				.append(this.applyDate, rhs.applyDate)
				.append(this.leaveDate, rhs.leaveDate)
				.append(this.signName, rhs.signName)
				.append(this.signDate, rhs.signDate)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.applyName) 
				.append(this.companyName) 
				.append(this.sex) 
				.append(this.deptName) 
				.append(this.workPosition) 
				.append(this.comeInDate) 
				.append(this.leaveReason) 
				.append(this.applyDate) 
				.append(this.leaveDate) 
				.append(this.signName) 
				.append(this.signDate) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("applyName", this.applyName) 
				.append("companyName", this.companyName) 
				.append("sex", this.sex) 
				.append("deptName", this.deptName) 
				.append("workPosition", this.workPosition) 
				.append("comeInDate", this.comeInDate) 
				.append("leaveReason", this.leaveReason) 
				.append("applyDate", this.applyDate) 
				.append("leaveDate", this.leaveDate) 
				.append("signName", this.signName) 
				.append("signDate", this.signDate) 
				.toString();
	}



}
