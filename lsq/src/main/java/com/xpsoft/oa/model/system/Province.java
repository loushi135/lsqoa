package com.xpsoft.oa.model.system;


import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;

/**
 * Province Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class Province extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long provinceId;  
	@Expose
	protected String provinceName;
	@Expose
	protected Integer sort;
	@Expose
	protected String remark;
	
	protected Set<City> citySet = new HashSet<City>();
	/**
	 * Default Empty Constructor for class Province
	 */
	public Province () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class Province
	 */
	public Province (
		 Long in_proID
        ) {
		this.setProvinceId(in_proID);
    }

	public Set<City> getCitySet() {
		return citySet;
	}

	public void setCitySet(Set<City> citySet) {
		this.citySet = citySet;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}


	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
