package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.hrm.Job;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Department;

/**
 * StaffEmployapply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * 当用申请
 */
public class StaffEmployapply extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;  
	@Expose
	protected String staffName;
	@Expose
	protected Department dept;
	@Expose
	protected Job job;
	@Expose
	protected String inviteWay;
	@Expose
	protected String publicWay;
	@Expose
	protected AppUser inviteUser;
	@Expose
	protected String inviteReason;
	@Expose
	protected String attachIDs;
	@Expose
	protected String attachFile;
	@Expose
	protected String professional;
	@Expose
	protected String experience;
	@Expose
	protected String confident;
	@Expose
	protected String thinkability;
	@Expose
	protected String expressability;
	@Expose
	protected String looks;
	@Expose
	protected java.math.BigDecimal inspect;
	@Expose
	protected java.math.BigDecimal inspectSalary;
	@Expose
	protected java.math.BigDecimal probation;
	@Expose
	protected java.math.BigDecimal probationSalary;
	@Expose
	protected String agreeEnterType;
	@Expose
	protected Long processRunId;
	@Expose
	protected String score;	//分数
	@Expose
	protected String medicalCheck;//体检情况
	
	/**
	 * Default Empty Constructor for class StaffEmployapply
	 */
	public StaffEmployapply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class StaffEmployapply
	 */
	public StaffEmployapply (
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
	 * 员工姓名	 * @return String
	 * @hibernate.property column="staffName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getStaffName() {
		return this.staffName;
	}
	
	/**
	 * Set the staffName
	 */	
	public void setStaffName(String aValue) {
		this.staffName = aValue;
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

	public AppUser getInviteUser() {
		return inviteUser;
	}

	public void setInviteUser(AppUser inviteUser) {
		this.inviteUser = inviteUser;
	}

	/**
	 * 招聘渠道	 * @return String
	 * @hibernate.property column="inviteWay" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getInviteWay() {
		return this.inviteWay;
	}
	
	/**
	 * Set the inviteWay
	 */	
	public void setInviteWay(String aValue) {
		this.inviteWay = aValue;
	}	

	/**
	 * 途径	 * @return String
	 * @hibernate.property column="publicWay" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getPublicWay() {
		return this.publicWay;
	}
	
	/**
	 * Set the publicWay
	 */	
	public void setPublicWay(String aValue) {
		this.publicWay = aValue;
	}	


	/**
	 * 推荐理由	 * @return String
	 * @hibernate.property column="inviteReason" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getInviteReason() {
		return this.inviteReason;
	}
	
	/**
	 * Set the inviteReason
	 */	
	public void setInviteReason(String aValue) {
		this.inviteReason = aValue;
	}	

	/**
	 * 附件	 * @return String
	 * @hibernate.property column="attachIDs" type="java.lang.String" length="512" not-null="false" unique="false"
	 */
	public String getAttachIDs() {
		return this.attachIDs;
	}
	
	/**
	 * Set the attachIDs
	 */	
	public void setAttachIDs(String aValue) {
		this.attachIDs = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="attachFile" type="java.lang.String" length="5000" not-null="false" unique="false"
	 */
	public String getAttachFile() {
		return this.attachFile;
	}
	
	/**
	 * Set the attachFile
	 */	
	public void setAttachFile(String aValue) {
		this.attachFile = aValue;
	}	

	/**
	 * 专业知识	 * @return String
	 * @hibernate.property column="professional" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getProfessional() {
		return this.professional;
	}
	
	/**
	 * Set the professional
	 * @spring.validator type="required"
	 */	
	public void setProfessional(String aValue) {
		this.professional = aValue;
	}	

	/**
	 * 工作经历	 * @return String
	 * @hibernate.property column="experience" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getExperience() {
		return this.experience;
	}
	
	/**
	 * Set the experience
	 * @spring.validator type="required"
	 */	
	public void setExperience(String aValue) {
		this.experience = aValue;
	}	

	/**
	 * 自信度	 * @return String
	 * @hibernate.property column="confident" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getConfident() {
		return this.confident;
	}
	
	/**
	 * Set the confident
	 * @spring.validator type="required"
	 */	
	public void setConfident(String aValue) {
		this.confident = aValue;
	}	

	/**
	 * 思维能力	 * @return String
	 * @hibernate.property column="thinkability" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getThinkability() {
		return this.thinkability;
	}
	
	/**
	 * Set the thinkability
	 * @spring.validator type="required"
	 */	
	public void setThinkability(String aValue) {
		this.thinkability = aValue;
	}	

	/**
	 * 表达能力	 * @return String
	 * @hibernate.property column="expressability" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getExpressability() {
		return this.expressability;
	}
	
	/**
	 * Set the expressability
	 * @spring.validator type="required"
	 */	
	public void setExpressability(String aValue) {
		this.expressability = aValue;
	}	

	/**
	 * 仪表	 * @return String
	 * @hibernate.property column="looks" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getLooks() {
		return this.looks;
	}
	
	/**
	 * Set the looks
	 * @spring.validator type="required"
	 */	
	public void setLooks(String aValue) {
		this.looks = aValue;
	}	

	/**
	 * 考察期	 * @return java.math.BigDecimal
	 * @hibernate.property column="inspect" type="java.math.BigDecimal" length="15" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getInspect() {
		return this.inspect;
	}
	
	/**
	 * Set the inspect
	 */	
	public void setInspect(java.math.BigDecimal aValue) {
		this.inspect = aValue;
	}	

	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
	}

	/**
	 * 考察期月薪	 * @return java.math.BigDecimal
	 * @hibernate.property column="inspectSalary" type="java.math.BigDecimal" length="15" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getInspectSalary() {
		return this.inspectSalary;
	}
	
	/**
	 * Set the inspectSalary
	 */	
	public void setInspectSalary(java.math.BigDecimal aValue) {
		this.inspectSalary = aValue;
	}	

	/**
	 * 试用期	 * @return java.math.BigDecimal
	 * @hibernate.property column="probation" type="java.math.BigDecimal" length="15" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getProbation() {
		return this.probation;
	}
	
	/**
	 * Set the probation
	 */	
	public void setProbation(java.math.BigDecimal aValue) {
		this.probation = aValue;
	}	

	/**
	 * 试用期月薪	 * @return java.math.BigDecimal
	 * @hibernate.property column="probationSalary" type="java.math.BigDecimal" length="15" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getProbationSalary() {
		return this.probationSalary;
	}
	
	/**
	 * Set the probationSalary
	 */	
	public void setProbationSalary(java.math.BigDecimal aValue) {
		this.probationSalary = aValue;
	}	

	/**
	 * 同意新进	 * @return String
	 * @hibernate.property column="agreeEnterType" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getAgreeEnterType() {
		return this.agreeEnterType;
	}
	
	/**
	 * Set the agreeEnterType
	 */	
	public void setAgreeEnterType(String aValue) {
		this.agreeEnterType = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof StaffEmployapply)) {
			return false;
		}
		StaffEmployapply rhs = (StaffEmployapply) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.staffName, rhs.staffName)
				.append(this.inviteWay, rhs.inviteWay)
				.append(this.publicWay, rhs.publicWay)
				.append(this.inviteReason, rhs.inviteReason)
				.append(this.attachIDs, rhs.attachIDs)
				.append(this.attachFile, rhs.attachFile)
				.append(this.professional, rhs.professional)
				.append(this.experience, rhs.experience)
				.append(this.confident, rhs.confident)
				.append(this.thinkability, rhs.thinkability)
				.append(this.expressability, rhs.expressability)
				.append(this.looks, rhs.looks)
				.append(this.inspect, rhs.inspect)
				.append(this.inspectSalary, rhs.inspectSalary)
				.append(this.probation, rhs.probation)
				.append(this.probationSalary, rhs.probationSalary)
				.append(this.agreeEnterType, rhs.agreeEnterType)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.staffName) 
				.append(this.inviteWay) 
				.append(this.publicWay) 
				.append(this.inviteReason) 
				.append(this.attachIDs) 
				.append(this.attachFile) 
				.append(this.professional) 
				.append(this.experience) 
				.append(this.confident) 
				.append(this.thinkability) 
				.append(this.expressability) 
				.append(this.looks) 
				.append(this.inspect) 
				.append(this.inspectSalary) 
				.append(this.probation) 
				.append(this.probationSalary) 
				.append(this.agreeEnterType) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("staffName", this.staffName) 
				.append("inviteWay", this.inviteWay) 
				.append("publicWay", this.publicWay) 
				.append("inviteReason", this.inviteReason) 
				.append("attachIDs", this.attachIDs) 
				.append("attachFile", this.attachFile) 
				.append("professional", this.professional) 
				.append("experience", this.experience) 
				.append("confident", this.confident) 
				.append("thinkability", this.thinkability) 
				.append("expressability", this.expressability) 
				.append("looks", this.looks) 
				.append("inspect", this.inspect) 
				.append("inspectSalary", this.inspectSalary) 
				.append("probation", this.probation) 
				.append("probationSalary", this.probationSalary) 
				.append("agreeEnterType", this.agreeEnterType) 
				.toString();
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getMedicalCheck() {
		return medicalCheck;
	}

	public void setMedicalCheck(String medicalCheck) {
		this.medicalCheck = medicalCheck;
	}
}
