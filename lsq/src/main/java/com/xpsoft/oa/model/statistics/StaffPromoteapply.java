package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.hrm.Job;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Department;

/**
 * StaffPromoteapply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * 晋升申请
 */
public class StaffPromoteapply extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;  
	@Expose
	protected AppUser promoteUser;
	@Expose
	protected Department oldDept;
	@Expose
	protected Job oldJob;
	@Expose
	protected Department newDept;
	@Expose
	protected Job newJob;
	@Expose
	protected java.util.Date activeDate;
	@Expose
	protected String promoteReason;
	@Expose
	protected String advantageOrChange;
	@Expose
	protected String professional;
	@Expose
	protected String professionalAbout;
	@Expose
	protected String mixedAbility;
	@Expose
	protected String mixedAbilityAbout;
	@Expose
	protected Long processRunId;

	/**
	 * Default Empty Constructor for class StaffPromoteapply
	 */
	public StaffPromoteapply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class StaffPromoteapply
	 */
	public StaffPromoteapply (
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


	public AppUser getPromoteUser() {
		return promoteUser;
	}

	public void setPromoteUser(AppUser promoteUser) {
		this.promoteUser = promoteUser;
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
	 * 生效日期	 * @return java.util.Date
	 * @hibernate.property column="activeDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getActiveDate() {
		return this.activeDate;
	}
	
	/**
	 * Set the activeDate
	 */	
	public void setActiveDate(java.util.Date aValue) {
		this.activeDate = aValue;
	}	

	
	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
	}

	/**
	 * 晋升理由	 * @return String
	 * @hibernate.property column="promoteReason" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getPromoteReason() {
		return this.promoteReason;
	}
	
	/**
	 * Set the promoteReason
	 */	
	public void setPromoteReason(String aValue) {
		this.promoteReason = aValue;
	}	

	/**
	 * 个人优点和需改进的方面	 * @return String
	 * @hibernate.property column="advantageOrChange" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getAdvantageOrChange() {
		return this.advantageOrChange;
	}
	
	/**
	 * Set the advantageOrChange
	 */	
	public void setAdvantageOrChange(String aValue) {
		this.advantageOrChange = aValue;
	}	

	/**
	 * 专业能力	 * @return String
	 * @hibernate.property column="professional" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getProfessional() {
		return this.professional;
	}
	
	/**
	 * Set the professional
	 */	
	public void setProfessional(String aValue) {
		this.professional = aValue;
	}	

	/**
	 * 说明	 * @return String
	 * @hibernate.property column="professionalAbout" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getProfessionalAbout() {
		return this.professionalAbout;
	}
	
	/**
	 * Set the professionalAbout
	 */	
	public void setProfessionalAbout(String aValue) {
		this.professionalAbout = aValue;
	}	

	/**
	 * 综合能力	 * @return String
	 * @hibernate.property column="mixedAbility" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getMixedAbility() {
		return this.mixedAbility;
	}
	
	/**
	 * Set the mixedAbility
	 */	
	public void setMixedAbility(String aValue) {
		this.mixedAbility = aValue;
	}	

	/**
	 * 说明	 * @return String
	 * @hibernate.property column="mixedAbilityAbout" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getMixedAbilityAbout() {
		return this.mixedAbilityAbout;
	}
	
	/**
	 * Set the mixedAbilityAbout
	 */	
	public void setMixedAbilityAbout(String aValue) {
		this.mixedAbilityAbout = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof StaffPromoteapply)) {
			return false;
		}
		StaffPromoteapply rhs = (StaffPromoteapply) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.promoteUser, rhs.promoteUser)
				.append(this.oldDept, rhs.oldDept)
				.append(this.oldJob, rhs.oldJob)
				.append(this.newDept, rhs.newDept)
				.append(this.newJob, rhs.newJob)
				.append(this.activeDate, rhs.activeDate)
				.append(this.promoteReason, rhs.promoteReason)
				.append(this.advantageOrChange, rhs.advantageOrChange)
				.append(this.professional, rhs.professional)
				.append(this.professionalAbout, rhs.professionalAbout)
				.append(this.mixedAbility, rhs.mixedAbility)
				.append(this.mixedAbilityAbout, rhs.mixedAbilityAbout)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.promoteUser) 
				.append(this.oldDept) 
				.append(this.oldJob) 
				.append(this.newDept) 
				.append(this.newJob) 
				.append(this.activeDate) 
				.append(this.promoteReason) 
				.append(this.advantageOrChange) 
				.append(this.professional) 
				.append(this.professionalAbout) 
				.append(this.mixedAbility) 
				.append(this.mixedAbilityAbout) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("promoteUserId", this.promoteUser) 
				.append("oldDeptId", this.oldDept) 
				.append("oldJobId", this.oldJob) 
				.append("newDeptId", this.newDept) 
				.append("newJobId", this.newJob) 
				.append("activeDate", this.activeDate) 
				.append("promoteReason", this.promoteReason) 
				.append("advantageOrChange", this.advantageOrChange) 
				.append("professional", this.professional) 
				.append("professionalAbout", this.professionalAbout) 
				.append("mixedAbility", this.mixedAbility) 
				.append("mixedAbilityAbout", this.mixedAbilityAbout) 
				.toString();
	}



}
