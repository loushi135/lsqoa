package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.hrm.Job;
import com.xpsoft.oa.model.system.Department;

/**
 * StaffEntryapply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * 入职申请表
 */
public class StaffEntryapply extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;  
	@Expose
	protected String companyName;
	@Expose
	protected Department dept;
	@Expose
	protected String entryUserName;
	@Expose
	protected Job job;
	@Expose
	protected String contact;
	@Expose
	protected java.util.Date entryDate;
	@Expose
	protected Long processRunId;

	/**
	 * Default Empty Constructor for class StaffEntryapply
	 */
	public StaffEntryapply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class StaffEntryapply
	 */
	public StaffEntryapply (
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
	 * 入职公司	 * @return String
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
	 * 姓名	 * @return String
	 * @hibernate.property column="entryUserName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getEntryUserName() {
		return this.entryUserName;
	}
	
	/**
	 * Set the entryUserName
	 */	
	public void setEntryUserName(String aValue) {
		this.entryUserName = aValue;
	}	


	/**
	 * 联系方式	 * @return String
	 * @hibernate.property column="contact" type="java.lang.String" length="500" not-null="false" unique="false"
	 */
	public String getContact() {
		return this.contact;
	}
	
	/**
	 * Set the contact
	 */	
	public void setContact(String aValue) {
		this.contact = aValue;
	}	

	/**
	 * 拟报到日期	 * @return java.util.Date
	 * @hibernate.property column="entryDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getEntryDate() {
		return this.entryDate;
	}
	
	/**
	 * Set the entryDate
	 */	
	public void setEntryDate(java.util.Date aValue) {
		this.entryDate = aValue;
	}	

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
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
		if (!(object instanceof StaffEntryapply)) {
			return false;
		}
		StaffEntryapply rhs = (StaffEntryapply) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.companyName, rhs.companyName)
				.append(this.entryUserName, rhs.entryUserName)
				.append(this.contact, rhs.contact)
				.append(this.entryDate, rhs.entryDate)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.companyName) 
				.append(this.entryUserName) 
				.append(this.contact) 
				.append(this.entryDate) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("companyName", this.companyName) 
				.append("entryUserName", this.entryUserName) 
				.append("contact", this.contact) 
				.append("entryDate", this.entryDate) 
				.toString();
	}



}
