package com.xpsoft.core.model;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * ProContentMsg Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ProContentMsg extends com.xpsoft.core.model.BaseModel {
//流程短信表单
    protected Long id;
	protected String name;
	protected String value;
	protected Long promsgdetailId;//ProMsgDetail 的 ID


	/**
	 * Default Empty Constructor for class ProContentMsg
	 */
	public ProContentMsg () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ProContentMsg
	 */
	public ProContentMsg (
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
	 * @hibernate.property column="name" type="java.lang.String" length="200" not-null="true" unique="false"
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Set the name
	 * @spring.validator type="required"
	 */	
	public void setName(String aValue) {
		this.name = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="value" type="java.lang.String" length="1000" not-null="false" unique="false"
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Set the value
	 */	
	public void setValue(String aValue) {
		this.value = aValue;
	}	

	/**
	 * 	 * @return Long
	 * @hibernate.property column="promsgdetailId" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public Long getPromsgdetailId() {
		return this.promsgdetailId;
	}
	
	/**
	 * Set the promsgdetailId
	 * @spring.validator type="required"
	 */	
	public void setPromsgdetailId(Long aValue) {
		this.promsgdetailId = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ProContentMsg)) {
			return false;
		}
		ProContentMsg rhs = (ProContentMsg) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.name, rhs.name)
				.append(this.value, rhs.value)
				.append(this.promsgdetailId, rhs.promsgdetailId)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.name) 
				.append(this.value) 
				.append(this.promsgdetailId) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("name", this.name) 
				.append("value", this.value) 
				.append("promsgdetailId", this.promsgdetailId) 
				.toString();
	}



}
