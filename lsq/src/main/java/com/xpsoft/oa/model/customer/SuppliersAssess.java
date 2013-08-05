package com.xpsoft.oa.model.customer;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.City;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.model.system.Province;

/**
 * SuppliersAssess Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class SuppliersAssess extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long suppliersId;  
	@Expose
	protected String suppliersNo;
	@Expose
	protected String suppliersName;//供应商名称(单位名称)
	@Expose
	protected String cooperateType;//合作类型
	@Expose
	protected String suppliersNature;//供应商性质
	@Expose
	protected String mainProduct;
	@Expose
	protected String supplierAddress;//注册地址(通讯地址)
	@Expose
	protected String legalRepresentative;
	@Expose
	protected String registeredCapital;//注册资金
	@Expose
	protected String establishDate;
	@Expose
	protected String supplierContacter;//业务联系人
	@Expose
	protected String supplierPhone;//联系电话（固话/手机）
	@Expose
	protected String companyNature;
	@Expose
	protected String suppliersbank;//开户银行
	@Expose
	protected String bankAccount;//开户账号
	@Expose
	protected Integer staffNum;//雇员总人数
	@Expose
	protected Integer managerNum;//管理人员
	@Expose
	protected Integer salesNum;//营销人员
	@Expose
	protected Integer techNum;//技术人员
	@Expose
	protected String institutionNature;
	@Expose
	protected String businessArea;
	@Expose
	protected String brand;
	@Expose
	protected String systemType;
	@Expose
	protected String delFlag;
	@Expose
	protected String recommendReason;//推荐原因
	@Expose
	protected Long processRunId;
	@Expose
	protected Province province;
	@Expose
	protected City city;
	protected Date createtime;
	@Expose
	protected String inviteUserName;//推荐人
	@Expose
	protected String zip;//邮      编
	@Expose
	protected String officeTel;//办公电话
	@Expose
	protected String fax;//传真
	@Expose
	protected String email;
	@Expose
	protected Integer fixedStaffNum;//固定员工人数
	@Expose
	protected Integer techDebugNum;//其中专业技术安装调试人员数
	@Expose
	protected Integer leaderNum;//其中班组长人数 
	@Expose
	protected Integer validElecNum;//持有效电工证工人数
	@Expose
	protected Integer validWelderNum;//持有效焊工证工人数
	@Expose
	protected Integer validQualifyNum;//持资格证书
	@Expose
	protected Integer peakNum;//高峰可上人员
	@Expose
	protected String mainArea;//工人主要来自地区
	@Expose
	protected String previousYearOutput;//前年工程产值
	@Expose
	protected String lastYearOutput;//去年工程产值
	@Expose
	protected String mainInfo;//主要骨干姓名及联系电话擅长系统信息
	@Expose
	private AppUser applyUser;//经办人
	@Expose
	private Integer type;//0 供应商   1班组
	@Expose
	private ProjectNew project;
	@Expose
	protected Set<FileAttach> fileAttachs = new HashSet<FileAttach>();
	public final static int SUPPLIER_TYPE = 0;
	public final static int TEAM_TYPE = 1;
	/**
	 * Default Empty Constructor for class SuppliersAssess
	 */
	public SuppliersAssess () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class SuppliersAssess
	 */
	public SuppliersAssess (
		 Long in_suppliersId
        ) {
		this.setSuppliersId(in_suppliersId);
    }

    

	/**
	 * id	 * @return Long
     * @hibernate.id column="suppliersId" type="java.lang.Long" generator-class="native"
	 */
	public Long getSuppliersId() {
		return this.suppliersId;
	}
	
	/**
	 * Set the suppliersId
	 */	
	public void setSuppliersId(Long aValue) {
		this.suppliersId = aValue;
	}

	public String getSuppliersNo() {
		return suppliersNo;
	}

	public void setSuppliersNo(String suppliersNo) {
		this.suppliersNo = suppliersNo;
	}

	public String getSuppliersName() {
		return suppliersName;
	}

	public ProjectNew getProject() {
		return project;
	}

	public void setProject(ProjectNew project) {
		this.project = project;
	}

	public void setSuppliersName(String suppliersName) {
		this.suppliersName = suppliersName;
	}

	public String getSuppliersNature() {
		return suppliersNature;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setSuppliersNature(String suppliersNature) {
		this.suppliersNature = suppliersNature;
	}

	public String getMainProduct() {
		return mainProduct;
	}

	public void setMainProduct(String mainProduct) {
		this.mainProduct = mainProduct;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	public String getRecommendReason() {
		return recommendReason;
	}

	public void setRecommendReason(String recommendReason) {
		this.recommendReason = recommendReason;
	}

	public Set<FileAttach> getFileAttachs() {
		return fileAttachs;
	}

	public void setFileAttachs(Set<FileAttach> fileAttachs) {
		this.fileAttachs = fileAttachs;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getEstablishDate() {
		return establishDate;
	}

	public void setEstablishDate(String establishDate) {
		this.establishDate = establishDate;
	}

	public String getSupplierContacter() {
		return supplierContacter;
	}

	public void setSupplierContacter(String supplierContacter) {
		this.supplierContacter = supplierContacter;
	}

	public String getSupplierPhone() {
		return supplierPhone;
	}

	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}

	public String getCompanyNature() {
		return companyNature;
	}

	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}

	public String getSuppliersbank() {
		return suppliersbank;
	}

	public void setSuppliersbank(String suppliersbank) {
		this.suppliersbank = suppliersbank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Integer getStaffNum() {
		return staffNum;
	}

	public void setStaffNum(Integer staffNum) {
		this.staffNum = staffNum;
	}

	public Integer getManagerNum() {
		return managerNum;
	}

	public void setManagerNum(Integer managerNum) {
		this.managerNum = managerNum;
	}

	public Integer getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(Integer salesNum) {
		this.salesNum = salesNum;
	}

	public Integer getTechNum() {
		return techNum;
	}

	public void setTechNum(Integer techNum) {
		this.techNum = techNum;
	}

	public String getInstitutionNature() {
		return institutionNature;
	}

	public void setInstitutionNature(String institutionNature) {
		this.institutionNature = institutionNature;
	}

	public String getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
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

	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
	}

	public String getCooperateType() {
		return cooperateType;
	}

	public void setCooperateType(String cooperateType) {
		this.cooperateType = cooperateType;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getInviteUserName() {
		return inviteUserName;
	}

	public void setInviteUserName(String inviteUserName) {
		this.inviteUserName = inviteUserName;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getFixedStaffNum() {
		return fixedStaffNum;
	}

	public void setFixedStaffNum(Integer fixedStaffNum) {
		this.fixedStaffNum = fixedStaffNum;
	}

	public Integer getTechDebugNum() {
		return techDebugNum;
	}

	public void setTechDebugNum(Integer techDebugNum) {
		this.techDebugNum = techDebugNum;
	}

	public Integer getLeaderNum() {
		return leaderNum;
	}

	public void setLeaderNum(Integer leaderNum) {
		this.leaderNum = leaderNum;
	}

	public Integer getValidElecNum() {
		return validElecNum;
	}

	public void setValidElecNum(Integer validElecNum) {
		this.validElecNum = validElecNum;
	}

	public Integer getValidWelderNum() {
		return validWelderNum;
	}

	public void setValidWelderNum(Integer validWelderNum) {
		this.validWelderNum = validWelderNum;
	}

	public Integer getValidQualifyNum() {
		return validQualifyNum;
	}

	public void setValidQualifyNum(Integer validQualifyNum) {
		this.validQualifyNum = validQualifyNum;
	}

	public Integer getPeakNum() {
		return peakNum;
	}

	public void setPeakNum(Integer peakNum) {
		this.peakNum = peakNum;
	}

	public String getMainArea() {
		return mainArea;
	}

	public void setMainArea(String mainArea) {
		this.mainArea = mainArea;
	}

	public String getPreviousYearOutput() {
		return previousYearOutput;
	}

	public void setPreviousYearOutput(String previousYearOutput) {
		this.previousYearOutput = previousYearOutput;
	}

	public String getLastYearOutput() {
		return lastYearOutput;
	}

	public void setLastYearOutput(String lastYearOutput) {
		this.lastYearOutput = lastYearOutput;
	}

	public String getMainInfo() {
		return mainInfo;
	}

	public void setMainInfo(String mainInfo) {
		this.mainInfo = mainInfo;
	}

	public AppUser getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(AppUser applyUser) {
		this.applyUser = applyUser;
	}
	
}
