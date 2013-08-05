package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.core.model.BaseModel;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Department;

/**
 * Constructioncontract Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class Constructioncontract extends BaseModel {
	@Expose
    protected Long contractId;
	@Expose
	protected String contractNo;
	@Expose
	protected String contractor;
	@Expose
	protected String projectRegional;
	@Expose
	protected String projectName;
	@Expose
	protected String projectCharger;
	@Expose
	protected String projectAbbreviation;
	@Expose
	protected String businessCharger;
	@Expose
	protected String linkmanAndTel;
	@Expose
	protected String numOrArea;
	@Expose
	protected java.math.BigDecimal sumPrice;
	@Expose
	protected String payWay;
	@Expose
	protected String projectTime;
	@Expose
	protected String quality;
	@Expose
	protected String isFullContract;
	@Expose
	protected String isDesignCost;
	@Expose
	protected java.math.BigDecimal designCost;
	@Expose
	protected String isModelCommunity;
	@Expose
	protected String guarantee;
	@Expose
	protected String constructionBackUp;
	@Expose
	protected String constructionBackUpPerson;
	@Expose
	protected java.util.Date constructionBackUpFinishTime;
	@Expose
	protected String constructionLicense;
	@Expose
	protected String constructionLicensePerson;
	@Expose
	protected java.util.Date constructionLicenseFinishTime;
	@Expose
	protected String isoverBudget;
	@Expose
	protected String quote;
	@Expose
	protected java.math.BigDecimal quoteloss;
	@Expose
	protected String remark;
	@Expose
	protected String meno;
	@Expose
	protected Integer status;//状态：0可用，1不可用
	@Expose
	protected String rewardOrPunish;
	@Expose
	protected Department deptRegional;//施工区域对象
	@Expose
	protected AppUser projectChargerUser;
	@Expose
	protected String attachIds;
	@Expose
	protected String attachFiles;
	@Expose
	protected AppUser constructionLicenseUser;
	@Expose
	protected AppUser constructionBackUpUser;
	@Expose
	protected Long processRunId;
	@Expose
	protected Integer contractVersion;//状态：0有施工合同，1有中标通知书，2有开工指令单
	@Expose
	protected String contractRemark;
	/**
	 * Default Empty Constructor for class Constructioncontract
	 */
	public Constructioncontract () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class Constructioncontract
	 */
	public Constructioncontract (
		 Long in_contractId
        ) {
		this.setContractId(in_contractId);
    }

    
	

	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
	}

	/**
	 * 	 * @return Long
     * @hibernate.id column="contractId" type="java.lang.Long" generator-class="native"
	 */
	public Long getContractId() {
		return this.contractId;
	}
	
	public Integer getContractVersion() {
		return contractVersion;
	}

	public void setContractVersion(Integer contractVersion) {
		this.contractVersion = contractVersion;
	}

	/**
	 * Set the contractId
	 */	
	public void setContractId(Long aValue) {
		this.contractId = aValue;
	}	

	public String getContractRemark() {
		return contractRemark;
	}

	public void setContractRemark(String contractRemark) {
		this.contractRemark = contractRemark;
	}

	/**
	 * 	 * @return String
	 * @hibernate.property column="contractNo" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getContractNo() {
		return this.contractNo;
	}
	
	/**
	 * Set the contractNo
	 * @spring.validator type="required"
	 */	
	public void setContractNo(String aValue) {
		this.contractNo = aValue;
	}	

	public String getRewardOrPunish() {
		return rewardOrPunish;
	}

	public void setRewardOrPunish(String rewardOrPunish) {
		this.rewardOrPunish = rewardOrPunish;
	}

	public Department getDeptRegional() {
		return deptRegional;
	}

	public void setDeptRegional(Department deptRegional) {
		this.deptRegional = deptRegional;
	}

	public AppUser getProjectChargerUser() {
		return projectChargerUser;
	}

	public void setProjectChargerUser(AppUser projectChargerUser) {
		this.projectChargerUser = projectChargerUser;
	}

	public String getAttachIds() {
		return attachIds;
	}

	public void setAttachIds(String attachIds) {
		this.attachIds = attachIds;
	}

	public String getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String attachFiles) {
		this.attachFiles = attachFiles;
	}

	public AppUser getConstructionLicenseUser() {
		return constructionLicenseUser;
	}

	public void setConstructionLicenseUser(AppUser constructionLicenseUser) {
		this.constructionLicenseUser = constructionLicenseUser;
	}

	public AppUser getConstructionBackUpUser() {
		return constructionBackUpUser;
	}

	public void setConstructionBackUpUser(AppUser constructionBackUpUser) {
		this.constructionBackUpUser = constructionBackUpUser;
	}

	/**
	 * 	 * @return String
	 * @hibernate.property column="contractor" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getContractor() {
		return this.contractor;
	}
	
	/**
	 * Set the contractor
	 * @spring.validator type="required"
	 */	
	public void setContractor(String aValue) {
		this.contractor = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="projectRegional" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getProjectRegional() {
		return this.projectRegional;
	}
	
	/**
	 * Set the projectRegional
	 * @spring.validator type="required"
	 */	
	public void setProjectRegional(String aValue) {
		this.projectRegional = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="projectName" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getProjectName() {
		return this.projectName;
	}
	
	/**
	 * Set the projectName
	 * @spring.validator type="required"
	 */	
	public void setProjectName(String aValue) {
		this.projectName = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="projectCharger" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getProjectCharger() {
		return this.projectCharger;
	}
	
	/**
	 * Set the projectCharger
	 * @spring.validator type="required"
	 */	
	public void setProjectCharger(String aValue) {
		this.projectCharger = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="projectAbbreviation" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getProjectAbbreviation() {
		return this.projectAbbreviation;
	}
	
	/**
	 * Set the projectAbbreviation
	 * @spring.validator type="required"
	 */	
	public void setProjectAbbreviation(String aValue) {
		this.projectAbbreviation = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="businessCharger" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getBusinessCharger() {
		return this.businessCharger;
	}
	
	/**
	 * Set the businessCharger
	 * @spring.validator type="required"
	 */	
	public void setBusinessCharger(String aValue) {
		this.businessCharger = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="linkmanAndTel" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getLinkmanAndTel() {
		return this.linkmanAndTel;
	}
	
	/**
	 * Set the linkmanAndTel
	 * @spring.validator type="required"
	 */	
	public void setLinkmanAndTel(String aValue) {
		this.linkmanAndTel = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="numOrArea" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getNumOrArea() {
		return this.numOrArea;
	}
	
	/**
	 * Set the numOrArea
	 * @spring.validator type="required"
	 */	
	public void setNumOrArea(String aValue) {
		this.numOrArea = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="sumPrice" type="java.math.BigDecimal" length="10" not-null="true" unique="false"
	 */
	public java.math.BigDecimal getSumPrice() {
		return this.sumPrice;
	}
	
	/**
	 * Set the sumPrice
	 * @spring.validator type="required"
	 */	
	public void setSumPrice(java.math.BigDecimal aValue) {
		this.sumPrice = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="payWay" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getPayWay() {
		return this.payWay;
	}
	
	/**
	 * Set the payWay
	 * @spring.validator type="required"
	 */	
	public void setPayWay(String aValue) {
		this.payWay = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="projectTime" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getProjectTime() {
		return this.projectTime;
	}
	
	/**
	 * Set the projectTime
	 * @spring.validator type="required"
	 */	
	public void setProjectTime(String aValue) {
		this.projectTime = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="quality" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getQuality() {
		return this.quality;
	}
	
	/**
	 * Set the quality
	 * @spring.validator type="required"
	 */	
	public void setQuality(String aValue) {
		this.quality = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="isFullContract" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getIsFullContract() {
		return this.isFullContract;
	}
	
	/**
	 * Set the isFullContract
	 * @spring.validator type="required"
	 */	
	public void setIsFullContract(String aValue) {
		this.isFullContract = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="isDesignCost" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getIsDesignCost() {
		return this.isDesignCost;
	}
	
	/**
	 * Set the isDesignCost
	 * @spring.validator type="required"
	 */	
	public void setIsDesignCost(String aValue) {
		this.isDesignCost = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="designCost" type="java.math.BigDecimal" length="10" not-null="true" unique="false"
	 */
	public java.math.BigDecimal getDesignCost() {
		return this.designCost;
	}
	
	/**
	 * Set the designCost
	 * @spring.validator type="required"
	 */	
	public void setDesignCost(java.math.BigDecimal aValue) {
		this.designCost = aValue;
	}	

	/**
	 * 文明工地	 * @return String
	 * @hibernate.property column="isModelCommunity" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getIsModelCommunity() {
		return this.isModelCommunity;
	}
	
	/**
	 * Set the isModelCommunity
	 * @spring.validator type="required"
	 */	
	public void setIsModelCommunity(String aValue) {
		this.isModelCommunity = aValue;
	}	

	/**
	 * 保修期	 * @return String
	 * @hibernate.property column="guarantee" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getGuarantee() {
		return this.guarantee;
	}
	
	/**
	 * Set the guarantee
	 * @spring.validator type="required"
	 */	
	public void setGuarantee(String aValue) {
		this.guarantee = aValue;
	}	

	/**
	 * 施工备案	 * @return String
	 * @hibernate.property column="constructionBackUp" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getConstructionBackUp() {
		return this.constructionBackUp;
	}
	
	/**
	 * Set the constructionBackUp
	 * @spring.validator type="required"
	 */	
	public void setConstructionBackUp(String aValue) {
		this.constructionBackUp = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="constructionBackUpPerson" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getConstructionBackUpPerson() {
		return this.constructionBackUpPerson;
	}
	
	/**
	 * Set the constructionBackUpPerson
	 * @spring.validator type="required"
	 */	
	public void setConstructionBackUpPerson(String aValue) {
		this.constructionBackUpPerson = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="constructionBackUpFinishTime" type="java.util.Date" length="10" not-null="true" unique="false"
	 */
	public java.util.Date getConstructionBackUpFinishTime() {
		return this.constructionBackUpFinishTime;
	}
	
	/**
	 * Set the constructionBackUpFinishTime
	 * @spring.validator type="required"
	 */	
	public void setConstructionBackUpFinishTime(java.util.Date aValue) {
		this.constructionBackUpFinishTime = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="constructionLicense" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getConstructionLicense() {
		return this.constructionLicense;
	}
	
	/**
	 * Set the constructionLicense
	 * @spring.validator type="required"
	 */	
	public void setConstructionLicense(String aValue) {
		this.constructionLicense = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="constructionLicensePerson" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getConstructionLicensePerson() {
		return this.constructionLicensePerson;
	}
	
	/**
	 * Set the constructionLicensePerson
	 * @spring.validator type="required"
	 */	
	public void setConstructionLicensePerson(String aValue) {
		this.constructionLicensePerson = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="constructionLicenseFinishTime" type="java.util.Date" length="10" not-null="true" unique="false"
	 */
	public java.util.Date getConstructionLicenseFinishTime() {
		return this.constructionLicenseFinishTime;
	}
	
	/**
	 * Set the constructionLicenseFinishTime
	 * @spring.validator type="required"
	 */	
	public void setConstructionLicenseFinishTime(java.util.Date aValue) {
		this.constructionLicenseFinishTime = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="isoverBudget" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getIsoverBudget() {
		return this.isoverBudget;
	}
	
	/**
	 * Set the isoverBudget
	 * @spring.validator type="required"
	 */	
	public void setIsoverBudget(String aValue) {
		this.isoverBudget = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="quote" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getQuote() {
		return this.quote;
	}
	
	/**
	 * Set the quote
	 * @spring.validator type="required"
	 */	
	public void setQuote(String aValue) {
		this.quote = aValue;
	}	

	/**
	 * 	 * @return java.math.BigDecimal
	 * @hibernate.property column="quoteloss" type="java.math.BigDecimal" length="10" not-null="true" unique="false"
	 */
	public java.math.BigDecimal getQuoteloss() {
		return this.quoteloss;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * Set the quoteloss
	 * @spring.validator type="required"
	 */	
	public void setQuoteloss(java.math.BigDecimal aValue) {
		this.quoteloss = aValue;
	}	

	/**
	 * 其他应在签订前商谈的事项	 * @return String
	 * @hibernate.property column="remark" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getRemark() {
		return this.remark;
	}
	
	/**
	 * Set the remark
	 * @spring.validator type="required"
	 */	
	public void setRemark(String aValue) {
		this.remark = aValue;
	}	

	/**
	 * 应在合同履行过程中注意的事项	 * @return String
	 * @hibernate.property column="meno" type="java.lang.String" length="255" not-null="true" unique="false"
	 */
	public String getMeno() {
		return this.meno;
	}
	
	/**
	 * Set the meno
	 * @spring.validator type="required"
	 */	
	public void setMeno(String aValue) {
		this.meno = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Constructioncontract)) {
			return false;
		}
		Constructioncontract rhs = (Constructioncontract) object;
		return new EqualsBuilder()
				.append(this.contractId, rhs.contractId)
				.append(this.contractNo, rhs.contractNo)
				.append(this.contractor, rhs.contractor)
				.append(this.projectRegional, rhs.projectRegional)
				.append(this.projectName, rhs.projectName)
				.append(this.projectCharger, rhs.projectCharger)
				.append(this.projectAbbreviation, rhs.projectAbbreviation)
				.append(this.businessCharger, rhs.businessCharger)
				.append(this.linkmanAndTel, rhs.linkmanAndTel)
				.append(this.numOrArea, rhs.numOrArea)
				.append(this.sumPrice, rhs.sumPrice)
				.append(this.payWay, rhs.payWay)
				.append(this.projectTime, rhs.projectTime)
				.append(this.quality, rhs.quality)
				.append(this.isFullContract, rhs.isFullContract)
				.append(this.isDesignCost, rhs.isDesignCost)
				.append(this.designCost, rhs.designCost)
				.append(this.isModelCommunity, rhs.isModelCommunity)
				.append(this.guarantee, rhs.guarantee)
				.append(this.constructionBackUp, rhs.constructionBackUp)
				.append(this.constructionBackUpPerson, rhs.constructionBackUpPerson)
				.append(this.constructionBackUpFinishTime, rhs.constructionBackUpFinishTime)
				.append(this.constructionLicense, rhs.constructionLicense)
				.append(this.constructionLicensePerson, rhs.constructionLicensePerson)
				.append(this.constructionLicenseFinishTime, rhs.constructionLicenseFinishTime)
				.append(this.isoverBudget, rhs.isoverBudget)
				.append(this.quote, rhs.quote)
				.append(this.quoteloss, rhs.quoteloss)
				.append(this.remark, rhs.remark)
				.append(this.meno, rhs.meno)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.contractId) 
				.append(this.contractNo) 
				.append(this.contractor) 
				.append(this.projectRegional) 
				.append(this.projectName) 
				.append(this.projectCharger) 
				.append(this.projectAbbreviation) 
				.append(this.businessCharger) 
				.append(this.linkmanAndTel) 
				.append(this.numOrArea) 
				.append(this.sumPrice) 
				.append(this.payWay) 
				.append(this.projectTime) 
				.append(this.quality) 
				.append(this.isFullContract) 
				.append(this.isDesignCost) 
				.append(this.designCost) 
				.append(this.isModelCommunity) 
				.append(this.guarantee) 
				.append(this.constructionBackUp) 
				.append(this.constructionBackUpPerson) 
				.append(this.constructionBackUpFinishTime) 
				.append(this.constructionLicense) 
				.append(this.constructionLicensePerson) 
				.append(this.constructionLicenseFinishTime) 
				.append(this.isoverBudget) 
				.append(this.quote) 
				.append(this.quoteloss) 
				.append(this.remark) 
				.append(this.meno) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("contractId", this.contractId) 
				.append("contractNo", this.contractNo) 
				.append("contractor", this.contractor) 
				.append("projectRegional", this.projectRegional) 
				.append("projectName", this.projectName) 
				.append("projectCharger", this.projectCharger) 
				.append("projectAbbreviation", this.projectAbbreviation) 
				.append("businessCharger", this.businessCharger) 
				.append("linkmanAndTel", this.linkmanAndTel) 
				.append("numOrArea", this.numOrArea) 
				.append("sumPrice", this.sumPrice) 
				.append("payWay", this.payWay) 
				.append("projectTime", this.projectTime) 
				.append("quality", this.quality) 
				.append("isFullContract", this.isFullContract) 
				.append("isDesignCost", this.isDesignCost) 
				.append("designCost", this.designCost) 
				.append("isModelCommunity", this.isModelCommunity) 
				.append("guarantee", this.guarantee) 
				.append("constructionBackUp", this.constructionBackUp) 
				.append("constructionBackUpPerson", this.constructionBackUpPerson) 
				.append("constructionBackUpFinishTime", this.constructionBackUpFinishTime) 
				.append("constructionLicense", this.constructionLicense) 
				.append("constructionLicensePerson", this.constructionLicensePerson) 
				.append("constructionLicenseFinishTime", this.constructionLicenseFinishTime) 
				.append("isoverBudget", this.isoverBudget) 
				.append("quote", this.quote) 
				.append("quoteloss", this.quoteloss) 
				.append("remark", this.remark) 
				.append("meno", this.meno) 
				.toString();
	}



}
