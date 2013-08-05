package org.model;

import java.io.Serializable;

import com.xpsoft.core.model.BaseModel;
public class SyncUserInfo extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5219414876642250224L;
	//员工基本信息
	private Long userId;
	private String fullname;
	private String username;
	private String staffNo;
	private String depId;
	private String depName;
	private String position;
	private String phone;
	private String mobile;
	private String mobile2;
	private String fax;
	private String address;
	private String zip;
	private String photo;
	private String assessionTime;
	private String departureTime;
	private String status;
	private String education;
	private String sex;//对应appuser title
	private String faxPhone;
	//员工档案
	private String profileNo;//档案号
	private String birthday;//出生日期
	private String eduMajor;//专业
	private String eduCollege;//毕业院校
	private String startWorkDate;
	private String idCard;//身份证号
	private String innerCommentTitle;//内评职称/其他证书
	private String rePayWageTime;//补发工资时间
	private String profilePlace;//档案所在机构
	private String graduateDate;//毕业时间
	private String hrmTitleDesc;//人事职称描述:
	private String healthStatus;//健康状况
	private String newStaffTrain;//新员工培训批次
	private String approvalTime;//审批录入年月
	private String statisticsTime;//统计批次
	private String masterCeremony;//拜师仪式
	private String dicEduDegree;//学历
	private String dicWorkContractType;//劳动合同类型
	private String dicMarriage;//婚姻状况
	private String dicParty;//政治面貌
	private String dicNationality;//国籍
	private String dicRace;//民族
	private String dicBloodType;//血型
	private String dicPlait;//编制
	private String dicProfessionType;//职类
	private String dicProfessionLevel;//职级
	private String dicHrmTitle;//人事职称
	private String dicHrmTitleLevel;//职级级别
	private String dicNewTypeCommitment;//新版承诺
	private String dicProManagerLevel;//项目经理等级
	private String dicSocialSecurityPlace;//社保缴纳地
	private String rosterJobName;//花名册岗位
	private String workContractEndDate;//劳动合同到期时间
	private String updateTime;
	public SyncUserInfo() {
		super();
	}
	public SyncUserInfo(Long userId, String fullname, String username,
			String staffNo, String depId, String depName, String position,
			String phone, String mobile, String mobile2, String fax,
			String address, String zip, String photo, String assessionTime,
			String departureTime, String status, String education, String sex,
			String faxPhone, String profileNo, String birthday,
			String eduMajor, String eduCollege, String startWorkDate,
			String idCard, String innerCommentTitle, String rePayWageTime,
			String profilePlace, String graduateDate, String hrmTitleDesc,
			String healthStatus, String newStaffTrain, String approvalTime,
			String statisticsTime, String masterCeremony, String dicEduDegree,
			String dicWorkContractType, String dicMarriage, String dicParty,
			String dicNationality, String dicRace, String dicBloodType,
			String dicPlait, String dicProfessionType,
			String dicProfessionLevel, String dicHrmTitle,
			String dicHrmTitleLevel, String dicNewTypeCommitment,
			String dicProManagerLevel, String dicSocialSecurityPlace,
			String rosterJobName, String workContractEndDate, String updateTime) {
		super();
		this.userId = userId;
		this.fullname = fullname;
		this.username = username;
		this.staffNo = staffNo;
		this.depId = depId;
		this.depName = depName;
		this.position = position;
		this.phone = phone;
		this.mobile = mobile;
		this.mobile2 = mobile2;
		this.fax = fax;
		this.address = address;
		this.zip = zip;
		this.photo = photo;
		this.assessionTime = assessionTime;
		this.departureTime = departureTime;
		this.status = status;
		this.education = education;
		this.sex = sex;
		this.faxPhone = faxPhone;
		this.profileNo = profileNo;
		this.birthday = birthday;
		this.eduMajor = eduMajor;
		this.eduCollege = eduCollege;
		this.startWorkDate = startWorkDate;
		this.idCard = idCard;
		this.innerCommentTitle = innerCommentTitle;
		this.rePayWageTime = rePayWageTime;
		this.profilePlace = profilePlace;
		this.graduateDate = graduateDate;
		this.hrmTitleDesc = hrmTitleDesc;
		this.healthStatus = healthStatus;
		this.newStaffTrain = newStaffTrain;
		this.approvalTime = approvalTime;
		this.statisticsTime = statisticsTime;
		this.masterCeremony = masterCeremony;
		this.dicEduDegree = dicEduDegree;
		this.dicWorkContractType = dicWorkContractType;
		this.dicMarriage = dicMarriage;
		this.dicParty = dicParty;
		this.dicNationality = dicNationality;
		this.dicRace = dicRace;
		this.dicBloodType = dicBloodType;
		this.dicPlait = dicPlait;
		this.dicProfessionType = dicProfessionType;
		this.dicProfessionLevel = dicProfessionLevel;
		this.dicHrmTitle = dicHrmTitle;
		this.dicHrmTitleLevel = dicHrmTitleLevel;
		this.dicNewTypeCommitment = dicNewTypeCommitment;
		this.dicProManagerLevel = dicProManagerLevel;
		this.dicSocialSecurityPlace = dicSocialSecurityPlace;
		this.rosterJobName = rosterJobName;
		this.workContractEndDate = workContractEndDate;
		this.updateTime = updateTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	public String getDepId() {
		return depId;
	}
	public void setDepId(String depId) {
		this.depId = depId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getAssessionTime() {
		return assessionTime;
	}
	public void setAssessionTime(String assessionTime) {
		this.assessionTime = assessionTime;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getFaxPhone() {
		return faxPhone;
	}
	public void setFaxPhone(String faxPhone) {
		this.faxPhone = faxPhone;
	}
	public String getProfileNo() {
		return profileNo;
	}
	public void setProfileNo(String profileNo) {
		this.profileNo = profileNo;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEduMajor() {
		return eduMajor;
	}
	public void setEduMajor(String eduMajor) {
		this.eduMajor = eduMajor;
	}
	public String getEduCollege() {
		return eduCollege;
	}
	public void setEduCollege(String eduCollege) {
		this.eduCollege = eduCollege;
	}
	public String getStartWorkDate() {
		return startWorkDate;
	}
	public void setStartWorkDate(String startWorkDate) {
		this.startWorkDate = startWorkDate;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getInnerCommentTitle() {
		return innerCommentTitle;
	}
	public void setInnerCommentTitle(String innerCommentTitle) {
		this.innerCommentTitle = innerCommentTitle;
	}
	public String getRePayWageTime() {
		return rePayWageTime;
	}
	public void setRePayWageTime(String rePayWageTime) {
		this.rePayWageTime = rePayWageTime;
	}
	public String getProfilePlace() {
		return profilePlace;
	}
	public void setProfilePlace(String profilePlace) {
		this.profilePlace = profilePlace;
	}
	public String getGraduateDate() {
		return graduateDate;
	}
	public void setGraduateDate(String graduateDate) {
		this.graduateDate = graduateDate;
	}
	public String getHrmTitleDesc() {
		return hrmTitleDesc;
	}
	public void setHrmTitleDesc(String hrmTitleDesc) {
		this.hrmTitleDesc = hrmTitleDesc;
	}
	public String getHealthStatus() {
		return healthStatus;
	}
	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}
	public String getNewStaffTrain() {
		return newStaffTrain;
	}
	public void setNewStaffTrain(String newStaffTrain) {
		this.newStaffTrain = newStaffTrain;
	}
	public String getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(String approvalTime) {
		this.approvalTime = approvalTime;
	}
	public String getStatisticsTime() {
		return statisticsTime;
	}
	public void setStatisticsTime(String statisticsTime) {
		this.statisticsTime = statisticsTime;
	}
	public String getMasterCeremony() {
		return masterCeremony;
	}
	public void setMasterCeremony(String masterCeremony) {
		this.masterCeremony = masterCeremony;
	}
	public String getDicEduDegree() {
		return dicEduDegree;
	}
	public void setDicEduDegree(String dicEduDegree) {
		this.dicEduDegree = dicEduDegree;
	}
	public String getDicWorkContractType() {
		return dicWorkContractType;
	}
	public void setDicWorkContractType(String dicWorkContractType) {
		this.dicWorkContractType = dicWorkContractType;
	}
	public String getDicMarriage() {
		return dicMarriage;
	}
	public void setDicMarriage(String dicMarriage) {
		this.dicMarriage = dicMarriage;
	}
	public String getDicParty() {
		return dicParty;
	}
	public void setDicParty(String dicParty) {
		this.dicParty = dicParty;
	}
	public String getDicNationality() {
		return dicNationality;
	}
	public void setDicNationality(String dicNationality) {
		this.dicNationality = dicNationality;
	}
	public String getDicRace() {
		return dicRace;
	}
	public void setDicRace(String dicRace) {
		this.dicRace = dicRace;
	}
	public String getDicBloodType() {
		return dicBloodType;
	}
	public void setDicBloodType(String dicBloodType) {
		this.dicBloodType = dicBloodType;
	}
	public String getDicPlait() {
		return dicPlait;
	}
	public void setDicPlait(String dicPlait) {
		this.dicPlait = dicPlait;
	}
	public String getDicProfessionType() {
		return dicProfessionType;
	}
	public void setDicProfessionType(String dicProfessionType) {
		this.dicProfessionType = dicProfessionType;
	}
	public String getDicProfessionLevel() {
		return dicProfessionLevel;
	}
	public void setDicProfessionLevel(String dicProfessionLevel) {
		this.dicProfessionLevel = dicProfessionLevel;
	}
	public String getDicHrmTitle() {
		return dicHrmTitle;
	}
	public void setDicHrmTitle(String dicHrmTitle) {
		this.dicHrmTitle = dicHrmTitle;
	}
	public String getDicHrmTitleLevel() {
		return dicHrmTitleLevel;
	}
	public void setDicHrmTitleLevel(String dicHrmTitleLevel) {
		this.dicHrmTitleLevel = dicHrmTitleLevel;
	}
	public String getDicNewTypeCommitment() {
		return dicNewTypeCommitment;
	}
	public void setDicNewTypeCommitment(String dicNewTypeCommitment) {
		this.dicNewTypeCommitment = dicNewTypeCommitment;
	}
	public String getDicProManagerLevel() {
		return dicProManagerLevel;
	}
	public void setDicProManagerLevel(String dicProManagerLevel) {
		this.dicProManagerLevel = dicProManagerLevel;
	}
	public String getDicSocialSecurityPlace() {
		return dicSocialSecurityPlace;
	}
	public void setDicSocialSecurityPlace(String dicSocialSecurityPlace) {
		this.dicSocialSecurityPlace = dicSocialSecurityPlace;
	}
	public String getRosterJobName() {
		return rosterJobName;
	}
	public void setRosterJobName(String rosterJobName) {
		this.rosterJobName = rosterJobName;
	}
	public String getWorkContractEndDate() {
		return workContractEndDate;
	}
	public void setWorkContractEndDate(String workContractEndDate) {
		this.workContractEndDate = workContractEndDate;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}
