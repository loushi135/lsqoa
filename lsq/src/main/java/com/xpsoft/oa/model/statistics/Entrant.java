package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.core.model.BaseModel;

/**
 * Entrant Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class Entrant extends BaseModel {

	@Expose
    protected Long entrantId;
	@Expose
	protected String entrantType;
	@Expose
	protected java.util.Date signTime;
	@Expose
	protected String position;
	@Expose
	protected String inviteWay;
	@Expose
	protected String inviteReason;
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
	protected String signName;
	@Expose
	protected java.math.BigDecimal inspect;
	@Expose
	protected java.math.BigDecimal inspectSalary;
	@Expose
	protected java.math.BigDecimal probation;
	@Expose
	protected java.math.BigDecimal probationSalary;
	@Expose
	protected java.util.Set entrantFiles = new java.util.HashSet();


	/**
	 * Default Empty Constructor for class Entrant
	 */
	public Entrant () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class Entrant
	 */
	public Entrant (
		 Long in_entrantId
        ) {
		this.setEntrantId(in_entrantId);
    }

    

	/**
	 * 	 * @return Long
     * @hibernate.id column="entrantId" type="java.lang.Long" generator-class="native"
	 */
	public Long getEntrantId() {
		return this.entrantId;
	}
	
	/**
	 * Set the entrantId
	 */	
	public void setEntrantId(Long aValue) {
		this.entrantId = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="entrantType" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getEntrantType() {
		return this.entrantType;
	}
	
	/**
	 * Set the entrantType
	 * @spring.validator type="required"
	 */	
	public void setEntrantType(String aValue) {
		this.entrantType = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="signTime" type="java.util.Date" length="19" not-null="true" unique="false"
	 */
	public java.util.Date getSignTime() {
		return this.signTime;
	}
	
	/**
	 * Set the signTime
	 * @spring.validator type="required"
	 */	
	public void setSignTime(java.util.Date aValue) {
		this.signTime = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="position" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getPosition() {
		return this.position;
	}
	
	/**
	 * Set the position
	 * @spring.validator type="required"
	 */	
	public void setPosition(String aValue) {
		this.position = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="inviteWay" type="java.lang.String" length="255" not-null="false" unique="false"
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
	 * 	 * @return String
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
	 * 	 * @return String
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
	 * 	 * @return String
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
	 * 	 * @return String
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
	 * 	 * @return String
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
	 * 	 * @return String
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
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="inspect" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
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

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="inspectSalary" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
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
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="probation" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
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

	public String getInviteReason() {
		return inviteReason;
	}

	public void setInviteReason(String inviteReason) {
		this.inviteReason = inviteReason;
	}

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="probationSalary" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
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

	public java.util.Set getEntrantFiles() {
		return entrantFiles;
	}

	public void setEntrantFiles(java.util.Set entrantFiles) {
		this.entrantFiles = entrantFiles;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Entrant)) {
			return false;
		}
		Entrant rhs = (Entrant) object;
		return new EqualsBuilder()
				.append(this.entrantId, rhs.entrantId)
				.append(this.entrantType, rhs.entrantType)
				.append(this.signTime, rhs.signTime)
				.append(this.position, rhs.position)
				.append(this.inviteWay, rhs.inviteWay)
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
				.isEquals();
	}

	
	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.entrantId) 
				.append(this.entrantType) 
				.append(this.signTime) 
				.append(this.position) 
				.append(this.inviteWay) 
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
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("entrantId", this.entrantId) 
				.append("entrantType", this.entrantType) 
				.append("signTime", this.signTime) 
				.append("position", this.position) 
				.append("inviteWay", this.inviteWay) 
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
				.toString();
	}



}
