package com.xpsoft.oa.model.system;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * UserFlowconfig Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class UserFlowconfig extends com.xpsoft.core.model.BaseModel {

    protected Long id;
	protected boolean isAgent;
	protected boolean isEmail;
	protected boolean isMsg;
	protected boolean isToERP;
	protected com.xpsoft.oa.model.system.AppUser appUser;


	/**
	 * Default Empty Constructor for class UserFlowconfig
	 */
	public UserFlowconfig () {
		super();
	}
	


	/**
	 * Default Key Fields Constructor for class UserFlowconfig
	 */
	public UserFlowconfig (
		 Long in_id
        ) {
		this.setId(in_id);
    }

	
	public com.xpsoft.oa.model.system.AppUser getAppUser () {
		return appUser;
	}	
	
	public void setAppUser (com.xpsoft.oa.model.system.AppUser in_appUser) {
		this.appUser = in_appUser;
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
	 */
	public Long getAppUserId() {
		return this.getAppUser()==null?null:this.getAppUser().getUserId();
	}
	
	/**
	 * Set the appUserId
	 */	
	public void setAppUserId(Long aValue) {
	    if (aValue==null) {
	    	appUser = null;
	    } else if (appUser == null) {
	        appUser = new com.xpsoft.oa.model.system.AppUser(aValue);
	        appUser.setVersion(new Integer(0));//set a version to cheat hibernate only
	    } else {
			appUser.setUserId(aValue);
	    }
	}	

	

	public boolean getIsAgent() {
		return isAgent;
	}



	public void setIsAgent(boolean isAgent) {
		this.isAgent = isAgent;
	}



	public boolean getIsEmail() {
		return isEmail;
	}



	public void setIsEmail(boolean isEmail) {
		this.isEmail = isEmail;
	}



	public boolean getIsMsg() {
		return isMsg;
	}



	public void setIsMsg(boolean isMsg) {
		this.isMsg = isMsg;
	}



	public boolean getIsToERP() {
		return isToERP;
	}



	public void setIsToERP(boolean isToERP) {
		this.isToERP = isToERP;
	}



	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof UserFlowconfig)) {
			return false;
		}
		UserFlowconfig rhs = (UserFlowconfig) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
						.append(this.isAgent, rhs.isAgent)
				.append(this.isEmail, rhs.isEmail)
				.append(this.isMsg, rhs.isMsg)
				.append(this.isToERP, rhs.isToERP)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
						.append(this.isAgent) 
				.append(this.isEmail) 
				.append(this.isMsg) 
				.append(this.isToERP) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
						.append("isAgent", this.isAgent) 
				.append("isEmail", this.isEmail) 
				.append("isMsg", this.isMsg) 
				.append("isToERP", this.isToERP) 
				.toString();
	}



}
