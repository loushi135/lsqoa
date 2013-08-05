package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * StaffActiveapply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class StaffActiveapply extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected String workAchieve;
	@Expose
	protected String workEfficiency;
	@Expose
	protected String teamSpirit;
	@Expose
	protected String businessLevel;
	@Expose
	protected String consciousness;
	@Expose
	protected String innovationAbility;
	@Expose
	protected String developAbility;
	@Expose
	protected String workAttitude;
	@Expose
	protected String character;
	@Expose
	protected AppUser applyUser;
	@Expose
	protected String applyName;
	@Expose
	protected String deptName;
	@Expose
	protected String workPosition;
	@Expose
	protected String examProject;
	@Expose
	protected String salaryOption;
	@Expose
	protected java.math.BigDecimal salaryMonthA;
	@Expose
	protected java.math.BigDecimal salaryBusinessA;
	@Expose
	protected String contractOption;
	@Expose
	protected String contractTime;
	@Expose
	protected java.math.BigDecimal salaryMonthB;
	@Expose
	protected java.math.BigDecimal salaryBusinessB;
	@Expose
	protected String deptLeaderName;
	@Expose
	protected String attachIds;
	@Expose
	protected String attachFiles;
	@Expose
	protected Long processRunId;
	@Expose
	protected String probation;
	@Expose
	protected String score;
	@Expose
	protected String firstAttachIds;
	@Expose
	protected String firstAttachFiles;


	/**
	 * Default Empty Constructor for class StaffActiveapply
	 */
	public StaffActiveapply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class StaffActiveapply
	 */
	public StaffActiveapply (
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
	 * 工作成查	 * @return String
	 * @hibernate.property column="workAchieve" type="java.lang.String" length="1000" not-null="false" unique="false"
	 */
	public String getWorkAchieve() {
		return this.workAchieve;
	}
	
	/**
	 * Set the workAchieve
	 */	
	public void setWorkAchieve(String aValue) {
		this.workAchieve = aValue;
	}	

	/**
	 * 工作效率	 * @return String
	 * @hibernate.property column="workEfficiency" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getWorkEfficiency() {
		return this.workEfficiency;
	}
	
	/**
	 * Set the workEfficiency
	 */	
	public void setWorkEfficiency(String aValue) {
		this.workEfficiency = aValue;
	}	

	/**
	 * 团队精神	 * @return String
	 * @hibernate.property column="teamSpirit" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getTeamSpirit() {
		return this.teamSpirit;
	}
	
	/**
	 * Set the teamSpirit
	 */	
	public void setTeamSpirit(String aValue) {
		this.teamSpirit = aValue;
	}	

	/**
	 * 业务水平	 * @return String
	 * @hibernate.property column="businessLevel" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getBusinessLevel() {
		return this.businessLevel;
	}
	
	/**
	 * Set the businessLevel
	 */	
	public void setBusinessLevel(String aValue) {
		this.businessLevel = aValue;
	}	

	/**
	 * 成本意识	 * @return String
	 * @hibernate.property column="consciousness" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getConsciousness() {
		return this.consciousness;
	}
	
	/**
	 * Set the consciousness
	 */	
	public void setConsciousness(String aValue) {
		this.consciousness = aValue;
	}	

	/**
	 * 创新能力	 * @return String
	 * @hibernate.property column="innovationAbility" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getInnovationAbility() {
		return this.innovationAbility;
	}
	
	/**
	 * Set the innovationAbility
	 */	
	public void setInnovationAbility(String aValue) {
		this.innovationAbility = aValue;
	}	

	/**
	 * 发展潜力	 * @return String
	 * @hibernate.property column="developAbility" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getDevelopAbility() {
		return this.developAbility;
	}
	
	/**
	 * Set the developAbility
	 */	
	public void setDevelopAbility(String aValue) {
		this.developAbility = aValue;
	}	

	/**
	 * 工作态度	 * @return String
	 * @hibernate.property column="workAttitude" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getWorkAttitude() {
		return this.workAttitude;
	}
	
	/**
	 * Set the workAttitude
	 */	
	public void setWorkAttitude(String aValue) {
		this.workAttitude = aValue;
	}	

	/**
	 * 品德言行	 * @return String
	 * @hibernate.property column="character" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getCharacter() {
		return this.character;
	}
	
	/**
	 * Set the character
	 */	
	public void setCharacter(String aValue) {
		this.character = aValue;
	}	


	public AppUser getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(AppUser applyUser) {
		this.applyUser = applyUser;
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

	/**
	 * 所在公司/部门	 * @return String
	 * @hibernate.property column="deptName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getDeptName() {
		return this.deptName;
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
	 * 考核项目	 * @return String
	 * @hibernate.property column="examProject" type="java.lang.String" length="1000" not-null="false" unique="false"
	 */
	public String getExamProject() {
		return this.examProject;
	}
	
	/**
	 * Set the examProject
	 */	
	public void setExamProject(String aValue) {
		this.examProject = aValue;
	}	

	/**
	 * 工资选择	 * @return String
	 * @hibernate.property column="salaryOption" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getSalaryOption() {
		return this.salaryOption;
	}
	
	/**
	 * Set the salaryOption
	 */	
	public void setSalaryOption(String aValue) {
		this.salaryOption = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="salaryMonthA" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getSalaryMonthA() {
		return this.salaryMonthA;
	}
	
	/**
	 * Set the salaryMonthA
	 */	
	public void setSalaryMonthA(java.math.BigDecimal aValue) {
		this.salaryMonthA = aValue;
	}	

	/**
	 * 按业绩考核	 * @return java.math.BigDecimal
	 * @hibernate.property column="salaryBusinessA" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getSalaryBusinessA() {
		return this.salaryBusinessA;
	}
	
	/**
	 * Set the salaryBusinessA
	 */	
	public void setSalaryBusinessA(java.math.BigDecimal aValue) {
		this.salaryBusinessA = aValue;
	}	

	/**
	 * 合同选择	 * @return String
	 * @hibernate.property column="contractOption" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getContractOption() {
		return this.contractOption;
	}
	
	/**
	 * Set the contractOption
	 */	
	public void setContractOption(String aValue) {
		this.contractOption = aValue;
	}	

	/**
	 * 合同期限	 * @return String
	 * @hibernate.property column="contractTime" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getContractTime() {
		return this.contractTime;
	}
	
	/**
	 * Set the contractTime
	 */	
	public void setContractTime(String aValue) {
		this.contractTime = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="salaryMonthB" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getSalaryMonthB() {
		return this.salaryMonthB;
	}
	
	/**
	 * Set the salaryMonthB
	 */	
	public void setSalaryMonthB(java.math.BigDecimal aValue) {
		this.salaryMonthB = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="salaryBusinessB" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getSalaryBusinessB() {
		return this.salaryBusinessB;
	}
	
	/**
	 * Set the salaryBusinessB
	 */	
	public void setSalaryBusinessB(java.math.BigDecimal aValue) {
		this.salaryBusinessB = aValue;
	}	

	/**
	 * 部门领导	 * @return String
	 * @hibernate.property column="deptLeaderName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getDeptLeaderName() {
		return this.deptLeaderName;
	}
	
	/**
	 * Set the deptLeaderName
	 */	
	public void setDeptLeaderName(String aValue) {
		this.deptLeaderName = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="attachIds" type="java.lang.String" length="200" not-null="false" unique="false"
	 */
	public String getAttachIds() {
		return this.attachIds;
	}
	
	/**
	 * Set the attachIds
	 */	
	public void setAttachIds(String aValue) {
		this.attachIds = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="attachFiles" type="java.lang.String" length="5000" not-null="false" unique="false"
	 */
	public String getAttachFiles() {
		return this.attachFiles;
	}
	
	/**
	 * Set the attachFiles
	 */	
	public void setAttachFiles(String aValue) {
		this.attachFiles = aValue;
	}	

	/**
	 * 	 * @return Long
	 * @hibernate.property column="processRunId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getProcessRunId() {
		return this.processRunId;
	}
	
	/**
	 * Set the processRunId
	 */	
	public void setProcessRunId(Long aValue) {
		this.processRunId = aValue;
	}	

	/**
	 * 试用期	 * @return String
	 * @hibernate.property column="probation" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getProbation() {
		return this.probation;
	}
	
	/**
	 * Set the probation
	 */	
	public void setProbation(String aValue) {
		this.probation = aValue;
	}	

	/**
	 * 分数	 * @return String
	 * @hibernate.property column="score" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getScore() {
		return this.score;
	}
	
	/**
	 * Set the score
	 */	
	public void setScore(String aValue) {
		this.score = aValue;
	}	

	/**
	 * 附件ID	 * @return String
	 * @hibernate.property column="firstAttachIds" type="java.lang.String" length="200" not-null="false" unique="false"
	 */
	public String getFirstAttachIds() {
		return this.firstAttachIds;
	}
	
	/**
	 * Set the firstAttachIds
	 */	
	public void setFirstAttachIds(String aValue) {
		this.firstAttachIds = aValue;
	}	

	/**
	 * 附件File	 * @return String
	 * @hibernate.property column="firstAttachFiles" type="java.lang.String" length="5000" not-null="false" unique="false"
	 */
	public String getFirstAttachFiles() {
		return this.firstAttachFiles;
	}
	
	/**
	 * Set the firstAttachFiles
	 */	
	public void setFirstAttachFiles(String aValue) {
		this.firstAttachFiles = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof StaffActiveapply)) {
			return false;
		}
		StaffActiveapply rhs = (StaffActiveapply) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.workAchieve, rhs.workAchieve)
				.append(this.workEfficiency, rhs.workEfficiency)
				.append(this.teamSpirit, rhs.teamSpirit)
				.append(this.businessLevel, rhs.businessLevel)
				.append(this.consciousness, rhs.consciousness)
				.append(this.innovationAbility, rhs.innovationAbility)
				.append(this.developAbility, rhs.developAbility)
				.append(this.workAttitude, rhs.workAttitude)
				.append(this.character, rhs.character)
				.append(this.applyUser, rhs.applyUser)
				.append(this.applyName, rhs.applyName)
				.append(this.deptName, rhs.deptName)
				.append(this.workPosition, rhs.workPosition)
				.append(this.examProject, rhs.examProject)
				.append(this.salaryOption, rhs.salaryOption)
				.append(this.salaryMonthA, rhs.salaryMonthA)
				.append(this.salaryBusinessA, rhs.salaryBusinessA)
				.append(this.contractOption, rhs.contractOption)
				.append(this.contractTime, rhs.contractTime)
				.append(this.salaryMonthB, rhs.salaryMonthB)
				.append(this.salaryBusinessB, rhs.salaryBusinessB)
				.append(this.deptLeaderName, rhs.deptLeaderName)
				.append(this.attachIds, rhs.attachIds)
				.append(this.attachFiles, rhs.attachFiles)
				.append(this.processRunId, rhs.processRunId)
				.append(this.probation, rhs.probation)
				.append(this.score, rhs.score)
				.append(this.firstAttachIds, rhs.firstAttachIds)
				.append(this.firstAttachFiles, rhs.firstAttachFiles)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.workAchieve) 
				.append(this.workEfficiency) 
				.append(this.teamSpirit) 
				.append(this.businessLevel) 
				.append(this.consciousness) 
				.append(this.innovationAbility) 
				.append(this.developAbility) 
				.append(this.workAttitude) 
				.append(this.character) 
				.append(this.applyUser) 
				.append(this.applyName) 
				.append(this.deptName) 
				.append(this.workPosition) 
				.append(this.examProject) 
				.append(this.salaryOption) 
				.append(this.salaryMonthA) 
				.append(this.salaryBusinessA) 
				.append(this.contractOption) 
				.append(this.contractTime) 
				.append(this.salaryMonthB) 
				.append(this.salaryBusinessB) 
				.append(this.deptLeaderName) 
				.append(this.attachIds) 
				.append(this.attachFiles) 
				.append(this.processRunId) 
				.append(this.probation) 
				.append(this.score) 
				.append(this.firstAttachIds) 
				.append(this.firstAttachFiles) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("workAchieve", this.workAchieve) 
				.append("workEfficiency", this.workEfficiency) 
				.append("teamSpirit", this.teamSpirit) 
				.append("businessLevel", this.businessLevel) 
				.append("consciousness", this.consciousness) 
				.append("innovationAbility", this.innovationAbility) 
				.append("developAbility", this.developAbility) 
				.append("workAttitude", this.workAttitude) 
				.append("character", this.character) 
				.append("applyUser", this.applyUser) 
				.append("applyName", this.applyName) 
				.append("deptName", this.deptName) 
				.append("workPosition", this.workPosition) 
				.append("examProject", this.examProject) 
				.append("salaryOption", this.salaryOption) 
				.append("salaryMonthA", this.salaryMonthA) 
				.append("salaryBusinessA", this.salaryBusinessA) 
				.append("contractOption", this.contractOption) 
				.append("contractTime", this.contractTime) 
				.append("salaryMonthB", this.salaryMonthB) 
				.append("salaryBusinessB", this.salaryBusinessB) 
				.append("deptLeaderName", this.deptLeaderName) 
				.append("attachIds", this.attachIds) 
				.append("attachFiles", this.attachFiles) 
				.append("processRunId", this.processRunId) 
				.append("probation", this.probation) 
				.append("score", this.score) 
				.append("firstAttachIds", this.firstAttachIds) 
				.append("firstAttachFiles", this.firstAttachFiles) 
				.toString();
	}



}
