package com.xpsoft.oa.model.admin;


import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.xpsoft.oa.model.system.AppUser;

/**
 * BookDel Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class BookDel extends com.xpsoft.core.model.BaseModel {

    protected Long id;  
	protected String delReason;
	protected Book book;
	protected AppUser user;
	protected Date createDate;

	/**
	 * Default Empty Constructor for class BookDel
	 */
	public BookDel () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class BookDel
	 */
	public BookDel (
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
	 * @hibernate.property column="delReason" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getDelReason() {
		return this.delReason;
	}
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * Set the delReason
	 */	
	public void setDelReason(String aValue) {
		this.delReason = aValue;
	}	

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BookDel)) {
			return false;
		}
		BookDel rhs = (BookDel) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.delReason, rhs.delReason)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.delReason) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("delReason", this.delReason) 
				.toString();
	}



}
