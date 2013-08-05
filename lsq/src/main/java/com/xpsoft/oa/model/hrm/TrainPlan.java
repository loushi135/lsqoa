package com.xpsoft.oa.model.hrm;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.dao.hrm.TrainUserDao;

/**
 * TrainPlan Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class TrainPlan extends com.xpsoft.core.model.BaseModel {
	public static final String SCHEDULED= "已安排";
	public static final String UNSCHEDULED= "未安排";
	public static final String COMPLETE= "已结束";
	public static final String CANCEL= "已取消";
	public static final String  ATTEND="0";
	public static final String  NOT_ATTEND="1";
	public static final String  REGIST="0";
	public static final String  NOT_REGIST="1";
	public static final Float FULL=100F;
    public static final Float  NOT_FULL=0F;
	@Expose
	protected Long id;
	@Expose
	protected String sn;
	@Expose
	protected TrainCourse trainCourse;
	@Expose
	protected java.util.Date trainTime;
	@Expose
	protected String trainStatus;
	@Expose
	protected String trainType;
	@Expose
	protected Integer sumNum;
	@Expose
	protected String cancelReason;
	@Expose
	protected String trainPlanPlace;
	@Expose
	protected String trainTarget;
	@Expose
	private Integer nowNum;
	/**
	 * Default Empty Constructor for class TrainPlan
	 */
	public TrainPlan () {
		super();
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	/**
	 * Default Key Fields Constructor for class TrainPlan
	 */
	public TrainPlan (
		 Long in_id
        ) {
		this.setId(in_id);
    }

    public TrainCourse getTrainCourse() {
			return trainCourse;
		}

	public void setTrainCourse(TrainCourse trainCourse) {
			this.trainCourse = trainCourse;
		}

	/**
	 * 	 * @return Long
     * @hibernate.id column="id" type="java.lang.Long" generator-class="native"
	 */
	public Long getId() {
		return this.id;
	}
	public void setId(Long aValue) {
		this.id = aValue;
	}	

	/**
	 * 课程外键	 * @return Long
	 * @hibernate.property column="trainCourseId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="trainTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getTrainTime() {
		return this.trainTime;
	}
	
	/**
	 * Set the trainTime
	 */	
	public void setTrainTime(java.util.Date aValue) {
		this.trainTime = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="trainStatus" type="java.lang.String" length="20" not-null="false" unique="false"
	 */
	public String getTrainStatus() {
		return this.trainStatus;
	}
	
	/**
	 * Set the trainStatus
	 */	
	public void setTrainStatus(String aValue) {
		this.trainStatus = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="trainType" type="java.lang.String" length="20" not-null="false" unique="false"
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

	/**
	 * 	 * @return Integer
	 * @hibernate.property column="sumNum" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getSumNum() {
		return this.sumNum;
	}
	
	/**
	 * Set the sumNum
	 */	
	public void setSumNum(Integer aValue) {
		if(null!=this.id&&null!=aValue&&null==nowNum){
			Integer i=0;
			
			TrainUserDao dao=(TrainUserDao)AppUtil.getBean("trainUserDao");
			
			i=dao.countRegist(this);
			setNowNum(i);
		}
		this.sumNum = aValue;
	}	

	public String getTrainPlanPlace() {
		return trainPlanPlace;
	}

	public void setTrainPlanPlace(String trainPlanPlace) {
		this.trainPlanPlace = trainPlanPlace;
	}
	public String getTrainTarget() {
		return trainTarget;
	}

	public void setTrainTarget(String trainTarget) {
		this.trainTarget = trainTarget;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof TrainPlan)) {
			return false;
		}
		TrainPlan rhs = (TrainPlan) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.sn, rhs.sn)
				.append(this.trainTime, rhs.trainTime)
				.append(this.trainStatus, rhs.trainStatus)
				.append(this.trainType, rhs.trainType)
				.append(this.sumNum, rhs.sumNum)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.sn) 
				.append(this.trainTime) 
				.append(this.trainStatus) 
				.append(this.trainType) 
				.append(this.sumNum) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("sn", this.sn) 
				.append("trainTime", this.trainTime) 
				.append("trainStatus", this.trainStatus) 
				.append("trainType", this.trainType) 
				.append("sumNum", this.sumNum) 
				.toString();
	}

	public Integer getNowNum() {
		if(null!=this.id&&null!=sumNum&&null==nowNum){
			Integer i=0;
			
			TrainUserDao dao=(TrainUserDao)AppUtil.getBean("trainUserDao");
			
			i=dao.countRegist(this);
			setNowNum(i);
		}
		
		return nowNum;
	}

	private void setNowNum(Integer nowNum) {
		
		this.nowNum = nowNum;
	}
}
