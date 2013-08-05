package com.xpsoft.oa.model.statistics;


import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.hrm.Job;
import com.xpsoft.oa.model.system.Department;
import com.xpsoft.oa.model.system.Dictionary;

/**
 * StaffRecruitapply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class StaffRecruitapply extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;  
	@Expose
	protected String companyName;
	@Expose
	protected Department dept;
	@Expose
	protected Job job;
	@Expose
	protected Integer currentNum;
	@Expose
	protected Integer applyNum;
	@Expose
	protected String applyReason;
	@Expose
	protected String age;
	@Expose
	protected String sex;
	@Expose
	protected Dictionary majorDic;
	@Expose
	protected Dictionary educationDic;
	@Expose
	protected String workYear;
	@Expose
	protected String positionDuty;
	@Expose
	protected String skillRequirement;
	@Expose
	protected String workExperience;
	@Expose
	protected String personality;
	@Expose
	protected String mainPositions;
	@Expose
	protected Set<Dictionary> mainPositionDics;
	@Expose
	protected String otherPosition;
	@Expose
	protected String otherRequirement;
	@Expose
	protected java.util.Date deadline;
	@Expose
	protected String personalCharacter;
	@Expose
	protected Long processRunId;

	/**
	 * Default Empty Constructor for class StaffRecruitapply
	 */
	public StaffRecruitapply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class StaffRecruitapply
	 */
	public StaffRecruitapply (
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
	 * 申请公司	 * @return String
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


	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
	}

	/**
	 * 该职位目前人数	 * @return Integer
	 * @hibernate.property column="currentNum" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getCurrentNum() {
		return this.currentNum;
	}
	
	/**
	 * Set the currentNum
	 */	
	public void setCurrentNum(Integer aValue) {
		this.currentNum = aValue;
	}	

	/**
	 * 此次申请人数	 * @return Integer
	 * @hibernate.property column="applyNum" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getApplyNum() {
		return this.applyNum;
	}
	
	/**
	 * Set the applyNum
	 */	
	public void setApplyNum(Integer aValue) {
		this.applyNum = aValue;
	}	

	/**
	 * 申请理由	 * @return String
	 * @hibernate.property column="applyReason" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getApplyReason() {
		return this.applyReason;
	}
	
	/**
	 * Set the applyReason
	 */	
	public void setApplyReason(String aValue) {
		this.applyReason = aValue;
	}	

	/**
	 * 年龄	 * @return String
	 * @hibernate.property column="age" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getAge() {
		return this.age;
	}
	
	/**
	 * Set the age
	 */	
	public void setAge(String aValue) {
		this.age = aValue;
	}	

	/**
	 * 性别	 * @return String
	 * @hibernate.property column="sex" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getSex() {
		return this.sex;
	}
	
	/**
	 * Set the sex
	 */	
	public void setSex(String aValue) {
		this.sex = aValue;
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

	public Dictionary getMajorDic() {
		return majorDic;
	}

	public void setMajorDic(Dictionary majorDic) {
		this.majorDic = majorDic;
	}

	public Dictionary getEducationDic() {
		return educationDic;
	}

	public void setEducationDic(Dictionary educationDic) {
		this.educationDic = educationDic;
	}


	public Set<Dictionary> getMainPositionDics() {
		return mainPositionDics;
	}

	public void setMainPositionDics(Set<Dictionary> mainPositionDics) {
		this.mainPositionDics = mainPositionDics;
	}

	/**
	 * 工作年限	 * @return String
	 * @hibernate.property column="workYear" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getWorkYear() {
		return this.workYear;
	}
	
	/**
	 * Set the workYear
	 */	
	public void setWorkYear(String aValue) {
		this.workYear = aValue;
	}	

	/**
	 * 该职位的主要职责	 * @return String
	 * @hibernate.property column="positionDuty" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getPositionDuty() {
		return this.positionDuty;
	}
	
	/**
	 * Set the positionDuty
	 */	
	public void setPositionDuty(String aValue) {
		this.positionDuty = aValue;
	}	

	/**
	 * 技能要求	 * @return String
	 * @hibernate.property column="skillRequirement" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getSkillRequirement() {
		return this.skillRequirement;
	}
	
	/**
	 * Set the skillRequirement
	 */	
	public void setSkillRequirement(String aValue) {
		this.skillRequirement = aValue;
	}	

	/**
	 * 工作经历	 * @return String
	 * @hibernate.property column="workExperience" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getWorkExperience() {
		return this.workExperience;
	}
	
	
	public String getMainPositions() {
		return mainPositions;
	}

	public void setMainPositions(String mainPositions) {
		this.mainPositions = mainPositions;
	}

	/**
	 * Set the workExperience
	 */	
	public void setWorkExperience(String aValue) {
		this.workExperience = aValue;
	}	

	/**
	 * 个性	 * @return String
	 * @hibernate.property column="personality" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getPersonality() {
		return this.personality;
	}
	
	/**
	 * Set the personality
	 */	
	public void setPersonality(String aValue) {
		this.personality = aValue;
	}	


	/**
	 * 其他	 * @return String
	 * @hibernate.property column="otherPosition" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getOtherPosition() {
		return this.otherPosition;
	}
	
	/**
	 * Set the otherPosition
	 */	
	public void setOtherPosition(String aValue) {
		this.otherPosition = aValue;
	}	

	/**
	 * 其他要求	 * @return String
	 * @hibernate.property column="otherRequirement" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getOtherRequirement() {
		return this.otherRequirement;
	}
	
	/**
	 * Set the otherRequirement
	 */	
	public void setOtherRequirement(String aValue) {
		this.otherRequirement = aValue;
	}	

	/**
	 * 到岗期限	 * @return java.util.Date
	 * @hibernate.property column="deadline" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getDeadline() {
		return this.deadline;
	}
	
	/**
	 * Set the deadline
	 */	
	public void setDeadline(java.util.Date aValue) {
		this.deadline = aValue;
	}	

	/**
	 * 人员性质	 * @return String
	 * @hibernate.property column="personalCharacter" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getPersonalCharacter() {
		return this.personalCharacter;
	}
	
	/**
	 * Set the personalCharacter
	 */	
	public void setPersonalCharacter(String aValue) {
		this.personalCharacter = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof StaffRecruitapply)) {
			return false;
		}
		StaffRecruitapply rhs = (StaffRecruitapply) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.companyName, rhs.companyName)
				.append(this.dept, rhs.dept)
				.append(this.job, rhs.job)
				.append(this.currentNum, rhs.currentNum)
				.append(this.applyNum, rhs.applyNum)
				.append(this.applyReason, rhs.applyReason)
				.append(this.age, rhs.age)
				.append(this.sex, rhs.sex)
				.append(this.majorDic, rhs.majorDic)
				.append(this.educationDic, rhs.educationDic)
				.append(this.workYear, rhs.workYear)
				.append(this.positionDuty, rhs.positionDuty)
				.append(this.skillRequirement, rhs.skillRequirement)
				.append(this.workExperience, rhs.workExperience)
				.append(this.personality, rhs.personality)
				.append(this.mainPositionDics, rhs.mainPositionDics)
				.append(this.otherPosition, rhs.otherPosition)
				.append(this.otherRequirement, rhs.otherRequirement)
				.append(this.deadline, rhs.deadline)
				.append(this.personalCharacter, rhs.personalCharacter)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.companyName) 
				.append(this.dept) 
				.append(this.job) 
				.append(this.currentNum) 
				.append(this.applyNum) 
				.append(this.applyReason) 
				.append(this.age) 
				.append(this.sex) 
				.append(this.majorDic) 
				.append(this.educationDic) 
				.append(this.workYear) 
				.append(this.positionDuty) 
				.append(this.skillRequirement) 
				.append(this.workExperience) 
				.append(this.personality) 
				.append(this.mainPositionDics) 
				.append(this.otherPosition) 
				.append(this.otherRequirement) 
				.append(this.deadline) 
				.append(this.personalCharacter) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("companyName", this.companyName) 
				.append("deptId", this.dept) 
				.append("jobId", this.job) 
				.append("currentNum", this.currentNum) 
				.append("applyNum", this.applyNum) 
				.append("applyReason", this.applyReason) 
				.append("age", this.age) 
				.append("sex", this.sex) 
				.append("majorDicId", this.majorDic) 
				.append("educationDicId", this.educationDic) 
				.append("workYear", this.workYear) 
				.append("positionDuty", this.positionDuty) 
				.append("skillRequirement", this.skillRequirement) 
				.append("workExperience", this.workExperience) 
				.append("personality", this.personality) 
				.append("mainPositionDicIds", this.mainPositionDics) 
				.append("otherPosition", this.otherPosition) 
				.append("otherRequirement", this.otherRequirement) 
				.append("deadline", this.deadline) 
				.append("personalCharacter", this.personalCharacter) 
				.toString();
	}



}
