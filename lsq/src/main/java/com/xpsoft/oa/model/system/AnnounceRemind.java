package com.xpsoft.oa.model.system;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;

/**
 * AnnounceRemind Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class AnnounceRemind extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected Announce announce;
	@Expose
	protected AppUser user;
	@Expose
	protected Integer flag;


	/**
	 * Default Empty Constructor for class AnnounceRemind
	 */
	public AnnounceRemind () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class AnnounceRemind
	 */
	public AnnounceRemind (
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
	 * @hibernate.property column="announceId" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public Announce getAnnounce() {
		return this.announce;
	}
	
	/**
	 * Set the announceId
	 * @spring.validator type="required"
	 */	
	public void setAnnounce(Announce aValue) {
		this.announce = aValue;
	}	

	/**
	 * 	 * @return Long
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
	 * 	 * @return Integer
	 * @hibernate.property column="flag" type="java.lang.Integer" length="10" not-null="true" unique="false"
	 */
	public Integer getFlag() {
		return this.flag;
	}
	
	/**
	 * Set the flag
	 * @spring.validator type="required"
	 */	
	public void setFlag(Integer aValue) {
		this.flag = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof AnnounceRemind)) {
			return false;
		}
		AnnounceRemind rhs = (AnnounceRemind) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.announce, rhs.announce)
				.append(this.user, rhs.user)
				.append(this.flag, rhs.flag)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.announce) 
				.append(this.user) 
				.append(this.flag) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("announceId", this.announce) 
				.append("userId", this.user) 
				.append("flag", this.flag) 
				.toString();
	}



}
