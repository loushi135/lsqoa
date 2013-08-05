package com.xpsoft.oa.model.statistics;


import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Department;

/**
 * Prequalificareport Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class Prequalificareport extends com.xpsoft.core.model.BaseModel {

	@Expose
    protected Long id;  
//	@Expose
//	protected ProjectNew project;
	@Expose
	private String proName;
	@Expose
	protected String designUnit;
	@Expose
	protected String buildUnit;
	@Expose
	protected String bidType;
	@Expose
	protected String bidProxy;
	@Expose
	protected java.math.BigDecimal proEstimatPrice;
	@Expose
	protected java.util.Date timeNotice;
	@Expose
	protected java.util.Date timePreQualifica;
	@Expose
	protected java.util.Date timePerBidOpen;
	@Expose
	protected String requireConstrLevel;
	@Expose
	protected String sureArrivalPreQ;
	@Expose
	protected String sureArrivalBid;
	@Expose
	protected String suggestConstructor;
	@Expose
	protected Department materialFitDept;
	@Expose
	protected String reqEnterpriseQua;
	@Expose
	protected String reqPerformance;
	@Expose
	protected String reqOther;
	@Expose
	protected AppUser applyUser;
	@Expose
	protected Long processRunId;


	/**
	 * Default Empty Constructor for class Prequalificareport
	 */
	public Prequalificareport () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class Prequalificareport
	 */
	public Prequalificareport (
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
	 * 设计单位	 * @return String
	 * @hibernate.property column="designUnit" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getDesignUnit() {
		return this.designUnit;
	}
	
	/**
	 * Set the designUnit
	 */	
	public void setDesignUnit(String aValue) {
		this.designUnit = aValue;
	}	

	/**
	 * 建设单位	 * @return String
	 * @hibernate.property column="buildUnit" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getBuildUnit() {
		return this.buildUnit;
	}
	
	/**
	 * Set the buildUnit
	 */	
	public void setBuildUnit(String aValue) {
		this.buildUnit = aValue;
	}	

	/**
	 * 招标类型  (设计 施工)	 * @return String
	 * @hibernate.property column="bidType" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getBidType() {
		return this.bidType;
	}
	
	/**
	 * Set the bidType
	 */	
	public void setBidType(String aValue) {
		this.bidType = aValue;
	}	

	/**
	 * 招标代理	 * @return String
	 * @hibernate.property column="bidProxy" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getBidProxy() {
		return this.bidProxy;
	}
	
	/**
	 * Set the bidProxy
	 */	
	public void setBidProxy(String aValue) {
		this.bidProxy = aValue;
	}	

	/**
	 * 项目估算价（万元）	 * @return java.math.BigDecimal
	 * @hibernate.property column="ProEstimatPrice" type="java.math.BigDecimal" length="25" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getProEstimatPrice() {
		return this.proEstimatPrice;
	}
	
	/**
	 * Set the proEstimatPrice
	 */	
	public void setProEstimatPrice(java.math.BigDecimal aValue) {
		this.proEstimatPrice = aValue;
	}	

	/**
	 * 公告发布时间	 * @return java.util.Date
	 * @hibernate.property column="timeNotice" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getTimeNotice() {
		return this.timeNotice;
	}
	
	/**
	 * Set the timeNotice
	 */	
	public void setTimeNotice(java.util.Date aValue) {
		this.timeNotice = aValue;
	}	

	/**
	 * 资格预审时间	 * @return java.util.Date
	 * @hibernate.property column="timePreQualifica" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getTimePreQualifica() {
		return this.timePreQualifica;
	}
	
	/**
	 * Set the timePreQualifica
	 */	
	public void setTimePreQualifica(java.util.Date aValue) {
		this.timePreQualifica = aValue;
	}	

	/**
	 * 预计开标时间	 * @return java.util.Date
	 * @hibernate.property column="timePerBidOpen" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getTimePerBidOpen() {
		return this.timePerBidOpen;
	}
	
	/**
	 * Set the timePerBidOpen
	 */	
	public void setTimePerBidOpen(java.util.Date aValue) {
		this.timePerBidOpen = aValue;
	}	

	/**
	 * 建造师资质要求 (一级 二级)	 * @return String
	 * @hibernate.property column="requireConstrLevel" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getRequireConstrLevel() {
		return this.requireConstrLevel;
	}
	
	/**
	 * Set the requireConstrLevel
	 */	
	public void setRequireConstrLevel(String aValue) {
		this.requireConstrLevel = aValue;
	}	

	/**
	 * 资格预审是否到场 (否 是)	 * @return String
	 * @hibernate.property column="sureArrivalPreQ" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getSureArrivalPreQ() {
		return this.sureArrivalPreQ;
	}
	
	/**
	 * Set the sureArrivalPreQ
	 */	
	public void setSureArrivalPreQ(String aValue) {
		this.sureArrivalPreQ = aValue;
	}	

	/**
	 * 开标是否到场 (否 是)	 * @return String
	 * @hibernate.property column="sureArrivalBid" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getSureArrivalBid() {
		return this.sureArrivalBid;
	}
	
	/**
	 * Set the sureArrivalBid
	 */	
	public void setSureArrivalBid(String aValue) {
		this.sureArrivalBid = aValue;
	}	

	/**
	 * 市场部推荐建造师	 * @return String
	 * @hibernate.property column="suggestConstructor" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getSuggestConstructor() {
		return this.suggestConstructor;
	}
	
	/**
	 * Set the suggestConstructor
	 */	
	public void setSuggestConstructor(String aValue) {
		this.suggestConstructor = aValue;
	}	

	/**
	 * 施工配合区域	 * @return Long
	 * @hibernate.property column="materialFitDeptId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */

	/**
	 * 企业资质要求	 * @return String
	 * @hibernate.property column="reqEnterpriseQua" type="java.lang.String" length="5000" not-null="false" unique="false"
	 */
	public String getReqEnterpriseQua() {
		return this.reqEnterpriseQua;
	}
	

	public Department getMaterialFitDept() {
		return materialFitDept;
	}

	public void setMaterialFitDept(Department materialFitDept) {
		this.materialFitDept = materialFitDept;
	}

	/**
	 * Set the reqEnterpriseQua
	 */	
	public void setReqEnterpriseQua(String aValue) {
		this.reqEnterpriseQua = aValue;
	}	

	/**
	 * 业绩要求（企业、项目经理）	 * @return String
	 * @hibernate.property column="reqPerformance" type="java.lang.String" length="5000" not-null="false" unique="false"
	 */
	public String getReqPerformance() {
		return this.reqPerformance;
	}
	
	/**
	 * Set the reqPerformance
	 */	
	public void setReqPerformance(String aValue) {
		this.reqPerformance = aValue;
	}	

	/**
	 * 其他要求	 * @return String
	 * @hibernate.property column="reqOther" type="java.lang.String" length="5000" not-null="false" unique="false"
	 */
	public String getReqOther() {
		return this.reqOther;
	}
	
	/**
	 * Set the reqOther
	 */	
	public void setReqOther(String aValue) {
		this.reqOther = aValue;
	}	

	public AppUser getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(AppUser applyUser) {
		this.applyUser = applyUser;
	}

	/**
	 * 流程ID	 * @return Long
	 * @hibernate.property column="processRunId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getProcessRunId() {
		return this.processRunId;
	}
	
	/**
	 * Set the processRunId
	 */	
	public void setProcessRunId(Long aValue) {
		this.processRunId = aValue;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}	
}
