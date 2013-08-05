package com.xpsoft.oa.model.hrm;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * TrainSurvey Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
@SuppressWarnings("serial")
public class TrainSurvey extends com.xpsoft.core.model.BaseModel {

	public final static String STATUS_GEN_PANEL = "1";
	public final static String STATUS_UNGEN_PANEL = "2";
	@Expose
    protected Long id;
	@Expose
	protected String sn;
	@Expose
	protected String verNum;
	@Expose
	protected java.util.Date beginTime;
	@Expose
	protected java.util.Date endTime;
	@Expose
	protected AppUser sponsor;
	@Expose
	protected java.util.Date createTime;
	@Expose
	protected String remark;
	@Expose
	protected String status;


	/**
	 * Default Empty Constructor for class TrainSurvey
	 */
	public TrainSurvey () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class TrainSurvey
	 */
	public TrainSurvey (
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
	 * 	 * @return String
	 * @hibernate.property column="sn" type="java.lang.String" length="200" not-null="false" unique="false"
	 */
	public String getSn() {
		return this.sn;
	}
	
	/**
	 * Set the sn
	 */	
	public void setSn(String aValue) {
		this.sn = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="verNum" type="java.lang.String" length="200" not-null="false" unique="false"
	 */
	public String getVerNum() {
		return this.verNum;
	}
	
	/**
	 * Set the verNum
	 */	
	public void setVerNum(String aValue) {
		this.verNum = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="beginTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getBeginTime() {
		return this.beginTime;
	}
	
	/**
	 * Set the beginTime
	 */	
	public void setBeginTime(java.util.Date aValue) {
		this.beginTime = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="endTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getEndTime() {
		return this.endTime;
	}
	
	/**
	 * Set the endTime
	 */	
	public void setEndTime(java.util.Date aValue) {
		this.endTime = aValue;
	}	

	/**
	 * 	 * @return Long
	 * @hibernate.property column="sponsor" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public AppUser getSponsor() {
		return this.sponsor;
	}
	
	/**
	 * Set the sponsor
	 */	
	public void setSponsor(AppUser aValue) {
		this.sponsor = aValue;
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

	/**
	 * 	 * @return String
	 * @hibernate.property column="remark" type="java.lang.String" length="500" not-null="false" unique="false"
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof TrainSurvey)) {
			return false;
		}
		TrainSurvey rhs = (TrainSurvey) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.sn, rhs.sn)
				.append(this.verNum, rhs.verNum)
				.append(this.beginTime, rhs.beginTime)
				.append(this.endTime, rhs.endTime)
				.append(this.sponsor, rhs.sponsor)
				.append(this.createTime, rhs.createTime)
				.append(this.remark, rhs.remark)
				.append(this.status, rhs.status)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.sn) 
				.append(this.verNum) 
				.append(this.beginTime) 
				.append(this.endTime) 
				.append(this.sponsor) 
				.append(this.createTime) 
				.append(this.remark) 
				.append(this.status) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("sn", this.sn) 
				.append("verNum", this.verNum) 
				.append("beginTime", this.beginTime) 
				.append("endTime", this.endTime) 
				.append("sponsor", this.sponsor) 
				.append("createTime", this.createTime) 
				.append("remark", this.remark) 
				.append("status", this.status) 
				.toString();
	}



}
