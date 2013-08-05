package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.hrm.Job;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Department;

/**
 * StaffChangejobapply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class StaffChangejobapply extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;  
	@Expose
	protected String companyName;
	@Expose
	protected AppUser changeJobUser;
	@Expose
	protected Department oldDept;
	@Expose
	protected Job oldJob;
	@Expose
	protected Department newDept;
	@Expose
	protected Job newJob;
	@Expose
	protected java.util.Date changeJobDate;
	@Expose
	protected String changeJobReason;
	@Expose
	protected Long processRunId;


	/**
	 * Default Empty Constructor for class StaffChangejobapply
	 */
	public StaffChangejobapply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class StaffChangejobapply
	 */
	public StaffChangejobapply (
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

	public AppUser getChangeJobUser() {
		return changeJobUser;
	}

	public void setChangeJobUser(AppUser changeJobUser) {
		this.changeJobUser = changeJobUser;
	}

	public Department getOldDept() {
		return oldDept;
	}

	public void setOldDept(Department oldDept) {
		this.oldDept = oldDept;
	}

	public Job getOldJob() {
		return oldJob;
	}

	public void setOldJob(Job oldJob) {
		this.oldJob = oldJob;
	}

	public Department getNewDept() {
		return newDept;
	}

	public void setNewDept(Department newDept) {
		this.newDept = newDept;
	}

	public Job getNewJob() {
		return newJob;
	}

	public void setNewJob(Job newJob) {
		this.newJob = newJob;
	}

	/**
	 * 转岗日期	 * @return java.util.Date
	 * @hibernate.property column="changeJobDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getChangeJobDate() {
		return this.changeJobDate;
	}
	
	/**
	 * Set the changeJobDate
	 */	
	public void setChangeJobDate(java.util.Date aValue) {
		this.changeJobDate = aValue;
	}	

	/**
	 * 转岗原因	 * @return String
	 * @hibernate.property column="changeJobReason" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getChangeJobReason() {
		return this.changeJobReason;
	}
	
	/**
	 * Set the changeJobReason
	 */	
	public void setChangeJobReason(String aValue) {
		this.changeJobReason = aValue;
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
		if (!(object instanceof StaffChangejobapply)) {
			return false;
		}
		StaffChangejobapply rhs = (StaffChangejobapply) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.companyName, rhs.companyName)
				.append(this.changeJobDate, rhs.changeJobDate)
				.append(this.changeJobReason, rhs.changeJobReason)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.companyName) 
				.append(this.changeJobDate) 
				.append(this.changeJobReason) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("companyName", this.companyName) 
				.append("changeJobDate", this.changeJobDate) 
				.append("changeJobReason", this.changeJobReason) 
				.toString();
	}



}
