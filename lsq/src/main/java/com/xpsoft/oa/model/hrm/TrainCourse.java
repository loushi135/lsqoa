package com.xpsoft.oa.model.hrm;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;

/**
 * TrainCourse Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class TrainCourse extends com.xpsoft.core.model.BaseModel {
	/**
	 * 选修为"1" 必修为"2"
	 */
	public static final String  ELECTIVE="1";
    public static final String  OBLIGATORY="2";
    @Expose
    protected Long courseId;
    @Expose
	protected String courseNo;
    @Expose
	protected String courseName;
    @Expose
	protected String courseType;
    @Expose
	protected String trainTarget;
    @Expose
	protected String trainCause;
    @Expose
	protected String coursePriority;
    @Expose
	protected String trainType;
    @Expose
	protected String trainWay;
    @Expose
	protected String courseTotal;
    @Expose
	protected String courseTime;
    @Expose
	protected String trainTeacher;
    @Expose
	protected String checkType;
    @Expose
	protected Integer trainBudget;
    @Expose
	protected String remarks;
    @Expose
	protected Integer credit;
    @Expose
	protected String memo;
    @Expose
	protected String description;
    @Expose
    protected String deptName;
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * Default Empty Constructor for class TrainCourse
	 */
	public TrainCourse () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class TrainCourse
	 */
	public TrainCourse (
		 Long in_courseId
        ) {
		this.setCourseId(in_courseId);
    }

    

	/**
	 * 	 * @return Long
     * @hibernate.id column="courseId" type="java.lang.Long" generator-class="native"
	 */
	public Long getCourseId() {
		return this.courseId;
	}
	
	/**
	 * Set the courseId
	 */	
	public void setCourseId(Long aValue) {
		this.courseId = aValue;
	}	

	/**
	 * 课程编号	 * @return String
	 * @hibernate.property column="courseNo" type="java.lang.String" length="80" not-null="false" unique="false"
	 */
	public String getCourseNo() {
		return this.courseNo;
	}
	
	/**
	 * Set the courseNo
	 */	
	public void setCourseNo(String aValue) {
		this.courseNo = aValue;
	}	

	/**
	 * 课程名称	 * @return String
	 * @hibernate.property column="courseName" type="java.lang.String" length="80" not-null="false" unique="false"
	 */
	public String getCourseName() {
		return this.courseName;
	}
	
	/**
	 * Set the courseName
	 */	
	public void setCourseName(String aValue) {
		this.courseName = aValue;
	}	

	/**
	 * 部门编号	 * @return Long
	 * @hibernate.property column="deptId" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	


	/**
	 * 课程类型	 * @return String
	 * @hibernate.property column="course_type" type="java.lang.String" length="80" not-null="false" unique="false"
	 */
	public String getCourseType() {
		return this.courseType;
	}
	
	/**
	 * Set the courseType
	 */	
	public void setCourseType(String aValue) {
		this.courseType = aValue;
	}	

	/**
	 * 培训对象	 * @return String
	 * @hibernate.property column="train_target" type="java.lang.String" length="80" not-null="false" unique="false"
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
	 * 培训目的	 * @return String
	 * @hibernate.property column="train_cause" type="java.lang.String" length="500" not-null="false" unique="false"
	 */
	public String getTrainCause() {
		return this.trainCause;
	}
	
	/**
	 * Set the trainCause
	 */	
	public void setTrainCause(String aValue) {
		this.trainCause = aValue;
	}	

	/**
	 * 课程优先级	 * @return String
	 * @hibernate.property column="course_priority" type="java.lang.String" length="20" not-null="false" unique="false"
	 */
	public String getCoursePriority() {
		return this.coursePriority;
	}
	
	/**
	 * Set the coursePriority
	 */	
	public void setCoursePriority(String aValue) {
		this.coursePriority = aValue;
	}	

	/**
	 * 培训类型	 * @return String
	 * @hibernate.property column="train_type" type="java.lang.String" length="20" not-null="false" unique="false"
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
	 * 培训方式	 * @return String
	 * @hibernate.property column="train_way" type="java.lang.String" length="80" not-null="false" unique="false"
	 */
	public String getTrainWay() {
		return this.trainWay;
	}
	
	/**
	 * Set the trainWay
	 */	
	public void setTrainWay(String aValue) {
		this.trainWay = aValue;
	}	

	/**
	 * 总课时	 * @return String
	 * @hibernate.property column="course_total" type="java.lang.String" length="80" not-null="false" unique="false"
	 */
	public String getCourseTotal() {
		return this.courseTotal;
	}
	
	/**
	 * Set the courseTotal
	 */	
	public void setCourseTotal(String aValue) {
		this.courseTotal = aValue;
	}	

	/**
	 * 课时	 * @return String
	 * @hibernate.property column="course_time" type="java.lang.String" length="80" not-null="false" unique="false"
	 */
	public String getCourseTime() {
		return this.courseTime;
	}
	
	/**
	 * Set the courseTime
	 */	
	public void setCourseTime(String aValue) {
		this.courseTime = aValue;
	}	

	/**
	 * 讲师	 * @return String
	 * @hibernate.property column="train_teacher" type="java.lang.String" length="80" not-null="false" unique="false"
	 */
	public String getTrainTeacher() {
		return this.trainTeacher;
	}
	
	/**
	 * Set the trainTeacher
	 */	
	public void setTrainTeacher(String aValue) {
		this.trainTeacher = aValue;
	}	

	/**
	 * 考核方式	 * @return String
	 * @hibernate.property column="check_type" type="java.lang.String" length="80" not-null="false" unique="false"
	 */
	public String getCheckType() {
		return this.checkType;
	}
	
	/**
	 * Set the checkType
	 */	
	public void setCheckType(String aValue) {
		this.checkType = aValue;
	}	

	/**
	 * 培训成本预算	 * @return Integer
	 * @hibernate.property column="train_budget" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getTrainBudget() {
		return this.trainBudget;
	}
	
	/**
	 * Set the trainBudget
	 */	
	public void setTrainBudget(Integer aValue) {
		this.trainBudget = aValue;
	}	

	/**
	 * 备注说明	 * @return String
	 * @hibernate.property column="remarks" type="java.lang.String" length="80" not-null="false" unique="false"
	 */
	public String getRemarks() {
		return this.remarks;
	}
	
	/**
	 * Set the remarks
	 */	
	public void setRemarks(String aValue) {
		this.remarks = aValue;
	}	

	/**
	 * 学分	 * @return Integer
	 * @hibernate.property column="credit" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getCredit() {
		return this.credit;
	}
	
	/**
	 * Set the credit
	 */	
	public void setCredit(Integer aValue) {
		this.credit = aValue;
	}	

	/**
	 * 备忘录	 * @return String
	 * @hibernate.property column="memo" type="java.lang.String" length="500" not-null="false" unique="false"
	 */
	public String getMemo() {
		return this.memo;
	}
	
	/**
	 * Set the memo
	 */	
	public void setMemo(String aValue) {
		this.memo = aValue;
	}	

	/**
	 * 描述	 * @return String
	 * @hibernate.property column="description" type="java.lang.String" length="500" not-null="false" unique="false"
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Set the description
	 */	
	public void setDescription(String aValue) {
		this.description = aValue;
	}	
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof TrainCourse)) {
			return false;
		}
		TrainCourse rhs = (TrainCourse) object;
		return new EqualsBuilder()
				.append(this.courseId, rhs.courseId)
				.append(this.courseNo, rhs.courseNo)
				.append(this.courseName, rhs.courseName)
				.append(this.courseType, rhs.courseType)
				.append(this.trainTarget, rhs.trainTarget)
				.append(this.trainCause, rhs.trainCause)
				.append(this.coursePriority, rhs.coursePriority)
				.append(this.trainType, rhs.trainType)
				.append(this.trainWay, rhs.trainWay)
				.append(this.courseTotal, rhs.courseTotal)
				.append(this.courseTime, rhs.courseTime)
				.append(this.trainTeacher, rhs.trainTeacher)
				.append(this.checkType, rhs.checkType)
				.append(this.trainBudget, rhs.trainBudget)
				.append(this.remarks, rhs.remarks)
				.append(this.credit, rhs.credit)
				.append(this.memo, rhs.memo)
				.append(this.deptName, rhs.deptName)
				.append(this.description, rhs.description)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.courseId) 
				.append(this.courseNo) 
				.append(this.courseName) 
				.append(this.courseType) 
				.append(this.trainTarget) 
				.append(this.trainCause) 
				.append(this.coursePriority) 
				.append(this.trainType) 
				.append(this.trainWay) 
				.append(this.courseTotal) 
				.append(this.courseTime) 
				.append(this.trainTeacher) 
				.append(this.checkType) 
				.append(this.trainBudget) 
				.append(this.remarks) 
				.append(this.credit) 
				.append(this.memo) 
				.append(this.deptName) 
				.append(this.description) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("courseId", this.courseId) 
				.append("courseNo", this.courseNo) 
				.append("courseName", this.courseName) 
				.append("courseType", this.courseType) 
				.append("trainTarget", this.trainTarget) 
				.append("trainCause", this.trainCause) 
				.append("coursePriority", this.coursePriority) 
				.append("trainType", this.trainType) 
				.append("trainWay", this.trainWay) 
				.append("courseTotal", this.courseTotal) 
				.append("courseTime", this.courseTime) 
				.append("trainTeacher", this.trainTeacher) 
				.append("checkType", this.checkType) 
				.append("trainBudget", this.trainBudget) 
				.append("remarks", this.remarks) 
				.append("credit", this.credit) 
				.append("memo", this.memo) 
				.append("deptName",this.deptName)
				.append("description", this.description) 
				.toString();
	}



}
