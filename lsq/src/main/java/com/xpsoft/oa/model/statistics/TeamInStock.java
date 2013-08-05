package com.xpsoft.oa.model.statistics;


import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.ProcessRun;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.system.AppUserService;

/**
 * TeamInStock Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class TeamInStock extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected String unitName;
	@Expose
	protected String inviteUserName;
	@Expose
	protected String legalRepresent;
	@Expose
	protected String contact;
	@Expose
	protected String address;
	@Expose
	protected String contactTel;
	@Expose
	protected String registerMoney;
	@Expose
	protected String zip;
	@Expose
	protected String officeTel;
	@Expose
	protected String fax;
	@Expose
	protected String email;
	@Expose
	protected Integer staffNum;
	@Expose
	protected Integer techNum;
	@Expose
	protected Integer leaderNum;
	@Expose
	protected Integer validElecNum;
	@Expose
	protected Integer validWelderNum;
	@Expose
	protected Integer validQualifyNum;
	@Expose
	protected Integer peakNum;
	@Expose
	protected String mainArea;
	@Expose
	protected String previousYearOutput;
	@Expose
	protected String lastYearOutput;
	@Expose
	protected String mainInfo;
	@Expose
	protected String inviteReason;
	@Expose
	protected Long applyUserId;
	@Expose
	protected Set<FileAttach> fileAttachs = new HashSet<FileAttach>();
	@Expose
	protected Long processRunId;
	@Expose
	private AppUser applyUser;
	@Expose
	private String bankName;
	@Expose
	private String bankAccount;
	
	public Set<FileAttach> getFileAttachs() {
		return fileAttachs;
	}

	public void setFileAttachs(Set<FileAttach> fileAttachs) {
		this.fileAttachs = fileAttachs;
	}

	/**
	 * Default Empty Constructor for class TeamInStock
	 */
	public TeamInStock () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class TeamInStock
	 */
	public TeamInStock (
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
	 * 单位名称	 * @return String
	 * @hibernate.property column="unitName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getUnitName() {
		return this.unitName;
	}
	
	/**
	 * Set the unitName
	 */	
	public void setUnitName(String aValue) {
		this.unitName = aValue;
	}	

	/**
	 * 推荐人	 * @return String
	 * @hibernate.property column="inviteUserName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getInviteUserName() {
		return this.inviteUserName;
	}
	
	/**
	 * Set the inviteUserName
	 */	
	public void setInviteUserName(String aValue) {
		this.inviteUserName = aValue;
	}	

	/**
	 * 法定代表人	 * @return String
	 * @hibernate.property column="legalRepresent" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getLegalRepresent() {
		return this.legalRepresent;
	}
	
	/**
	 * Set the legalRepresent
	 */	
	public void setLegalRepresent(String aValue) {
		this.legalRepresent = aValue;
	}	

	/**
	 * 联系人	 * @return String
	 * @hibernate.property column="contact" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getContact() {
		return this.contact;
	}
	
	/**
	 * Set the contact
	 */	
	public void setContact(String aValue) {
		this.contact = aValue;
	}	

	/**
	 * 通讯地址	 * @return String
	 * @hibernate.property column="address" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getAddress() {
		return this.address;
	}
	
	/**
	 * Set the address
	 */	
	public void setAddress(String aValue) {
		this.address = aValue;
	}	

	/**
	 * 联系人电话	 * @return String
	 * @hibernate.property column="contactTel" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getContactTel() {
		return this.contactTel;
	}
	
	/**
	 * Set the contactTel
	 */	
	public void setContactTel(String aValue) {
		this.contactTel = aValue;
	}	

	/**
	 * 注册资金	 * @return String
	 * @hibernate.property column="registerMoney" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getRegisterMoney() {
		return this.registerMoney;
	}
	
	/**
	 * Set the registerMoney
	 */	
	public void setRegisterMoney(String aValue) {
		this.registerMoney = aValue;
	}	

	/**
	 * 邮      编	 * @return String
	 * @hibernate.property column="zip" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getZip() {
		return this.zip;
	}
	
	/**
	 * Set the zip
	 */	
	public void setZip(String aValue) {
		this.zip = aValue;
	}	

	/**
	 * 办公电话	 * @return String
	 * @hibernate.property column="officeTel" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getOfficeTel() {
		return this.officeTel;
	}
	
	/**
	 * Set the officeTel
	 */	
	public void setOfficeTel(String aValue) {
		this.officeTel = aValue;
	}	

	/**
	 * 传真	 * @return String
	 * @hibernate.property column="fax" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getFax() {
		return this.fax;
	}
	
	/**
	 * Set the fax
	 */	
	public void setFax(String aValue) {
		this.fax = aValue;
	}	

	/**
	 * email	 * @return String
	 * @hibernate.property column="email" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Set the email
	 */	
	public void setEmail(String aValue) {
		this.email = aValue;
	}	

	/**
	 * 固定员工人数	 * @return Integer
	 * @hibernate.property column="staffNum" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getStaffNum() {
		return this.staffNum;
	}
	
	/**
	 * Set the staffNum
	 */	
	public void setStaffNum(Integer aValue) {
		this.staffNum = aValue;
	}	

	/**
	 * 其中专业技术安装调试人员数	 * @return Integer
	 * @hibernate.property column="techNum" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getTechNum() {
		return this.techNum;
	}
	
	/**
	 * Set the techNum
	 */	
	public void setTechNum(Integer aValue) {
		this.techNum = aValue;
	}	

	/**
	 * 其中班组长人数 	 * @return Integer
	 * @hibernate.property column="leaderNum" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getLeaderNum() {
		return this.leaderNum;
	}
	
	/**
	 * Set the leaderNum
	 */	
	public void setLeaderNum(Integer aValue) {
		this.leaderNum = aValue;
	}	

	/**
	 * 持有效电工证工人数	 * @return Integer
	 * @hibernate.property column="validElecNum" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getValidElecNum() {
		return this.validElecNum;
	}
	
	/**
	 * Set the validElecNum
	 */	
	public void setValidElecNum(Integer aValue) {
		this.validElecNum = aValue;
	}	

	/**
	 * 持有效焊工证工人数	 * @return Integer
	 * @hibernate.property column="validWelderNum" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getValidWelderNum() {
		return this.validWelderNum;
	}
	
	/**
	 * Set the validWelderNum
	 */	
	public void setValidWelderNum(Integer aValue) {
		this.validWelderNum = aValue;
	}	

	/**
	 * 持资格证书	 * @return Integer
	 * @hibernate.property column="validQualifyNum" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getValidQualifyNum() {
		return this.validQualifyNum;
	}
	
	/**
	 * Set the validQualifyNum
	 */	
	public void setValidQualifyNum(Integer aValue) {
		this.validQualifyNum = aValue;
	}	

	/**
	 * 高峰可上人员	 * @return Integer
	 * @hibernate.property column="peakNum" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getPeakNum() {
		return this.peakNum;
	}
	
	/**
	 * Set the peakNum
	 */	
	public void setPeakNum(Integer aValue) {
		this.peakNum = aValue;
	}	

	/**
	 * 工人主要来自地区	 * @return String
	 * @hibernate.property column="mainArea" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getMainArea() {
		return this.mainArea;
	}
	
	/**
	 * Set the mainArea
	 */	
	public void setMainArea(String aValue) {
		this.mainArea = aValue;
	}	

	/**
	 * 前年工程产值	 * @return String
	 * @hibernate.property column="previousYearOutput" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getPreviousYearOutput() {
		return this.previousYearOutput;
	}
	
	/**
	 * Set the previousYearOutput
	 */	
	public void setPreviousYearOutput(String aValue) {
		this.previousYearOutput = aValue;
	}	

	/**
	 * 去年工程产值	 * @return String
	 * @hibernate.property column="lastYearOutput" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getLastYearOutput() {
		return this.lastYearOutput;
	}
	
	/**
	 * Set the lastYearOutput
	 */	
	public void setLastYearOutput(String aValue) {
		this.lastYearOutput = aValue;
	}	

	/**
	 * 主要骨干姓名及联系电话擅长系统信息	 * @return String
	 * @hibernate.property column="mainInfo" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getMainInfo() {
		return this.mainInfo;
	}
	
	/**
	 * Set the mainInfo
	 */	
	public void setMainInfo(String aValue) {
		this.mainInfo = aValue;
	}	

	/**
	 * 推荐原因	 * @return String
	 * @hibernate.property column="inviteReason" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getInviteReason() {
		return this.inviteReason;
	}
	
	/**
	 * Set the inviteReason
	 */	
	public void setInviteReason(String aValue) {
		this.inviteReason = aValue;
	}	

	/**
	 * 经办人	 * @return Long
	 * @hibernate.property column="applyUserId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getApplyUserId() {
		return this.applyUserId;
	}
	
	/**
	 * Set the applyUserId
	 */	
	public void setApplyUserId(Long aValue) {
		this.applyUserId = aValue;
//		AppUserService service= (AppUserService)AppUtil.getBean("appUserService");
//		this.applyUser=service.get(aValue);
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof TeamInStock)) {
			return false;
		}
		TeamInStock rhs = (TeamInStock) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.unitName, rhs.unitName)
				.append(this.inviteUserName, rhs.inviteUserName)
				.append(this.legalRepresent, rhs.legalRepresent)
				.append(this.contact, rhs.contact)
				.append(this.address, rhs.address)
				.append(this.contactTel, rhs.contactTel)
				.append(this.registerMoney, rhs.registerMoney)
				.append(this.zip, rhs.zip)
				.append(this.officeTel, rhs.officeTel)
				.append(this.fax, rhs.fax)
				.append(this.email, rhs.email)
				.append(this.staffNum, rhs.staffNum)
				.append(this.techNum, rhs.techNum)
				.append(this.leaderNum, rhs.leaderNum)
				.append(this.validElecNum, rhs.validElecNum)
				.append(this.validWelderNum, rhs.validWelderNum)
				.append(this.validQualifyNum, rhs.validQualifyNum)
				.append(this.peakNum, rhs.peakNum)
				.append(this.mainArea, rhs.mainArea)
				.append(this.previousYearOutput, rhs.previousYearOutput)
				.append(this.lastYearOutput, rhs.lastYearOutput)
				.append(this.mainInfo, rhs.mainInfo)
				.append(this.inviteReason, rhs.inviteReason)
				.append(this.applyUserId, rhs.applyUserId)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.unitName) 
				.append(this.inviteUserName) 
				.append(this.legalRepresent) 
				.append(this.contact) 
				.append(this.address) 
				.append(this.contactTel) 
				.append(this.registerMoney) 
				.append(this.zip) 
				.append(this.officeTel) 
				.append(this.fax) 
				.append(this.email) 
				.append(this.staffNum) 
				.append(this.techNum) 
				.append(this.leaderNum) 
				.append(this.validElecNum) 
				.append(this.validWelderNum) 
				.append(this.validQualifyNum) 
				.append(this.peakNum) 
				.append(this.mainArea) 
				.append(this.previousYearOutput) 
				.append(this.lastYearOutput) 
				.append(this.mainInfo) 
				.append(this.inviteReason) 
				.append(this.applyUserId) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("unitName", this.unitName) 
				.append("inviteUserName", this.inviteUserName) 
				.append("legalRepresent", this.legalRepresent) 
				.append("contact", this.contact) 
				.append("address", this.address) 
				.append("contactTel", this.contactTel) 
				.append("registerMoney", this.registerMoney) 
				.append("zip", this.zip) 
				.append("officeTel", this.officeTel) 
				.append("fax", this.fax) 
				.append("email", this.email) 
				.append("staffNum", this.staffNum) 
				.append("techNum", this.techNum) 
				.append("leaderNum", this.leaderNum) 
				.append("validElecNum", this.validElecNum) 
				.append("validWelderNum", this.validWelderNum) 
				.append("validQualifyNum", this.validQualifyNum) 
				.append("peakNum", this.peakNum) 
				.append("mainArea", this.mainArea) 
				.append("previousYearOutput", this.previousYearOutput) 
				.append("lastYearOutput", this.lastYearOutput) 
				.append("mainInfo", this.mainInfo) 
				.append("inviteReason", this.inviteReason) 
				.append("applyUserId", this.applyUserId) 
				.toString();
	}

	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
	}

	public AppUser getApplyUser() {
		return applyUser;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public void setApplyUser(AppUser applyUser) {
		this.applyUser = applyUser;
	}
}
