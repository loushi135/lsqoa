package com.xpsoft.oa.model.system;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;

/**
 * City Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class City extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long cityId;  
	@Expose
	protected String cityName;
	@Expose
	protected Province province;
	@Expose
	protected Integer sort;


	/**
	 * Default Empty Constructor for class City
	 */
	public City () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class City
	 */
	public City (
		 Long in_cityId
        ) {
		this.setCityId(in_cityId);
    }

    

	/**
	 * 城市主键	 * @return Long
     * @hibernate.id column="cityId" type="java.lang.Long" generator-class="native"
	 */
	public Long getCityId() {
		return this.cityId;
	}
	
	/**
	 * Set the cityId
	 */	
	public void setCityId(Long aValue) {
		this.cityId = aValue;
	}	

	/**
	 * 城市名称	 * @return String
	 * @hibernate.property column="cityName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getCityName() {
		return this.cityName;
	}
	
	/**
	 * Set the cityName
	 */	
	public void setCityName(String aValue) {
		this.cityName = aValue;
	}	


	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	/**
	 * 排序	 * @return Integer
	 * @hibernate.property column="sort" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getSort() {
		return this.sort;
	}
	
	/**
	 * Set the sort
	 */	
	public void setSort(Integer aValue) {
		this.sort = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof City)) {
			return false;
		}
		City rhs = (City) object;
		return new EqualsBuilder()
				.append(this.cityId, rhs.cityId)
				.append(this.cityName, rhs.cityName)
				.append(this.sort, rhs.sort)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.cityId) 
				.append(this.cityName) 
				.append(this.sort) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("cityId", this.cityId) 
				.append("cityName", this.cityName) 
				.append("sort", this.sort) 
				.toString();
	}



}
