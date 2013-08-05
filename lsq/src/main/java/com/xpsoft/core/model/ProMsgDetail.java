package com.xpsoft.core.model;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * ProMsgDetail Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ProMsgDetail extends com.xpsoft.core.model.BaseModel {
//流程短信实例
    protected Long id;
	protected Long typeId;//类型编号
	protected String strId;//短信编号
	protected String processId;//流程编号
	protected String taskId;//任务编号
	protected String taskName;//任务名称
	protected Long userId ;//员工编号
	
	protected String sucRes;//成功返回值
	protected String faiRes;//失败返回值

	/**
	 * Default Empty Constructor for class ProMsgDetail
	 */
	public ProMsgDetail () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ProMsgDetail
	 */
	public ProMsgDetail (
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
	 * 	 * @return Long
	 * @hibernate.property column="type_id" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public Long getTypeId() {
		return this.typeId;
	}
	
	/**
	 * Set the typeId
	 * @spring.validator type="required"
	 */	
	public void setTypeId(Long aValue) {
		this.typeId = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="str_id" type="java.lang.String" length="100" not-null="true" unique="false"
	 */
	public String getStrId() {
		return this.strId;
	}
	
	/**
	 * Set the strId
	 * @spring.validator type="required"
	 */	
	public void setStrId(String aValue) {
		this.strId = aValue;
	}	

	/**
	 * 	 * @return Long
	 * @hibernate.property column="process_id" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public String getProcessId() {
		return this.processId;
	}
	
	/**
	 * Set the processId
	 * @spring.validator type="required"
	 */	
	public void setProcessId(String aValue) {
		this.processId = aValue;
	}	

	/**
	 * 	 * @return Long
	 * @hibernate.property column="task_id" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public String getTaskId() {
		return this.taskId;
	}
	
	/**
	 * Set the taskId
	 * @spring.validator type="required"
	 */	
	public void setTaskId(String aValue) {
		this.taskId = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="task_name" type="java.lang.String" length="128" not-null="true" unique="false"
	 */
	public String getTaskName() {
		return this.taskName;
	}
	
	/**
	 * Set the taskName
	 * @spring.validator type="required"
	 */	
	public void setTaskName(String aValue) {
		this.taskName = aValue;
	}	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getSucRes() {
		return sucRes;
	}

	public void setSucRes(String sucRes) {
		this.sucRes = sucRes;
	}

	public String getFaiRes() {
		return faiRes;
	}

	public void setFaiRes(String faiRes) {
		this.faiRes = faiRes;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ProMsgDetail)) {
			return false;
		}
		ProMsgDetail rhs = (ProMsgDetail) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.typeId, rhs.typeId)
				.append(this.strId, rhs.strId)
				.append(this.processId, rhs.processId)
				.append(this.taskId, rhs.taskId)
				.append(this.taskName, rhs.taskName)
				.append(this.userId, rhs.userId)
				.append(this.sucRes, rhs.sucRes)
				.append(this.faiRes, rhs.faiRes)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.typeId) 
				.append(this.strId) 
				.append(this.processId) 
				.append(this.taskId) 
				.append(this.taskName) 
				.append(this.userId) 
				.append(this.sucRes) 
				.append(this.faiRes) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("typeId", this.typeId) 
				.append("strId", this.strId) 
				.append("processId", this.processId) 
				.append("taskId", this.taskId) 
				.append("taskName", this.taskName) 
				.append("userId", this.userId) 
				.append("sucRes", this.sucRes) 
				.append("faiRes", this.faiRes) 
				.toString();
	}



}
