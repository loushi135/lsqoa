package com.xpsoft.oa.model.hrm;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * TrainUser Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class TrainUser extends com.xpsoft.core.model.BaseModel {
	//是否愿意参加培训（0：参加，1：未参加）是否出席培训（0出席，1未出席）
	@Expose
	public static final String ATTEND= "0";
	@Expose
	public static final String UN_ATTEND= "1";
	@Expose
	public static final String REGIST= "0";
	@Expose
	public static final String UN_REGIST= "1";
	@Expose
    protected Long id;
	@Expose
	protected TrainPlan trainPlan;
	@Expose
	protected AppUser appUser;
	@Expose
	protected String regist;
	@Expose
	protected String attend;
	@Expose
	protected String year;
	@Expose
	protected Float credit;
	@Expose
	protected Float courseScore;


	/**
	 * Default Empty Constructor for class TrainUser
	 */
	public TrainUser () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class TrainUser
	 */
	public TrainUser (
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
	 * 培训计划id	 * @return Long
	 * @hibernate.property column="trainPlanId" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */

	/**
	 * 员工id	 * @return Long
	 * @hibernate.property column="userId" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */

	/**
	 * 是否愿意参加培训（0：参加，1：未参加）	 * @return String
	 * @hibernate.property column="regist" type="java.lang.String" length="20" not-null="false" unique="false"
	 */
	public String getRegist() {
		return this.regist;
	}
	
	/**
	 * Set the regist
	 */	
	public void setRegist(String aValue) {
		this.regist = aValue;
	}	

	/**
	 * 是否出席培训（0出席，1未出席）	 * @return String
	 * @hibernate.property column="attend" type="java.lang.String" length="20" not-null="false" unique="false"
	 */
	public String getAttend() {
		return this.attend;
	}
	
	/**
	 * Set the attend
	 */	
	public void setAttend(String aValue) {
		this.attend = aValue;
	}	

	/**
	 * 年份	 * @return String
	 * @hibernate.property column="year" type="java.lang.String" length="20" not-null="false" unique="false"
	 */
	public String getYear() {
		return this.year;
	}
	
	/**
	 * Set the year
	 */	
	public void setYear(String aValue) {
		this.year = aValue;
	}	

	/**
	 * 获得学分（默认满学分）	 * @return Float
	 * @hibernate.property column="credit" type="java.lang.Float" length="12" not-null="false" unique="false"
	 */
	public Float getCredit() {
		return this.credit;
	}
	
	/**
	 * Set the credit
	 */	
	public void setCredit(Float aValue) {
		this.credit = aValue;
	}	

	/**
	 * 课程评分（只有出席的人员才可操作attend字段为1时）	 * @return Float
	 * @hibernate.property column="courseScore" type="java.lang.Float" length="12" not-null="false" unique="false"
	 */
	public Float getCourseScore() {
		return this.courseScore;
	}
	
	/**
	 * Set the courseScore
	 */	
	public void setCourseScore(Float aValue) {
		this.courseScore = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof TrainUser)) {
			return false;
		}
		TrainUser rhs = (TrainUser) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.regist, rhs.regist)
				.append(this.attend, rhs.attend)
				.append(this.year, rhs.year)
				.append(this.credit, rhs.credit)
				.append(this.courseScore, rhs.courseScore)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.regist) 
				.append(this.attend) 
				.append(this.year) 
				.append(this.credit) 
				.append(this.courseScore) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("regist", this.regist) 
				.append("attend", this.attend) 
				.append("year", this.year) 
				.append("credit", this.credit) 
				.append("courseScore", this.courseScore) 
				.toString();
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public TrainPlan getTrainPlan() {
		return trainPlan;
	}

	public void setTrainPlan(TrainPlan trainPlan) {
		this.trainPlan = trainPlan;
	}



}
