package com.xpsoft.oa.model.hrm;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * PhotoFolder Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class PhotoFolder extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected String folderName;
	@Expose
	protected PhotoFolder parent;
	@Expose
	protected AppUser user;
	private List<PhotoFolder> childFolders = new ArrayList<PhotoFolder>();
	private Set<Photo> photoSet = new HashSet<Photo>();
	/**
	 * Default Empty Constructor for class PhotoFolder
	 */
	public PhotoFolder () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class PhotoFolder
	 */
	public PhotoFolder (
		 Long in_id
        ) {
		this.setId(in_id);
    }


	public PhotoFolder(Long id, String folderName, PhotoFolder parent) {
		super();
		this.id = id;
		this.folderName = folderName;
		this.parent = parent;
	}

	public PhotoFolder(Long id, String folderName, PhotoFolder parent,
			List<PhotoFolder> childFolders) {
		super();
		this.id = id;
		this.folderName = folderName;
		this.parent = parent;
		this.childFolders = childFolders;
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

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	/**
	 * 文件夹名称	 * @return String
	 * @hibernate.property column="folderName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getFolderName() {
		return this.folderName;
	}
	
	/**
	 * Set the folderName
	 */	
	public void setFolderName(String aValue) {
		this.folderName = aValue;
	}	


	public PhotoFolder getParent() {
		return parent;
	}

	public void setParent(PhotoFolder parent) {
		this.parent = parent;
	}

	public List<PhotoFolder> getChildFolders() {
		return childFolders;
	}

	public void setChildFolders(List<PhotoFolder> childFolders) {
		this.childFolders = childFolders;
	}
	

	public Set<Photo> getPhotoSet() {
		return photoSet;
	}

	public void setPhotoSet(Set<Photo> photoSet) {
		this.photoSet = photoSet;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof PhotoFolder)) {
			return false;
		}
		PhotoFolder rhs = (PhotoFolder) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.folderName, rhs.folderName)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.folderName) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("folderName", this.folderName) 
				.toString();
	}



}
