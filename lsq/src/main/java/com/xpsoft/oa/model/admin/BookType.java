package com.xpsoft.oa.model.admin;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;


public class BookType extends com.xpsoft.core.model.BaseModel {

	protected Long typeId;
	protected String typeName;
	protected Long ptypeId;
	protected Integer index;
	private String typeSN;
	protected String remark;

	/* 该字段不与数据库交互 */
	private String path;

	// protected java.util.Set books = new java.util.HashSet();

	public BookType(Long typeId, String typeName, Long ptypeId, String typeSN,
			String path) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.ptypeId = ptypeId;
		this.typeSN = typeSN;
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getPtypeId() {
		return ptypeId;
	}

	public void setPtypeId(Long ptypeId) {
		this.ptypeId = ptypeId;
	}

	/**
	 * Default Empty Constructor for class BookType
	 */
	public BookType() {
		super();
	}

	/**
	 * Default Key Fields Constructor for class BookType
	 */
	public BookType(Long in_typeId) {
		this.setTypeId(in_typeId);
	}

	public BookType(Long in_typeId, Long in_ptypeId) {
		this.setTypeId(in_typeId);
		this.setPtypeId(in_ptypeId);
	}

	// public java.util.Set getBooks () {
	// return books;
	// }
	//	
	// public void setBooks (java.util.Set in_books) {
	// this.books = in_books;
	// }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * *
	 * 
	 * @return Long
	 * @hibernate.id column="typeId" type="java.lang.Long"
	 *               generator-class="native"
	 */
	public Long getTypeId() {
		return this.typeId;
	}

	/**
	 * Set the typeId
	 */
	public void setTypeId(Long aValue) {
		this.typeId = aValue;
	}

	/**
	 * *
	 * 
	 * @return String
	 * @hibernate.property column="typeName" type="java.lang.String"
	 *                     length="128" not-null="true" unique="false"
	 */
	public String getTypeName() {
		return this.typeName;
	}

	/**
	 * Set the typeName
	 * 
	 * @spring.validator type="required"
	 */
	public void setTypeName(String aValue) {
		this.typeName = aValue;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BookType)) {
			return false;
		}
		BookType rhs = (BookType) object;
		return new EqualsBuilder().append(this.typeId, rhs.typeId).append(
				this.typeName, rhs.typeName).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.typeId)
				.append(this.typeName).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("typeId", this.typeId).append(
				"typeName", this.typeName).toString();
	}

	public String getTypeSN() {
		return typeSN;
	}

	public void setTypeSN(String typeSN) {
		this.typeSN = typeSN;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

}
