package com.xpsoft.oa.model.bbs;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;

/**
 * BbsUserProperty Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class BbsUserProperty extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected  Long userId;
	@Expose
	protected  String description;


	/**
	 * Default Empty Constructor for class BbsUserProperty
	 */
	public BbsUserProperty () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class BbsUserProperty
	 */
	public BbsUserProperty (
		 Long in_userId
        ) {
		this.setUserId(in_userId);
    }

    

	/**
	 * 	 * @return Long
     * @hibernate.id column="userId" type="java.lang.Long" generator-class="native"
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
	 * 头像Id	 * @return Integer
	 * @hibernate.property column="fileId" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */

	/**
	 * 昵称	 * @return String
	 * @hibernate.property column="nickName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */

	/**
	 * Set the nickName
	 */	


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BbsUserProperty)) {
			return false;
		}
		BbsUserProperty rhs = (BbsUserProperty) object;
		return new EqualsBuilder()
				.append(this.userId, rhs.userId)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.userId) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("userId", this.userId) 
				.toString();
	}



}
