package com.xpsoft.oa.model.customer;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.City;
import com.xpsoft.oa.model.system.Province;

/**
 * CustomerNew Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class CustomerNew extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected String name;
	@Expose
	protected String fullname;
	@Expose
	protected String code;
	@Expose
	protected String delFlag; 
	@Expose
	protected Province province;
	@Expose
	protected City city;

	/**
	 * Default Empty Constructor for class CustomerNew
	 */
	public CustomerNew () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class CustomerNew
	 */
	public CustomerNew (
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
	 * @hibernate.property column="name" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Set the name
	 */	
	public void setName(String aValue) {
		this.name = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="fullname" type="java.lang.String" length="128" not-null="false" unique="false"
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


	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	/**
	 * 	 * @return String
	 * @hibernate.property column="code" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getCode() {
		return this.code;
	}
	
	/**
	 * Set the code
	 */	
	public void setCode(String aValue) {
		this.code = aValue;
	}	

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof CustomerNew)) {
			return false;
		}
		CustomerNew rhs = (CustomerNew) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.name, rhs.name)
				.append(this.fullname, rhs.fullname)
				.append(this.code, rhs.code)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.name) 
				.append(this.fullname) 
				.append(this.code) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("name", this.name) 
				.append("fullname", this.fullname) 
				.append("code", this.code) 
				.toString();
	}



}
