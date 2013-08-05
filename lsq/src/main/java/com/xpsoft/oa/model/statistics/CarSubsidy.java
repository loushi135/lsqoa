package com.xpsoft.oa.model.statistics;


import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.Department;
import com.xpsoft.oa.model.system.FileAttach;

/**
 * CarSubsidy Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class CarSubsidy extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;  
	@Expose
	protected String reporter;
	@Expose
	protected Department dept;
	@Expose
	protected String companyName;
	@Expose
	protected String carNo;
	@Expose
	protected String brand;
	@Expose
	protected String displacement;
	@Expose
	protected String boughtYear;
	@Expose
	protected String subsidyReason;
	@Expose
	protected Long processRunId;
	@Expose
	protected Set<FileAttach> fileAttachs = new HashSet<FileAttach>();

	/**
	 * Default Empty Constructor for class CarSubsidy
	 */
	public CarSubsidy () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class CarSubsidy
	 */
	public CarSubsidy (
		 Long in_id
        ) {
		this.setId(in_id);
    }

    

	

	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
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

	public Set<FileAttach> getFileAttachs() {
		return fileAttachs;
	}

	public void setFileAttachs(Set<FileAttach> fileAttachs) {
		this.fileAttachs = fileAttachs;
	}

	/**
	 * 报告人	 * @return String
	 * @hibernate.property column="reporter" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getReporter() {
		return this.reporter;
	}
	
	/**
	 * Set the reporter
	 */	
	public void setReporter(String aValue) {
		this.reporter = aValue;
	}	


	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	/**
	 * 所在公司	 * @return String
	 * @hibernate.property column="companyName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getCompanyName() {
		return this.companyName;
	}
	
	/**
	 * Set the companyName
	 */	
	public void setCompanyName(String aValue) {
		this.companyName = aValue;
	}	

	/**
	 * 车牌号	 * @return String
	 * @hibernate.property column="carNo" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getCarNo() {
		return this.carNo;
	}
	
	/**
	 * Set the carNo
	 */	
	public void setCarNo(String aValue) {
		this.carNo = aValue;
	}	

	/**
	 * 品牌型号	 * @return String
	 * @hibernate.property column="brand" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getBrand() {
		return this.brand;
	}
	
	/**
	 * Set the brand
	 */	
	public void setBrand(String aValue) {
		this.brand = aValue;
	}	

	/**
	 * 排量	 * @return String
	 * @hibernate.property column="displacement" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getDisplacement() {
		return this.displacement;
	}
	
	/**
	 * Set the displacement
	 */	
	public void setDisplacement(String aValue) {
		this.displacement = aValue;
	}	

	/**
	 * 购置年份	 * @return String
	 * @hibernate.property column="boughtYear" type="java.util.Date" length="19" not-null="false" unique="false"
	 */

	/**
	 * 申请原因	 * @return String
	 * @hibernate.property column="subsidyReason" type="java.lang.String" length="1000" not-null="false" unique="false"
	 */
	public String getSubsidyReason() {
		return this.subsidyReason;
	}
	
	public String getBoughtYear() {
		return boughtYear;
	}

	public void setBoughtYear(String boughtYear) {
		this.boughtYear = boughtYear;
	}

	/**
	 * Set the subsidyReason
	 */	
	public void setSubsidyReason(String aValue) {
		this.subsidyReason = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof CarSubsidy)) {
			return false;
		}
		CarSubsidy rhs = (CarSubsidy) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.reporter, rhs.reporter)
				.append(this.companyName, rhs.companyName)
				.append(this.carNo, rhs.carNo)
				.append(this.brand, rhs.brand)
				.append(this.displacement, rhs.displacement)
				.append(this.boughtYear, rhs.boughtYear)
				.append(this.subsidyReason, rhs.subsidyReason)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.reporter) 
				.append(this.companyName) 
				.append(this.carNo) 
				.append(this.brand) 
				.append(this.displacement) 
				.append(this.boughtYear) 
				.append(this.subsidyReason) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("reporter", this.reporter) 
				.append("companyName", this.companyName) 
				.append("carNo", this.carNo) 
				.append("brand", this.brand) 
				.append("displacement", this.displacement) 
				.append("boughtYear", this.boughtYear) 
				.append("subsidyReason", this.subsidyReason) 
				.toString();
	}



}
