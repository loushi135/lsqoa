package com.xpsoft.oa.model.flow;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * ProOtherDef Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ProOtherDef extends com.xpsoft.core.model.BaseModel {

    protected Long proid;
	protected String printUserIds;
	protected String printUserNames;


	/**
	 * Default Empty Constructor for class ProOtherDef
	 */
	public ProOtherDef () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ProOtherDef
	 */
	public ProOtherDef (
		 Long in_proid
        ) {
		this.setProid(in_proid);
    }

    

	/**
	 * 流程ID	 * @return Long
     * @hibernate.id column="proid" type="java.lang.Long" generator-class="native"
	 */
	public Long getProid() {
		return this.proid;
	}
	
	/**
	 * Set the proid
	 */	
	public void setProid(Long aValue) {
		this.proid = aValue;
	}	

	/**
	 * 打印人ID列表	 * @return String
	 * @hibernate.property column="printUserIds" type="java.lang.String" length="500" not-null="false" unique="false"
	 */
	public String getPrintUserIds() {
		return this.printUserIds;
	}
	
	/**
	 * Set the printUserIds
	 */	
	public void setPrintUserIds(String aValue) {
		this.printUserIds = aValue;
	}	

	/**
	 * 打印人姓名列表	 * @return String
	 * @hibernate.property column="printUserNames" type="java.lang.String" length="500" not-null="false" unique="false"
	 */
	public String getPrintUserNames() {
		return this.printUserNames;
	}
	
	/**
	 * Set the printUserNames
	 */	
	public void setPrintUserNames(String aValue) {
		this.printUserNames = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ProOtherDef)) {
			return false;
		}
		ProOtherDef rhs = (ProOtherDef) object;
		return new EqualsBuilder()
				.append(this.proid, rhs.proid)
				.append(this.printUserIds, rhs.printUserIds)
				.append(this.printUserNames, rhs.printUserNames)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.proid) 
				.append(this.printUserIds) 
				.append(this.printUserNames) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("proid", this.proid) 
				.append("printUserIds", this.printUserIds) 
				.append("printUserNames", this.printUserNames) 
				.toString();
	}



}
