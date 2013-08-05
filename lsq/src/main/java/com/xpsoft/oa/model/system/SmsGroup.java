package com.xpsoft.oa.model.system;


import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;

/**
 * SmsGroup Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class SmsGroup extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected String groupName;

	protected Set<AppUser> userSet = new HashSet<AppUser>();

	/**
	 * Default Empty Constructor for class SmsGroup
	 */
	public SmsGroup () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class SmsGroup
	 */
	public SmsGroup (
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
	 * @hibernate.property column="groupName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getGroupName() {
		return this.groupName;
	}
	
	/**
	 * Set the groupName
	 */	
	public void setGroupName(String aValue) {
		this.groupName = aValue;
	}	

	public Set<AppUser> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<AppUser> userSet) {
		this.userSet = userSet;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof SmsGroup)) {
			return false;
		}
		SmsGroup rhs = (SmsGroup) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.groupName, rhs.groupName)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.groupName) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("groupName", this.groupName) 
				.toString();
	}



}
