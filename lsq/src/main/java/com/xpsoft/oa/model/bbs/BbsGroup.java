package com.xpsoft.oa.model.bbs;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * BbsGroup Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class BbsGroup extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected String groupName;
	@Expose
	protected Long parentId;
	@Expose
	protected AppUser createUser;


	/**
	 * Default Empty Constructor for class BbsGroup
	 */
	public BbsGroup () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class BbsGroup
	 */
	public BbsGroup (
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
	 * 分组名称	 * @return String
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

	/**
	 * 分类父主键	 * @return Long
	 * @hibernate.property column="parentId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getParentId() {
		return this.parentId;
	}
	
	/**
	 * Set the parentId
	 */	
	public void setParentId(Long aValue) {
		this.parentId = aValue;
	}	

	public boolean equals(Object object) {
		if (!(object instanceof BbsGroup)) {
			return false;
		}
		BbsGroup rhs = (BbsGroup) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.groupName, rhs.groupName)
				.append(this.parentId, rhs.parentId)
				.isEquals();
	}


	public AppUser getCreateUser() {
		return createUser;
	}

	public void setCreateUser(AppUser createUser) {
		this.createUser = createUser;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.groupName) 
				.append(this.parentId) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("groupName", this.groupName) 
				.append("parentId", this.parentId) 
				.toString();
	}



}
