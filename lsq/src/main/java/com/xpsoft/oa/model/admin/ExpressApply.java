package com.xpsoft.oa.model.admin;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.system.Department;

/**
 * ExpressApply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ExpressApply extends com.xpsoft.core.model.BaseModel {

    protected Long id;
	protected String applyer;
	protected Long applyerId;
	protected java.util.Date applyDate;
	protected Integer expressType;
	protected Long provinceId;
	protected String province;
	protected Long cityId;
	protected String city;
	protected String toWhereName;
	protected String expressName;
	protected String expressNo;
	protected Long processRunId;
	protected Department dept;
	protected ProjectNew projectNew;


	/**
	 * Default Empty Constructor for class ExpressApply
	 */
	public ExpressApply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ExpressApply
	 */
	public ExpressApply (
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
	 * @hibernate.property column="applyer" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getApplyer() {
		return this.applyer;
	}
	
	/**
	 * Set the applyer
	 * @spring.validator type="required"
	 */	
	public void setApplyer(String aValue) {
		this.applyer = aValue;
	}	

	/**
	 * 	 * @return Long
	 * @hibernate.property column="applyerId" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public Long getApplyerId() {
		return this.applyerId;
	}
	
	/**
	 * Set the applyerId
	 * @spring.validator type="required"
	 */	
	public void setApplyerId(Long aValue) {
		this.applyerId = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="applyDate" type="java.util.Date" length="19" not-null="true" unique="false"
	 */
	public java.util.Date getApplyDate() {
		return this.applyDate;
	}
	
	/**
	 * Set the applyDate
	 * @spring.validator type="required"
	 */	
	public void setApplyDate(java.util.Date aValue) {
		this.applyDate = aValue;
	}	

	/**
	 * 	 * @return Integer
	 * @hibernate.property column="expressType" type="java.lang.Integer" length="10" not-null="true" unique="false"
	 */
	public Integer getExpressType() {
		return this.expressType;
	}
	
	/**
	 * Set the expressType
	 * @spring.validator type="required"
	 */	
	public void setExpressType(Integer aValue) {
		this.expressType = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="province" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getProvince() {
		return this.province;
	}
	
	/**
	 * Set the province
	 */	
	public void setProvince(String aValue) {
		this.province = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="city" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getCity() {
		return this.city;
	}
	
	/**
	 * Set the city
	 */	
	public void setCity(String aValue) {
		this.city = aValue;
	}	

	
	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public ProjectNew getProjectNew() {
		return projectNew;
	}

	public void setProjectNew(ProjectNew projectNew) {
		this.projectNew = projectNew;
	}

	/**
	 * 	 * @return String
	 * @hibernate.property column="toWhereName" type="java.lang.String" length="500" not-null="false" unique="false"
	 */
	public String getToWhereName() {
		return this.toWhereName;
	}
	
	/**
	 * Set the toWhereName
	 */	
	public void setToWhereName(String aValue) {
		this.toWhereName = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="expressName" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getExpressName() {
		return this.expressName;
	}
	
	/**
	 * Set the expressName
	 */	
	public void setExpressName(String aValue) {
		this.expressName = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="expressNo" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getExpressNo() {
		return this.expressNo;
	}
	
	/**
	 * Set the expressNo
	 */	
	public void setExpressNo(String aValue) {
		this.expressNo = aValue;
	}	
	
	

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	
	



	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ExpressApply)) {
			return false;
		}
		ExpressApply rhs = (ExpressApply) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.applyer, rhs.applyer)
				.append(this.applyerId, rhs.applyerId)
				.append(this.applyDate, rhs.applyDate)
				.append(this.expressType, rhs.expressType)
				.append(this.province, rhs.province)
				.append(this.city, rhs.city)
				.append(this.toWhereName, rhs.toWhereName)
				.append(this.expressName, rhs.expressName)
				.append(this.expressNo, rhs.expressNo)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.applyer) 
				.append(this.applyerId) 
				.append(this.applyDate) 
				.append(this.expressType) 
				.append(this.province) 
				.append(this.city) 
				.append(this.toWhereName) 
				.append(this.expressName) 
				.append(this.expressNo) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("applyer", this.applyer) 
				.append("applyerId", this.applyerId) 
				.append("applyDate", this.applyDate) 
				.append("expressType", this.expressType) 
				.append("province", this.province) 
				.append("city", this.city) 
				.append("toWhereName", this.toWhereName) 
				.append("expressName", this.expressName) 
				.append("expressNo", this.expressNo) 
				.toString();
	}



}
