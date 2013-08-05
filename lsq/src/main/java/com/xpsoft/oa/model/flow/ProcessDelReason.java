package com.xpsoft.oa.model.flow;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * ProcessDelReason Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ProcessDelReason extends com.xpsoft.core.model.BaseModel {

    protected Long id;
	protected String flowName;
	protected String flowCreater;
	protected Long flowCreaterId;
	protected java.util.Date flowCreaterDate;
	protected String reason;


	/**
	 * Default Empty Constructor for class ProcessDelReason
	 */
	public ProcessDelReason () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ProcessDelReason
	 */
	public ProcessDelReason (
		 Long in_id
        ) {
		this.setId(in_id);
    }

    

	
	public Long getFlowCreaterId() {
		return flowCreaterId;
	}

	public void setFlowCreaterId(Long flowCreaterId) {
		this.flowCreaterId = flowCreaterId;
	}

	/**
	 * 序号	 * @return Long
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
	 * 流程名称	 * @return String
	 * @hibernate.property column="flowName" type="java.lang.String" length="128" not-null="true" unique="false"
	 */
	public String getFlowName() {
		return this.flowName;
	}
	
	/**
	 * Set the flowName
	 * @spring.validator type="required"
	 */	
	public void setFlowName(String aValue) {
		this.flowName = aValue;
	}	

	/**
	 * 流程创建者	 * @return String
	 * @hibernate.property column="flowCreater" type="java.lang.String" length="24" not-null="true" unique="false"
	 */
	public String getFlowCreater() {
		return this.flowCreater;
	}
	
	/**
	 * Set the flowCreater
	 * @spring.validator type="required"
	 */	
	public void setFlowCreater(String aValue) {
		this.flowCreater = aValue;
	}	

	/**
	 * 流程创建日期	 * @return java.util.Date
	 * @hibernate.property column="flowCreaterDate" type="java.util.Date" length="19" not-null="true" unique="false"
	 */
	public java.util.Date getFlowCreaterDate() {
		return this.flowCreaterDate;
	}
	
	/**
	 * Set the flowCreaterDate
	 * @spring.validator type="required"
	 */	
	public void setFlowCreaterDate(java.util.Date aValue) {
		this.flowCreaterDate = aValue;
	}	

	/**
	 * 删除原因	 * @return String
	 * @hibernate.property column="reason" type="java.lang.String" length="500" not-null="true" unique="false"
	 */
	public String getReason() {
		return this.reason;
	}
	
	/**
	 * Set the reason
	 * @spring.validator type="required"
	 */	
	public void setReason(String aValue) {
		this.reason = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ProcessDelReason)) {
			return false;
		}
		ProcessDelReason rhs = (ProcessDelReason) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.flowName, rhs.flowName)
				.append(this.flowCreater, rhs.flowCreater)
				.append(this.flowCreaterDate, rhs.flowCreaterDate)
				.append(this.reason, rhs.reason)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.flowName) 
				.append(this.flowCreater) 
				.append(this.flowCreaterDate) 
				.append(this.reason) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("flowName", this.flowName) 
				.append("flowCreater", this.flowCreater) 
				.append("flowCreaterDate", this.flowCreaterDate) 
				.append("reason", this.reason) 
				.toString();
	}



}
