package com.xpsoft.oa.model.flow;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * TaskAgent Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class TaskAgent extends com.xpsoft.core.model.BaseModel {

    protected Long id;
	protected Long assignId;
	protected String assignName;
	protected Long agentAssignId;
	protected String agentAssignName;
	protected Integer status;
	protected String remark;
	protected java.util.Date optDate;


	/**
	 * Default Empty Constructor for class TaskAgent
	 */
	public TaskAgent () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class TaskAgent
	 */
	public TaskAgent (
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
	 * 原任务办理人	 * @return Long
	 * @hibernate.property column="assignId" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public Long getAssignId() {
		return this.assignId;
	}
	
	/**
	 * Set the assignId
	 * @spring.validator type="required"
	 */	
	public void setAssignId(Long aValue) {
		this.assignId = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="assignName" type="java.lang.String" length="64" not-null="true" unique="false"
	 */
	public String getAssignName() {
		return this.assignName;
	}
	
	/**
	 * Set the assignName
	 * @spring.validator type="required"
	 */	
	public void setAssignName(String aValue) {
		this.assignName = aValue;
	}	

	/**
	 * 任务代理办理人	 * @return Long
	 * @hibernate.property column="agent_assignId" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public Long getAgentAssignId() {
		return this.agentAssignId;
	}
	
	/**
	 * Set the agentAssignId
	 * @spring.validator type="required"
	 */	
	public void setAgentAssignId(Long aValue) {
		this.agentAssignId = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="agent_assignName" type="java.lang.String" length="64" not-null="true" unique="false"
	 */
	public String getAgentAssignName() {
		return this.agentAssignName;
	}
	
	/**
	 * Set the agentAssignName
	 * @spring.validator type="required"
	 */	
	public void setAgentAssignName(String aValue) {
		this.agentAssignName = aValue;
	}	

	/**
	 * 	 * @return Integer
	 * @hibernate.property column="status" type="java.lang.Integer" length="10" not-null="true" unique="false"
	 */
	public Integer getStatus() {
		return this.status;
	}
	
	/**
	 * Set the status
	 * @spring.validator type="required"
	 */	
	public void setStatus(Integer aValue) {
		this.status = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="remark" type="java.lang.String" length="512" not-null="false" unique="false"
	 */
	public String getRemark() {
		return this.remark;
	}
	
	/**
	 * Set the remark
	 */	
	public void setRemark(String aValue) {
		this.remark = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="optDate" type="java.util.Date" length="19" not-null="true" unique="false"
	 */
	public java.util.Date getOptDate() {
		return this.optDate;
	}
	
	/**
	 * Set the optDate
	 * @spring.validator type="required"
	 */	
	public void setOptDate(java.util.Date aValue) {
		this.optDate = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof TaskAgent)) {
			return false;
		}
		TaskAgent rhs = (TaskAgent) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.assignId, rhs.assignId)
				.append(this.assignName, rhs.assignName)
				.append(this.agentAssignId, rhs.agentAssignId)
				.append(this.agentAssignName, rhs.agentAssignName)
				.append(this.status, rhs.status)
				.append(this.remark, rhs.remark)
				.append(this.optDate, rhs.optDate)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.assignId) 
				.append(this.assignName) 
				.append(this.agentAssignId) 
				.append(this.agentAssignName) 
				.append(this.status) 
				.append(this.remark) 
				.append(this.optDate) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("assignId", this.assignId) 
				.append("assignName", this.assignName) 
				.append("agentAssignId", this.agentAssignId) 
				.append("agentAssignName", this.agentAssignName) 
				.append("status", this.status) 
				.append("remark", this.remark) 
				.append("optDate", this.optDate) 
				.toString();
	}



}
