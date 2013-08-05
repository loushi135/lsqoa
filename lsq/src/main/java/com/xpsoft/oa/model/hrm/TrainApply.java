package com.xpsoft.oa.model.hrm;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.core.model.BaseModel;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Department;

/**
 * TrainApply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class TrainApply extends BaseModel {
	public final static String PUBLISH = "1";
	@Expose
    protected Long id;
	@Expose
	protected AppUser applyUser;
	@Expose
	protected Department department;
	@Expose
	protected String trainTarget;
	@Expose
	protected String trainType;
	@Expose
	protected TrainCourse trainCourse;
	@Expose
	protected java.util.Date createTime;
	@Expose
	protected String publish;


	/**
	 * Default Empty Constructor for class TrainApply
	 */
	public TrainApply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class TrainApply
	 */
	public TrainApply (
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


	public AppUser getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(AppUser applyUser) {
		this.applyUser = applyUser;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * 	 * @return String
	 * @hibernate.property column="train_plan" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getTrainTarget() {
		return this.trainTarget;
	}
	
	/**
	 * Set the trainTarget
	 */	
	public void setTrainTarget(String aValue) {
		this.trainTarget = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="train_type" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getTrainType() {
		return this.trainType;
	}
	
	/**
	 * Set the trainType
	 */	
	public void setTrainType(String aValue) {
		this.trainType = aValue;
	}	


	public TrainCourse getTrainCourse() {
		return trainCourse;
	}

	public void setTrainCourse(TrainCourse trainCourse) {
		this.trainCourse = trainCourse;
	}

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="createTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	/**
	 * Set the createTime
	 */	
	public void setCreateTime(java.util.Date aValue) {
		this.createTime = aValue;
	}	

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof TrainApply)) {
			return false;
		}
		TrainApply rhs = (TrainApply) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.applyUser, rhs.applyUser)
				.append(this.department, rhs.department)
				.append(this.trainTarget, rhs.trainTarget)
				.append(this.trainType, rhs.trainType)
				.append(this.trainCourse, rhs.trainCourse)
				.append(this.createTime, rhs.createTime)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.applyUser) 
				.append(this.department) 
				.append(this.trainTarget) 
				.append(this.trainType) 
				.append(this.trainCourse) 
				.append(this.createTime) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("applyUser", this.applyUser) 
				.append("department", this.department) 
				.append("trainTarget", this.trainTarget) 
				.append("trainType", this.trainType) 
				.append("trainCourse", this.trainCourse) 
				.append("createTime", this.createTime) 
				.toString();
	}



}
