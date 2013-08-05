package com.xpsoft.oa.model.statistics;


import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Department;

/**
 * SalesProject Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class SalesProject extends com.xpsoft.core.model.BaseModel {

	@Expose
    protected Long id;  
	@Expose
	protected String proNo;
	@Expose
	protected String proName;
	@Expose
	protected Department teamDep;
	@Expose
	protected AppUser businessUser;
	@Expose
	protected String remark;
	@Expose
	protected java.util.Date applyDate;
	@Expose
	protected java.math.BigDecimal buildPrice;
	@Expose
	protected java.math.BigDecimal buildArea;
	@Expose
	protected String proInfoSource;
	@Expose
	protected String constructFollowDept;
	@Expose
	protected String proAddr;
	@Expose
	protected String proUseful;
	@Expose
	protected String proMainUnit;
	@Expose
	protected String owerRelation;
	@Expose
	protected String bidDuringTime;
	@Expose
	protected String progressInfo;
	@Expose
	protected String chargeDesign;
	@Expose
	protected String doReview;
	@Expose
	protected String bidQuotaLocalLaws;
	@Expose
	protected String competitionCompany;
	@Expose
	protected String recommendDesignDept;
	@Expose
	protected String proClassify;
	@Expose
	protected Long processRunId;

	/**
	 * Default Empty Constructor for class SalesProject
	 */
	public SalesProject () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class SalesProject
	 */
	public SalesProject (
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
	 * 项目编号	 * @return String
	 * @hibernate.property column="proNo" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getProNo() {
		return this.proNo;
	}
	
	/**
	 * Set the proNo
	 */	
	public void setProNo(String aValue) {
		this.proNo = aValue;
	}	

	/**
	 * 项目名称	 * @return String
	 * @hibernate.property column="proName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getProName() {
		return this.proName;
	}
	
	/**
	 * Set the proName
	 */	
	public void setProName(String aValue) {
		this.proName = aValue;
	}	


	public Department getTeamDep() {
		return teamDep;
	}

	public void setTeamDep(Department teamDep) {
		this.teamDep = teamDep;
	}

	public AppUser getBusinessUser() {
		return businessUser;
	}

	public void setBusinessUser(AppUser businessUser) {
		this.businessUser = businessUser;
	}

	/**
	 * 备注	 * @return String
	 * @hibernate.property column="remark" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getRemark() {
		return this.remark;
	}
	
	/**
	 * Set the remark
	 */	
	public void setRemark(String aValue) {
		this.remark = aValue;
	}

	public java.util.Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(java.util.Date applyDate) {
		this.applyDate = applyDate;
	}

	public java.math.BigDecimal getBuildPrice() {
		return buildPrice;
	}

	public void setBuildPrice(java.math.BigDecimal buildPrice) {
		this.buildPrice = buildPrice;
	}

	public java.math.BigDecimal getBuildArea() {
		return buildArea;
	}

	public void setBuildArea(java.math.BigDecimal buildArea) {
		this.buildArea = buildArea;
	}

	public String getProInfoSource() {
		return proInfoSource;
	}

	public void setProInfoSource(String proInfoSource) {
		this.proInfoSource = proInfoSource;
	}

	public String getConstructFollowDept() {
		return constructFollowDept;
	}

	public void setConstructFollowDept(String constructFollowDept) {
		this.constructFollowDept = constructFollowDept;
	}

	public String getProAddr() {
		return proAddr;
	}

	public void setProAddr(String proAddr) {
		this.proAddr = proAddr;
	}

	public String getProUseful() {
		return proUseful;
	}

	public void setProUseful(String proUseful) {
		this.proUseful = proUseful;
	}

	public String getProMainUnit() {
		return proMainUnit;
	}

	public void setProMainUnit(String proMainUnit) {
		this.proMainUnit = proMainUnit;
	}

	public String getOwerRelation() {
		return owerRelation;
	}

	public void setOwerRelation(String owerRelation) {
		this.owerRelation = owerRelation;
	}

	public String getBidDuringTime() {
		return bidDuringTime;
	}

	public void setBidDuringTime(String bidDuringTime) {
		this.bidDuringTime = bidDuringTime;
	}

	public String getProgressInfo() {
		return progressInfo;
	}

	public void setProgressInfo(String progressInfo) {
		this.progressInfo = progressInfo;
	}

	public String getChargeDesign() {
		return chargeDesign;
	}

	public void setChargeDesign(String chargeDesign) {
		this.chargeDesign = chargeDesign;
	}

	public String getDoReview() {
		return doReview;
	}

	public void setDoReview(String doReview) {
		this.doReview = doReview;
	}

	public String getBidQuotaLocalLaws() {
		return bidQuotaLocalLaws;
	}

	public void setBidQuotaLocalLaws(String bidQuotaLocalLaws) {
		this.bidQuotaLocalLaws = bidQuotaLocalLaws;
	}

	public String getCompetitionCompany() {
		return competitionCompany;
	}

	public void setCompetitionCompany(String competitionCompany) {
		this.competitionCompany = competitionCompany;
	}

	public String getRecommendDesignDept() {
		return recommendDesignDept;
	}

	public void setRecommendDesignDept(String recommendDesignDept) {
		this.recommendDesignDept = recommendDesignDept;
	}

	public String getProClassify() {
		return proClassify;
	}

	public void setProClassify(String proClassify) {
		this.proClassify = proClassify;
	}

	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
	}	
}
