package com.xpsoft.oa.model.hrm;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * PhotoComment Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class PhotoComment extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected Photo photo;
	@Expose
	protected AppUser user;
	@Expose
	protected String fullname;
	@Expose
	protected String content;
	@Expose
	protected java.util.Date createDate;


	/**
	 * Default Empty Constructor for class PhotoComment
	 */
	public PhotoComment () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class PhotoComment
	 */
	public PhotoComment (
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
	 * @hibernate.property column="fullname" type="java.lang.String" length="20" not-null="false" unique="false"
	 */
	public String getFullname() {
		return this.fullname;
	}
	
	/**
	 * Set the fullname
	 */	
	public void setFullname(String aValue) {
		this.fullname = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="content" type="java.lang.String" length="500" not-null="false" unique="false"
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * Set the content
	 */	
	public void setContent(String aValue) {
		this.content = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="createDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * Set the createDate
	 */	
	public void setCreateDate(java.util.Date aValue) {
		this.createDate = aValue;
	}	

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof PhotoComment)) {
			return false;
		}
		PhotoComment rhs = (PhotoComment) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.fullname, rhs.fullname)
				.append(this.content, rhs.content)
				.append(this.createDate, rhs.createDate)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.fullname) 
				.append(this.content) 
				.append(this.createDate) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("fullname", this.fullname) 
				.append("content", this.content) 
				.append("createDate", this.createDate) 
				.toString();
	}



}
