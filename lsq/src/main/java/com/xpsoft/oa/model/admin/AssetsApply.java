package com.xpsoft.oa.model.admin;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.system.AppUser;

/**
 * AssetsApply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class AssetsApply extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected String name;
	@Expose
	protected String dept;
	@Expose
	protected ProjectNew useProject;
	@Expose
	protected AppUser useProjectManager;
	@Expose
	protected java.util.Date applyDate;
	@Expose
	protected Integer applyType;
	@Expose
	protected String applyDescription;
	@Expose
	protected Long processRunId;
	@Expose
	protected String isSubsidyOrNot;//是否补贴
	@Expose
	protected java.util.Set assetsApplycontents = new java.util.HashSet();

	/**
	 * Default Empty Constructor for class AssetsApply
	 */
	public AssetsApply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class AssetsApply
	 */
	public AssetsApply (
		 Long in_id
        ) {
		this.setId(in_id);
    }


	
	public String getIsSubsidyOrNot() {
		return isSubsidyOrNot;
	}

	public void setIsSubsidyOrNot(String isSubsidyOrNot) {
		this.isSubsidyOrNot = isSubsidyOrNot;
	}

	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
	}

	public java.util.Set getAssetsApplycontents () {
		return assetsApplycontents;
	}	
	
	public void setAssetsApplycontents (java.util.Set in_assetsApplycontents) {
		this.assetsApplycontents = in_assetsApplycontents;
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
	 * @hibernate.property column="name" type="java.lang.String" length="24" not-null="true" unique="false"
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Set the name
	 * @spring.validator type="required"
	 */	
	public void setName(String aValue) {
		this.name = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="dept" type="java.lang.String" length="24" not-null="true" unique="false"
	 */
	public String getDept() {
		return this.dept;
	}
	
	/**
	 * Set the dept
	 * @spring.validator type="required"
	 */	
	public void setDept(String aValue) {
		this.dept = aValue;
	}	

	
	public ProjectNew getUseProject() {
		return useProject;
	}

	public void setUseProject(ProjectNew useProject) {
		this.useProject = useProject;
	}

	public AppUser getUseProjectManager() {
		return useProjectManager;
	}

	public void setUseProjectManager(AppUser useProjectManager) {
		this.useProjectManager = useProjectManager;
	}

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="applyDate" type="java.util.Date" length="19" not-null="true" unique="false"
	 */
	public java.util.Date getApplyDate() {
		return this.applyDate;
	}
	
	/**
	 * Set the applyDate
	 * @spring.validator type="required"
	 */	
	public void setApplyDate(java.util.Date aValue) {
		this.applyDate = aValue;
	}	

	/**
	 * 	 * @return Integer
	 * @hibernate.property column="applyType" type="java.lang.Integer" length="10" not-null="true" unique="false"
	 */
	public Integer getApplyType() {
		return this.applyType;
	}
	
	/**
	 * Set the applyType
	 * @spring.validator type="required"
	 */	
	public void setApplyType(Integer aValue) {
		this.applyType = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="applyDescription" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getApplyDescription() {
		return this.applyDescription;
	}
	
	/**
	 * Set the applyDescription
	 * @spring.validator type="required"
	 */	
	public void setApplyDescription(String aValue) {
		this.applyDescription = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof AssetsApply)) {
			return false;
		}
		AssetsApply rhs = (AssetsApply) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.name, rhs.name)
				.append(this.dept, rhs.dept)
				.append(this.applyDate, rhs.applyDate)
				.append(this.applyType, rhs.applyType)
				.append(this.applyDescription, rhs.applyDescription)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.name) 
				.append(this.dept) 
				.append(this.applyDate) 
				.append(this.applyType) 
				.append(this.applyDescription) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("name", this.name) 
				.append("dept", this.dept) 
				.append("applyDate", this.applyDate) 
				.append("applyType", this.applyType) 
				.append("applyDescription", this.applyDescription) 
				.toString();
	}



}
