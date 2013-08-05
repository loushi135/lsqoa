package com.xpsoft.oa.model.flow;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * ProSubjectDef Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ProSubjectDef extends com.xpsoft.core.model.BaseModel {

    protected Long id;
	protected Long defId;
	protected String refField;
	protected String defaultVal;
	protected String refLabel;


	/**
	 * Default Empty Constructor for class ProSubjectDef
	 */
	public ProSubjectDef () {
		super();
	}
	
	public ProSubjectDef (Long defId,String refField,String refLabel) {
		
		this.defId=defId;
		if(StringUtils.isEmpty(refLabel)){
			this.defaultVal=refField;
		}else {
			this.refField=refField;
			this.refLabel=refLabel;
			this.defaultVal=refLabel;
		}
		
	}
	/**
	 * Default Key Fields Constructor for class ProSubjectDef
	 */
	public ProSubjectDef (
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
	 * @hibernate.property column="defId" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public Long getDefId() {
		return this.defId;
	}
	
	/**
	 * Set the defId
	 * @spring.validator type="required"
	 */	
	public void setDefId(Long aValue) {
		this.defId = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="refField" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getRefField() {
		return this.refField;
	}
	
	/**
	 * Set the refField
	 */	
	public void setRefField(String aValue) {
		this.refField = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="defaultVal" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getDefaultVal() {
		return this.defaultVal;
	}
	
	/**
	 * Set the defaultVal
	 */	
	public void setDefaultVal(String aValue) {
		this.defaultVal = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="refLabel" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getRefLabel() {
		return this.refLabel;
	}
	
	/**
	 * Set the refLabel
	 */	
	public void setRefLabel(String aValue) {
		this.refLabel = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ProSubjectDef)) {
			return false;
		}
		ProSubjectDef rhs = (ProSubjectDef) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.defId, rhs.defId)
				.append(this.refField, rhs.refField)
				.append(this.defaultVal, rhs.defaultVal)
				.append(this.refLabel, rhs.refLabel)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.defId) 
				.append(this.refField) 
				.append(this.defaultVal) 
				.append(this.refLabel) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("defId", this.defId) 
				.append("refField", this.refField) 
				.append("defaultVal", this.defaultVal) 
				.append("refLabel", this.refLabel) 
				.toString();
	}



}
