package com.xpsoft.oa.model.hrm;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.xpsoft.core.model.BaseModel;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Department;
import com.xpsoft.oa.model.system.Dictionary;

public class EmpProfile extends BaseModel {
	public static short CHECK_FLAG_NONE = 0;
	public static short CHECK_FLAG_PASS = 1;
	public static short CHECK_FLAG_NOT_PASS = 2;

	public static short DELETE_FLAG_NOT = 0;
	public static short DELETE_FLAG_HAD = 1;
	@Expose
    protected Long profileId;  
	@Expose
	protected String profileNo;
	@Expose
	protected AppUser appUser;
	@Expose
	protected java.util.Date birthday;
	@Expose
	protected String fullname;
	@Expose
	protected String homeZip;
	@Expose
	protected String sex;
	@Expose
	protected Job job;
	@Expose
	protected String position;
	@Expose
	protected String address;
	@Expose
	protected String homeTel;
	@Expose
	protected String otherTel;
	@Expose
	protected String homeAddr;
	@Expose
	protected String eduMajor;
	@Expose
	protected String eduCollege;
	@Expose
	protected java.util.Date startWorkDate;
	@Expose
	protected String idCard;
	@Expose
	protected String photo;
	@Expose
	protected String creator;
	@Expose
	protected java.util.Date createtime;
	@Expose
	protected String checkName;
	@Expose
	protected java.util.Date checktime;
	@Expose
	protected Short approvalStatus;
	@Expose
	protected String opprovalOpinion;
	@Expose
	protected String memo;
	@Expose
	protected String depName;
	@Expose
	protected Department dept;
	@Expose
	protected Short delFlag;
	@Expose
	protected String innerCommentTitle;
	@Expose
	protected java.util.Date rePayWageTime;
	@Expose
	protected String profilePlace;
	@Expose
	protected java.util.Date graduateDate;
	@Expose
	protected String hrmTitleDesc;
	@Expose
	protected String healthStatus;
	@Expose
	protected String newStaffTrain;
	@Expose
	protected String approvalTime;
	@Expose
	protected String statisticsTime;
	@Expose
	protected String masterCeremony;
	@Expose
	protected String rosterJobName;
	@Expose
	protected Date workContractEndDate;
	@Expose
	protected Dictionary dicEduDegree;
	@Expose
	protected Dictionary dicWorkContractType;
	@Expose
	protected Dictionary dicMarriage;
	@Expose
	protected Dictionary dicParty;
	@Expose
	protected Dictionary dicNationality;
	@Expose
	protected Dictionary dicRace;
	@Expose
	protected Dictionary dicBloodType;
	@Expose
	protected Dictionary dicPlait;
	@Expose
	protected Dictionary dicProfessionType;
	@Expose
	protected Dictionary dicProfessionLevel;
	@Expose
	protected Dictionary dicHrmTitle;
	@Expose
	protected Dictionary dicHrmTitleLevel;
	@Expose
	protected Dictionary dicNewTypeCommitment;
	@Expose
	protected Dictionary dicProManagerLevel;
	@Expose
	protected Dictionary dicSocialSecurityPlace;


	/**
	 * Default Empty Constructor for class EmpProfile
	 */
	public EmpProfile () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class EmpProfile
	 */
	public EmpProfile (
		 Long in_profileId
        ) {
		this.setProfileId(in_profileId);
    }

    

	/**
	 * 	 * @return Long
     * @hibernate.id column="profileId" type="java.lang.Long" generator-class="native"
	 */
	public Long getProfileId() {
		return this.profileId;
	}
	
	/**
	 * Set the profileId
	 */	
	public void setProfileId(Long aValue) {
		this.profileId = aValue;
	}	

	/**
	 * 档案编号	 * @return String
	 * @hibernate.property column="profileNo" type="java.lang.String" length="100" not-null="true" unique="false"
	 */
	public String getProfileNo() {
		return this.profileNo;
	}
	
	/**
	 * Set the profileNo
	 * @spring.validator type="required"
	 */	
	public void setProfileNo(String aValue) {
		this.profileNo = aValue;
	}	


	public String getOpprovalOpinion() {
		return opprovalOpinion;
	}

	public void setOpprovalOpinion(String opprovalOpinion) {
		this.opprovalOpinion = opprovalOpinion;
	}

	/**
	 * 出生日期	 * @return java.util.Date
	 * @hibernate.property column="birthday" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getBirthday() {
		return this.birthday;
	}
	
	/**
	 * Set the birthday
	 */	
	public void setBirthday(java.util.Date aValue) {
		this.birthday = aValue;
	}	

