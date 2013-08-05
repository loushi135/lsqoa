package com.xpsoft.oa.model.hrm;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;

/**
 * TrainSurveyUser Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class TrainSurveyUser extends com.xpsoft.core.model.BaseModel {

	public final static String VOTED = "0";
	public final static String UN_VOTED = "1";
	@Expose
    protected Long id;
	@Expose
	protected TrainSurvey trainSurvey;
	@Expose
	protected Long userId;
	@Expose
	protected String voted;


	/**
	 * Default Empty Constructor for class TrainSurveyUser
	 */
	public TrainSurveyUser () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class TrainSurveyUser
	 */
	public TrainSurveyUser (
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
	 * @hibernate.property column="trainSurveyId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public TrainSurvey getTrainSurvey() {
		return this.trainSurvey;
	}
	
	/**
	 * Set the trainSurveyId
	 */	
	public void setTrainSurvey(TrainSurvey aValue) {
		this.trainSurvey = aValue;
	}	

	/**
	 * 	 * @return Long
	 * @hibernate.property column="userId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getUserId() {
		return this.userId;
	}
	
	/**
	 * Set the userId
	 */	
	public void setUserId(Long aValue) {
		this.userId = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="voted" type="java.lang.String" length="2" not-null="false" unique="false"
	 */
	public String getVoted() {
		return this.voted;
	}
	
	/**
	 * Set the voted
	 */	
	public void setVoted(String aValue) {
		this.voted = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof TrainSurveyUser)) {
			return false;
		}
		TrainSurveyUser rhs = (TrainSurveyUser) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.trainSurvey, rhs.trainSurvey)
				.append(this.userId, rhs.userId)
				.append(this.voted, rhs.voted)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.trainSurvey) 
				.append(this.userId) 
				.append(this.voted) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("trainSurveyId", this.trainSurvey) 
				.append("userId", this.userId) 
				.append("voted", this.voted) 
				.toString();
	}



}
