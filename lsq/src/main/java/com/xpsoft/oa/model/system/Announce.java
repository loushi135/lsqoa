package com.xpsoft.oa.model.system;


import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;

/**
 * Announce Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class Announce extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected String context;
	@Expose
	protected java.util.Date createtime;
	@Expose
	protected AppUser createUser;
	@Expose
	private Set<FileAttach> fileAttachs = new HashSet<FileAttach>(0);


	/**
	 * Default Empty Constructor for class Announce
	 */
	public Announce () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class Announce
	 */
	public Announce (
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
	 * @hibernate.property column="context" type="java.lang.String" length="1000" not-null="true" unique="false"
	 */
	public String getContext() {
		return this.context;
	}
	
	/**
	 * Set the context
	 * @spring.validator type="required"
	 */	
	public void setContext(String aValue) {
		this.context = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="createtime" type="java.util.Date" length="19" not-null="true" unique="false"
	 */
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	/**
	 * Set the createtime
	 * @spring.validator type="required"
	 */	
	public void setCreatetime(java.util.Date aValue) {
		this.createtime = aValue;
	}	

	/**
	 * 	 * @return Long
	 * @hibernate.property column="createUser" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public AppUser getCreateUser() {
		return this.createUser;
	}
	
	/**
	 * Set the createUser
	 * @spring.validator type="required"
	 */	
	public void setCreateUser(AppUser aValue) {
		this.createUser = aValue;
	}	
	
	public Set<FileAttach> getFileAttachs() {
		return fileAttachs;
	}

	public void setFileAttachs(Set<FileAttach> fileAttachs) {
		this.fileAttachs = fileAttachs;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Announce)) {
			return false;
		}
		Announce rhs = (Announce) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.context, rhs.context)
				.append(this.createtime, rhs.createtime)
				.append(this.createUser, rhs.createUser)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.context) 
				.append(this.createtime) 
				.append(this.createUser) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("context", this.context) 
				.append("createtime", this.createtime) 
				.append("createUser", this.createUser) 
				.toString();
	}



}
