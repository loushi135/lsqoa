package com.xpsoft.oa.model.hrm;


import java.math.BigDecimal;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.FileAttach;

/**
 * Photo Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class Photo extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected PhotoFolder photoFolder;
	@Expose
	protected AppUser user;
	@Expose
	protected String photoName;
	@Expose
	protected String photoDesc;
	@Expose
	protected FileAttach file;
	@Expose
	protected java.util.Date createDate;


	/**
	 * Default Empty Constructor for class Photo
	 */
	public Photo () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class Photo
	 */
	public Photo (
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
	 * 图片名称	 * @return String
	 * @hibernate.property column="photoName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getPhotoName() {
		return this.photoName;
	}
	
	/**
	 * Set the photoName
	 */	
	public void setPhotoName(String aValue) {
		this.photoName = aValue;
	}	

	/**
	 * 图片描述	 * @return String
	 * @hibernate.property column="photoDesc" type="java.lang.String" length="500" not-null="false" unique="false"
	 */
	public String getPhotoDesc() {
		return this.photoDesc;
	}
	
	/**
	 * Set the photoDesc
	 */	
	public void setPhotoDesc(String aValue) {
		this.photoDesc = aValue;
	}	

	public PhotoFolder getPhotoFolder() {
		return photoFolder;
	}

	public void setPhotoFolder(PhotoFolder photoFolder) {
		this.photoFolder = photoFolder;
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public FileAttach getFile() {
		return file;
	}

	public void setFile(FileAttach file) {
		this.file = file;
	}

	/**
	 * 创建时间	 * @return java.util.Date
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

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Photo)) {
			return false;
		}
		Photo rhs = (Photo) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.photoName, rhs.photoName)
				.append(this.photoDesc, rhs.photoDesc)
				.append(this.createDate, rhs.createDate)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.photoName) 
				.append(this.photoDesc) 
				.append(this.createDate) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("photoName", this.photoName) 
				.append("photoDesc", this.photoDesc) 
				.append("createDate", this.createDate) 
				.toString();
	}



}
