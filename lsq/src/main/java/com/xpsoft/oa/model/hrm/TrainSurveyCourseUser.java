package com.xpsoft.oa.model.hrm;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * TrainSurveyCourseUser Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class TrainSurveyCourseUser extends com.xpsoft.core.model.BaseModel {

	public static final String INTEREST_COURES = "0";
	public static final String UN_INTEREST_COURES = "1";
	@Expose
    protected Long id;
	@Expose
	protected TrainSurvey trainSurvey;
	@Expose
	protected TrainCourse trainCourse;
	@Expose
	protected AppUser user;
	@Expose
	protected String interest;


	/**
	 * Default Empty Constructor for class TrainSurveyCourseUser
	 */
	public TrainSurveyCourseUser () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class TrainSurveyCourseUser
	 */
	public TrainSurveyCourseUser (
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
	 * 调研的相关课程id	 * @return Long
	 * @hibernate.property column="trainSurveyId" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public TrainSurvey getTrainSurvey() {
		return this.trainSurvey;
	}
	
	/**
	 * Set the trainSurveyId
	 * @spring.validator type="required"
	 */	
	public void setTrainSurvey(TrainSurvey aValue) {
		this.trainSurvey = aValue;
	}	

	/**
	 * 	 * @return Long
	 * @hibernate.property column="trainCourseId" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public TrainCourse getTrainCourse() {
		return this.trainCourse;
	}
	
	/**
	 * Set the trainCourseId
	 * @spring.validator type="required"
	 */	
	public void setTrainCourse(TrainCourse aValue) {
		this.trainCourse = aValue;
	}	

	/**
	 * 用户id	 * @return Long
	 * @hibernate.property column="userId" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public AppUser getUser() {
		return this.user;
	}
	
	/**
	 * Set the userId
	 * @spring.validator type="required"
	 */	
	public void setUser(AppUser aValue) {
		this.user = aValue;
	}	

	/**
	 * 是否喜欢（0：喜欢，1：不喜欢）	 * @return String
	 * @hibernate.property column="interest" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getInterest() {
		return this.interest;
	}
	
	/**
	 * Set the interest
	 */	
	public void setInterest(String aValue) {
		this.interest = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof TrainSurveyCourseUser)) {
			return false;
		}
		TrainSurveyCourseUser rhs = (TrainSurveyCourseUser) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.trainSurvey, rhs.trainSurvey)
				.append(this.trainCourse, rhs.trainCourse)
				.append(this.user, rhs.user)
				.append(this.interest, rhs.interest)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.trainSurvey) 
				.append(this.trainCourse) 
				.append(this.user) 
				.append(this.interest) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("trainSurveyId", this.trainSurvey) 
				.append("trainCourseId", this.trainCourse) 
				.append("userId", this.user) 
				.append("interest", this.interest) 
				.toString();
	}

}
