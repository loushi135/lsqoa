package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;


/**
 * LeaseHouse Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class LeaseHouse extends com.xpsoft.core.model.BaseModel {
	@Expose
	protected Long id;
	@Expose
	protected String reporter;
	@Expose
	protected String reporterDepatment;
	@Expose
	protected String reporterCompanyName;
	@Expose
	protected java.util.Date leaseStart;
	@Expose
	protected java.util.Date leaseEnd;
	@Expose
	protected String monthlyRent;
	@Expose
	protected String yearRent;
	@Expose
	protected ProjectNew project;
	@Expose
	protected AppUser projectManager;
	@Expose
	protected String sort;
	@Expose
	protected String cause;
	@Expose
	protected String leaseLen;
	@Expose
	protected Long processRunId;
	@Expose
	protected String attachIds;
	@Expose
	protected String attachFiles;
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * Default Empty Constructor for class LeaseHouse
	 */
	public LeaseHouse () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class LeaseHouse
	 */
	public LeaseHouse (
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

	public String getAttachIds() {
		return attachIds;
	}

	public void setAttachIds(String attachIds) {
		this.attachIds = attachIds;
	}

	public String getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String attachFiles) {
		this.attachFiles = attachFiles;
	}

	/**
	 * 报告人	 * @return String
	 * @hibernate.property column="reporter" type="java.lang.String" length="120" not-null="false" unique="false"
	 */
	public String getReporter() {
		return this.reporter;
	}
	
	public String getLeaseLen() {
		return leaseLen;
	}

	public void setLeaseLen(String leaseLen) {
		this.leaseLen = leaseLen;
	}

	/**
	 * Set the reporter
	 */	
	public void setReporter(String aValue) {
		this.reporter = aValue;
	}	

	/**
	 * 所属部门	 * @return String
	 * @hibernate.property column="reporterDepatment" type="java.lang.String" length="120" not-null="false" unique="false"
	 */
	public String getReporterDepatment() {
		return this.reporterDepatment;
	}
	
	/**
	 * Set the reporterDepatment
	 */	
	public void setReporterDepatment(String aValue) {
		this.reporterDepatment = aValue;
	}	

	public AppUser getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(AppUser projectManager) {
		this.projectManager = projectManager;
	}

	/**
	 * 所在公司	 * @return String
	 * @hibernate.property column="reporterCompanyName" type="java.lang.String" length="120" not-null="false" unique="false"
	 */
	public String getReporterCompanyName() {
		return this.reporterCompanyName;
	}
	
	/**
	 * Set the reporterCompanyName
	 */	
	public void setReporterCompanyName(String aValue) {
		this.reporterCompanyName = aValue;
	}	

	/**
	 * 项目名称	 * @return String
	 * @hibernate.property column="projectId" type="java.lang.String" length="120" not-null="false" unique="false"
	 */

	
	/**
	 * Set the projectId
	 */	
	
	/**
	 * 租期开始日期	 * @return java.util.Date
	 * @hibernate.property column="leaseStart" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getLeaseStart() {
		return this.leaseStart;
	}
	
	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
	}

	/**
	 * Set the leaseStart
	 */	
	public void setLeaseStart(java.util.Date aValue) {
		this.leaseStart = aValue;
	}	

	/**
	 * 租期结束日期	 * @return java.util.Date
	 * @hibernate.property column="leaseEnd" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getLeaseEnd() {
		return this.leaseEnd;
	}
	
	/**
	 * Set the leaseEnd
	 */	
	public void setLeaseEnd(java.util.Date aValue) {
		this.leaseEnd = aValue;
	}	

	/**
	 * 月租金	 * @return String
	 * @hibernate.property column="monthlyRent" type="java.lang.String" length="30" not-null="false" unique="false"
	 */
	public String getMonthlyRent() {
		return this.monthlyRent;
	}
	
	/**
	 * Set the monthlyRent
	 */	
	public void setMonthlyRent(String aValue) {
		this.monthlyRent = aValue;
	}	

	/**
	 * 年租金	 * @return String
	 * @hibernate.property column="yearRent" type="java.lang.String" length="30" not-null="false" unique="false"
	 */
	public String getYearRent() {
		return this.yearRent;
	}
	
	/**
	 * Set the yearRent
	 */	
	public void setYearRent(String aValue) {
		this.yearRent = aValue;
	}	

	/**
	 * 申请原因	 * @return String
	 * @hibernate.property column="cause" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getCause() {
		return this.cause;
	}
	
	/**
	 * Set the cause
	 */	
	public void setCause(String aValue) {
		this.cause = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof LeaseHouse)) {
			return false;
		}
		LeaseHouse rhs = (LeaseHouse) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.reporter, rhs.reporter)
				.append(this.reporterDepatment, rhs.reporterDepatment)
				.append(this.reporterCompanyName, rhs.reporterCompanyName)
				.append(this.leaseStart, rhs.leaseStart)
				.append(this.leaseEnd, rhs.leaseEnd)
				.append(this.monthlyRent, rhs.monthlyRent)
				.append(this.yearRent, rhs.yearRent)
				.append(this.cause, rhs.cause)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.reporter) 
				.append(this.reporterDepatment) 
				.append(this.reporterCompanyName) 
				.append(this.leaseStart) 
				.append(this.leaseEnd) 
				.append(this.monthlyRent) 
				.append(this.yearRent) 
				.append(this.cause) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("reporter", this.reporter) 
				.append("reporterDepatment", this.reporterDepatment) 
				.append("reporterCompanyName", this.reporterCompanyName) 
				.append("leaseStart", this.leaseStart) 
				.append("leaseEnd", this.leaseEnd) 
				.append("monthlyRent", this.monthlyRent) 
				.append("yearRent", this.yearRent) 
				.append("cause", this.cause) 
				.toString();
	}

	public ProjectNew getProject() {
		return project;
	}

	public void setProject(ProjectNew project) {
		this.project = project;
	}

}