	/**
	 * 员工姓名	 * @return String
	 * @hibernate.property column="fullname" type="java.lang.String" length="64" not-null="true" unique="false"
	 */
	public String getFullname() {
		return this.fullname;
	}
	
	/**
	 * Set the fullname
	 * @spring.validator type="required"
	 */	
	public void setFullname(String aValue) {
		this.fullname = aValue;
	}	

	/**
	 * 家庭邮编	 * @return String
	 * @hibernate.property column="homeZip" type="java.lang.String" length="32" not-null="false" unique="false"
	 */
	public String getHomeZip() {
		return this.homeZip;
	}
	
	/**
	 * Set the homeZip
	 */	
	public void setHomeZip(String aValue) {
		this.homeZip = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="sex" type="java.lang.String" length="32" not-null="false" unique="false"
	 */
	public String getSex() {
		return this.sex;
	}
	
	/**
	 * Set the sex
	 */	
	public void setSex(String aValue) {
		this.sex = aValue;
	}	



	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	/**
	 * 	 * @return String
	 * @hibernate.property column="position" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getPosition() {
		return this.position;
	}
	
	/**
	 * Set the position
	 */	
	public void setPosition(String aValue) {
		this.position = aValue;
	}	

	/**
	 * 家庭地址	 * @return String
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
	 * 宅电	 * @return String
	 * @hibernate.property column="homeTel" type="java.lang.String" length="64" not-null="false" unique="false"
	 */
	public String getHomeTel() {
		return this.homeTel;
	}
	
	/**
	 * Set the homeTel
	 */	
	public void setHomeTel(String aValue) {
		this.homeTel = aValue;
	}	

	/**
	 * 其他电话	 * @return String
	 * @hibernate.property column="otherTel" type="java.lang.String" length="64" not-null="false" unique="false"
	 */
	public String getOtherTel() {
		return this.otherTel;
	}
	
	/**
	 * Set the otherTel
	 */	
	public void setOtherTel(String aValue) {
		this.otherTel = aValue;
	}	


	/**
	 * 家乡	 * @return String
	 * @hibernate.property column="homeAddr" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getHomeAddr() {
		return this.homeAddr;
	}
	
	/**
	 * Set the homeAddr
	 */	
	public void setHomeAddr(String aValue) {
		this.homeAddr = aValue;
	}	


	/**
	 * 专业	 * @return String
	 * @hibernate.property column="eduMajor" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getEduMajor() {
		return this.eduMajor;
	}
	
	/**
	 * Set the eduMajor
	 */	
	public void setEduMajor(String aValue) {
		this.eduMajor = aValue;
	}	

	/**
	 * 毕业院校	 * @return String
	 * @hibernate.property column="eduCollege" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getEduCollege() {
		return this.eduCollege;
	}
	
	/**
	 * Set the eduCollege
	 */	
	public void setEduCollege(String aValue) {
		this.eduCollege = aValue;
	}	

	/**
	 * 参加工作时间	 * @return java.util.Date
	 * @hibernate.property column="startWorkDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getStartWorkDate() {
		return this.startWorkDate;
	}
	
	/**
	 * Set the startWorkDate
	 */	
	public void setStartWorkDate(java.util.Date aValue) {
		this.startWorkDate = aValue;
	}	

	/**
	 * 身份证号	 * @return String
	 * @hibernate.property column="idCard" type="java.lang.String" length="64" not-null="false" unique="false"
	 */
	public String getIdCard() {
		return this.idCard;
	}
	
	/**
	 * Set the idCard
	 */	
	public void setIdCard(String aValue) {
		this.idCard = aValue;
	}	

	/**
	 * 照片	 * @return String
	 * @hibernate.property column="photo" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getPhoto() {
		return this.photo;
	}
	
	/**
	 * Set the photo
	 */	
	public void setPhoto(String aValue) {
		this.photo = aValue;
	}	

	/**
	 * 建档人	 * @return String
	 * @hibernate.property column="creator" type="java.lang.String" length="64" not-null="false" unique="false"
	 */
	public String getCreator() {
		return this.creator;
	}
	
	/**
	 * Set the creator
	 */	
	public void setCreator(String aValue) {
		this.creator = aValue;
	}	

	/**
	 * 建档时间	 * @return java.util.Date
	 * @hibernate.property column="createtime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	/**
	 * Set the createtime
	 */	
	public void setCreatetime(java.util.Date aValue) {
		this.createtime = aValue;
	}	

	/**
	 * 审核人	 * @return String
	 * @hibernate.property column="checkName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getCheckName() {
		return this.checkName;
	}
	
	/**
	 * Set the checkName
	 */	
	public void setCheckName(String aValue) {
		this.checkName = aValue;
	}	

	/**
	 * 审核时间	 * @return java.util.Date
	 * @hibernate.property column="checktime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getChecktime() {
		return this.checktime;
	}
	
	/**
	 * Set the checktime
	 */	
	public void setChecktime(java.util.Date aValue) {
		this.checktime = aValue;
	}	

	/**
	 * 核审状态
            0=未审批
            1=通过审核
            2=未通过审核	 * @return Short
	 * @hibernate.property column="approvalStatus" type="java.lang.Short" length="5" not-null="false" unique="false"
	 */
	public Short getApprovalStatus() {
		return this.approvalStatus;
	}
	
	/**
	 * Set the approvalStatus
	 */	
	public void setApprovalStatus(Short aValue) {
		this.approvalStatus = aValue;
	}	

	/**
	 * 备注	 * @return String
	 * @hibernate.property column="memo" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getMemo() {
		return this.memo;
	}
	
	/**
	 * Set the memo
	 */	
	public void setMemo(String aValue) {
		this.memo = aValue;
	}	

	/**
	 * 所属部门或公司	 * @return String
	 * @hibernate.property column="depName" type="java.lang.String" length="100" not-null="false" unique="false"
	 */
	public String getDepName() {
		return this.depName;
	}
	
	/**
	 * Set the depName
	 */	
	public void setDepName(String aValue) {
		this.depName = aValue;
	}	

	/**
	 * 删除状态             0=未删除             1=删除	 * @return Short
	 * @hibernate.property column="delFlag" type="java.lang.Short" length="5" not-null="false" unique="false"
	 */
	public Short getDelFlag() {
		return this.delFlag;
	}
	
	/**
	 * Set the delFlag
	 */	
	public void setDelFlag(Short aValue) {
		this.delFlag = aValue;
	}	


	/**
	 * 内评职称/其它证书	 * @return String
	 * @hibernate.property column="innerCommentTitle" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getInnerCommentTitle() {
		return this.innerCommentTitle;
	}
	
	/**
	 * Set the innerCommentTitle
	 */	
	public void setInnerCommentTitle(String aValue) {
		this.innerCommentTitle = aValue;
	}	


	/**
	 * 补发工资时间	 * @return java.util.Date
	 * @hibernate.property column="rePayWageTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getRePayWageTime() {
		return this.rePayWageTime;
	}
	
	/**
	 * Set the rePayWageTime
	 */	
	public void setRePayWageTime(java.util.Date aValue) {
		this.rePayWageTime = aValue;
	}	

	/**
	 * 档案所在机构	 * @return String
	 * @hibernate.property column="profilePlace" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getProfilePlace() {
		return this.profilePlace;
	}
	
	/**
	 * Set the profilePlace
	 */	
	public void setProfilePlace(String aValue) {
		this.profilePlace = aValue;
	}	

	/**
	 * 毕业时间	 * @return java.util.Date
	 * @hibernate.property column="graduateDate
graduateDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getGraduateDate() {
		return this.graduateDate;
	}
	
	/**
	 * Set the graduateDate
graduateDate
	 */	
	public void setGraduateDate(java.util.Date aValue) {
		this.graduateDate = aValue;
	}	

	/**
	 * 人事职称描述	 * @return String
	 * @hibernate.property column="hrmTitleDesc" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getHrmTitleDesc() {
		return this.hrmTitleDesc;
	}
	
	/**
	 * Set the hrmTitleDesc
	 */	
	public void setHrmTitleDesc(String aValue) {
		this.hrmTitleDesc = aValue;
	}	

	

	/**
	 * 健康状态	 * @return String
	 * @hibernate.property column="healthStatus" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getHealthStatus() {
		return this.healthStatus;
	}
	
	/**
	 * Set the healthStatus
	 */	
	public void setHealthStatus(String aValue) {
		this.healthStatus = aValue;
	}	


	/**
	 * 新员工培训批次	 * @return String
	 * @hibernate.property column="newStaffTrain" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getNewStaffTrain() {
		return this.newStaffTrain;
	}
	
	/**
	 * Set the newStaffTrain
	 */	
	public void setNewStaffTrain(String aValue) {
		this.newStaffTrain = aValue;
	}	

	/**
	 * 审批录入年月	 * @return String
	 * @hibernate.property column="approvalTime" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getApprovalTime() {
		return this.approvalTime;
	}
	
	/**
	 * Set the approvalTime
	 */	
	public void setApprovalTime(String aValue) {
		this.approvalTime = aValue;
	}	

	/**
	 * 统计批次	 * @return java.util.Date
	 * @hibernate.property column="statisticsTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */


	/**
	 * 拜师仪式	 * @return String
	 * @hibernate.property column="masterCeremony" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getMasterCeremony() {
		return this.masterCeremony;
	}
	
	public String getStatisticsTime() {
		return statisticsTime;
	}

	public void setStatisticsTime(String statisticsTime) {
		this.statisticsTime = statisticsTime;
	}

	/**
	 * Set the masterCeremony
	 */	
	public void setMasterCeremony(String aValue) {
		this.masterCeremony = aValue;
	}

	public Dictionary getDicEduDegree() {
		return dicEduDegree;
	}

	public void setDicEduDegree(Dictionary dicEduDegree) {
		this.dicEduDegree = dicEduDegree;
	}

	public String getRosterJobName() {
		return rosterJobName;
	}

	public void setRosterJobName(String rosterJobName) {
		this.rosterJobName = rosterJobName;
	}

	public Date getWorkContractEndDate() {
		return workContractEndDate;
	}

	public void setWorkContractEndDate(Date workContractEndDate) {
		this.workContractEndDate = workContractEndDate;
	}

	public Dictionary getDicWorkContractType() {
		return dicWorkContractType;
	}

	public void setDicWorkContractType(Dictionary dicWorkContractType) {
		this.dicWorkContractType = dicWorkContractType;
	}

	public Dictionary getDicMarriage() {
		return dicMarriage;
	}

	public void setDicMarriage(Dictionary dicMarriage) {
		this.dicMarriage = dicMarriage;
	}

	public Dictionary getDicParty() {
		return dicParty;
	}

	public void setDicParty(Dictionary dicParty) {
		this.dicParty = dicParty;
	}

	public Dictionary getDicNationality() {
		return dicNationality;
	}

	public void setDicNationality(Dictionary dicNationality) {
		this.dicNationality = dicNationality;
	}

	public Dictionary getDicRace() {
		return dicRace;
	}

	public void setDicRace(Dictionary dicRace) {
		this.dicRace = dicRace;
	}

	public Dictionary getDicBloodType() {
		return dicBloodType;
	}

	public void setDicBloodType(Dictionary dicBloodType) {
		this.dicBloodType = dicBloodType;
	}

	public Dictionary getDicPlait() {
		return dicPlait;
	}

	public void setDicPlait(Dictionary dicPlait) {
		this.dicPlait = dicPlait;
	}

	public Dictionary getDicProfessionType() {
		return dicProfessionType;
	}

	public void setDicProfessionType(Dictionary dicProfessionType) {
		this.dicProfessionType = dicProfessionType;
	}

	public Dictionary getDicProfessionLevel() {
		return dicProfessionLevel;
	}

	public void setDicProfessionLevel(Dictionary dicProfessionLevel) {
		this.dicProfessionLevel = dicProfessionLevel;
	}

	public Dictionary getDicHrmTitle() {
		return dicHrmTitle;
	}

	public void setDicHrmTitle(Dictionary dicHrmTitle) {
		this.dicHrmTitle = dicHrmTitle;
	}

	public Dictionary getDicHrmTitleLevel() {
		return dicHrmTitleLevel;
	}

	public void setDicHrmTitleLevel(Dictionary dicHrmTitleLevel) {
		this.dicHrmTitleLevel = dicHrmTitleLevel;
	}

	public Dictionary getDicNewTypeCommitment() {
		return dicNewTypeCommitment;
	}

	public void setDicNewTypeCommitment(Dictionary dicNewTypeCommitment) {
		this.dicNewTypeCommitment = dicNewTypeCommitment;
	}

	public Dictionary getDicProManagerLevel() {
		return dicProManagerLevel;
	}

	public void setDicProManagerLevel(Dictionary dicProManagerLevel) {
		this.dicProManagerLevel = dicProManagerLevel;
	}

	public Dictionary getDicSocialSecurityPlace() {
		return dicSocialSecurityPlace;
	}

	public void setDicSocialSecurityPlace(Dictionary dicSocialSecurityPlace) {
		this.dicSocialSecurityPlace = dicSocialSecurityPlace;
	}	
	
}
