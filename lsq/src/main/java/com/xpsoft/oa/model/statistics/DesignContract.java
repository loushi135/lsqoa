package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.core.model.BaseModel;

/**
 * DesignContract Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class DesignContract extends BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected String contractNo;
	@Expose
	protected String companyName;
	@Expose
	protected String designDept;
	@Expose
	protected String companyProperty;
	@Expose
	protected String cooperation;
	@Expose
	protected Integer cooperationNum;
	@Expose
	protected String companyCredit;
	@Expose
	protected String projectName;
	@Expose
	protected String projectAddr;
	@Expose
	protected java.math.BigDecimal contractAmount;
	@Expose
	protected java.math.BigDecimal designArea;
	@Expose
	protected java.math.BigDecimal singlePrice;
	@Expose
	protected java.math.BigDecimal projectPrice;
	@Expose
	protected java.math.BigDecimal chargeRate;
	@Expose
	protected String isEndWork;
	@Expose
	protected String workArea;
	@Expose
	protected String isLetDesignFee;
	@Expose
	protected Long processRunId;


	/**
	 * Default Empty Constructor for class DesignContract
	 */
	public DesignContract () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class DesignContract
	 */
	public DesignContract (
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

	/**
	 * 合同编号	 * @return String
	 * @hibernate.property column="contractNo" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getContractNo() {
		return this.contractNo;
	}
	
	/**
	 * Set the contractNo
	 */	
	public void setContractNo(String aValue) {
		this.contractNo = aValue;
	}	

	/**
	 * 甲方单位名称	 * @return String
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
	 * 设计部门	 * @return String
	 * @hibernate.property column="designDept" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getDesignDept() {
		return this.designDept;
	}
	
	/**
	 * Set the designDept
	 */	
	public void setDesignDept(String aValue) {
		this.designDept = aValue;
	}	

	/**
	 * 甲方单位性质	 * @return String
	 * @hibernate.property column="companyProperty" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getCompanyProperty() {
		return this.companyProperty;
	}
	
	/**
	 * Set the companyProperty
	 */	
	public void setCompanyProperty(String aValue) {
		this.companyProperty = aValue;
	}	

	/**
	 * 有无合作	 * @return String
	 * @hibernate.property column="cooperation" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getCooperation() {
		return this.cooperation;
	}
	
	/**
	 * Set the cooperation
	 */	
	public void setCooperation(String aValue) {
		this.cooperation = aValue;
	}	

	/**
	 * 合作次数	 * @return Integer
	 * @hibernate.property column="cooperationNum" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getCooperationNum() {
		return this.cooperationNum;
	}
	
	/**
	 * Set the cooperationNum
	 */	
	public void setCooperationNum(Integer aValue) {
		this.cooperationNum = aValue;
	}	

	/**
	 * 甲方资信评价	 * @return String
	 * @hibernate.property column="companyCredit" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getCompanyCredit() {
		return this.companyCredit;
	}
	
	/**
	 * Set the companyCredit
	 */	
	public void setCompanyCredit(String aValue) {
		this.companyCredit = aValue;
	}	

	/**
	 * 项目名称	 * @return String
	 * @hibernate.property column="projectName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getProjectName() {
		return this.projectName;
	}
	
	/**
	 * Set the projectName
	 */	
	public void setProjectName(String aValue) {
		this.projectName = aValue;
	}	

	/**
	 * 项目地点	 * @return String
	 * @hibernate.property column="projectAddr" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getProjectAddr() {
		return this.projectAddr;
	}
	
	/**
	 * Set the projectAddr
	 */	
	public void setProjectAddr(String aValue) {
		this.projectAddr = aValue;
	}	

	/**
	 * 合同总价	 * @return java.math.BigDecimal
	 * @hibernate.property column="contractAmount" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getContractAmount() {
		return this.contractAmount;
	}
	
	/**
	 * Set the contractAmount
	 */	
	public void setContractAmount(java.math.BigDecimal aValue) {
		this.contractAmount = aValue;
	}	

	/**
	 * 设计面积	 * @return java.math.BigDecimal
	 * @hibernate.property column="designArea" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getDesignArea() {
		return this.designArea;
	}
	
	/**
	 * Set the designArea
	 */	
	public void setDesignArea(java.math.BigDecimal aValue) {
		this.designArea = aValue;
	}	

	/**
	 * 单价	 * @return java.math.BigDecimal
	 * @hibernate.property column="singlePrice" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getSinglePrice() {
		return this.singlePrice;
	}
	
	/**
	 * Set the singlePrice
	 */	
	public void setSinglePrice(java.math.BigDecimal aValue) {
		this.singlePrice = aValue;
	}	

	/**
	 * 工程造价	 * @return java.math.BigDecimal
	 * @hibernate.property column="projectPrice" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getProjectPrice() {
		return this.projectPrice;
	}
	
	/**
	 * Set the projectPrice
	 */	
	public void setProjectPrice(java.math.BigDecimal aValue) {
		this.projectPrice = aValue;
	}	

	/**
	 * 取费率	 * @return java.math.BigDecimal
	 * @hibernate.property column="chargeRate" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getChargeRate() {
		return this.chargeRate;
	}
	
	/**
	 * Set the chargeRate
	 */	
	public void setChargeRate(java.math.BigDecimal aValue) {
		this.chargeRate = aValue;
	}	

	/**
	 * 是否承接后期施工	 * @return String
	 * @hibernate.property column="isEndWork" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getIsEndWork() {
		return this.isEndWork;
	}
	
	/**
	 * Set the isEndWork
	 */	
	public void setIsEndWork(String aValue) {
		this.isEndWork = aValue;
	}	

	/**
	 * 施工跟进区域	 * @return String
	 * @hibernate.property column="workArea" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getWorkArea() {
		return this.workArea;
	}
	
	/**
	 * Set the workArea
	 */	
	public void setWorkArea(String aValue) {
		this.workArea = aValue;
	}	

	/**
	 * 有无因承接后期施工而让利设计费情况	 * @return String
	 * @hibernate.property column="isLetDesignFee" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getIsLetDesignFee() {
		return this.isLetDesignFee;
	}
	
	/**
	 * Set the isLetDesignFee
	 */	
	public void setIsLetDesignFee(String aValue) {
		this.isLetDesignFee = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof DesignContract)) {
			return false;
		}
		DesignContract rhs = (DesignContract) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.contractNo, rhs.contractNo)
				.append(this.companyName, rhs.companyName)
				.append(this.designDept, rhs.designDept)
				.append(this.companyProperty, rhs.companyProperty)
				.append(this.cooperation, rhs.cooperation)
				.append(this.cooperationNum, rhs.cooperationNum)
				.append(this.companyCredit, rhs.companyCredit)
				.append(this.projectName, rhs.projectName)
				.append(this.projectAddr, rhs.projectAddr)
				.append(this.contractAmount, rhs.contractAmount)
				.append(this.designArea, rhs.designArea)
				.append(this.singlePrice, rhs.singlePrice)
				.append(this.projectPrice, rhs.projectPrice)
				.append(this.chargeRate, rhs.chargeRate)
				.append(this.isEndWork, rhs.isEndWork)
				.append(this.workArea, rhs.workArea)
				.append(this.isLetDesignFee, rhs.isLetDesignFee)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.contractNo) 
				.append(this.companyName) 
				.append(this.designDept) 
				.append(this.companyProperty) 
				.append(this.cooperation) 
				.append(this.cooperationNum) 
				.append(this.companyCredit) 
				.append(this.projectName) 
				.append(this.projectAddr) 
				.append(this.contractAmount) 
				.append(this.designArea) 
				.append(this.singlePrice) 
				.append(this.projectPrice) 
				.append(this.chargeRate) 
				.append(this.isEndWork) 
				.append(this.workArea) 
				.append(this.isLetDesignFee) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("contractNo", this.contractNo) 
				.append("companyName", this.companyName) 
				.append("designDept", this.designDept) 
				.append("companyProperty", this.companyProperty) 
				.append("cooperation", this.cooperation) 
				.append("cooperationNum", this.cooperationNum) 
				.append("companyCredit", this.companyCredit) 
				.append("projectName", this.projectName) 
				.append("projectAddr", this.projectAddr) 
				.append("contractAmount", this.contractAmount) 
				.append("designArea", this.designArea) 
				.append("singlePrice", this.singlePrice) 
				.append("projectPrice", this.projectPrice) 
				.append("chargeRate", this.chargeRate) 
				.append("isEndWork", this.isEndWork) 
				.append("workArea", this.workArea) 
				.append("isLetDesignFee", this.isLetDesignFee) 
				.toString();
	}



}
