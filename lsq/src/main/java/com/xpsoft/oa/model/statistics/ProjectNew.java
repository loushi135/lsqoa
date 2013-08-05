package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * ProjectNew Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ProjectNew extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected String proName;
	@Expose
	protected String proNo;
	@Expose
	protected String proAddr;
	@Expose
	protected String area;
	@Expose
	protected AppUser proChargerUser;
	@Expose
	protected String proCharger;
	@Expose
	protected String proChargerTel;
	@Expose
	protected String proFollower;
	@Expose
	protected String proFollowerTel;
	@Expose
	protected String designUnit;
	@Expose
	protected String designCharger;
	@Expose
	protected String designChargerTel;
	@Expose
	protected String buildUnit;
	@Expose
	protected String buildCharger;
	@Expose
	protected String buildChargerTel;
	@Expose
	protected String watchUnit;
	@Expose
	protected String watchCharger;
	@Expose
	protected String watchChargerTel;
	@Expose
	protected String totalUnit;
	@Expose
	protected String totalCharger;
	@Expose
	protected String totalChargerTel;
	@Expose
	protected Constructioncontract contract;
	@Expose
	protected java.util.Date startDate;
	@Expose
	protected java.util.Date endDate;
	@Expose
	protected String businessMain;
	@Expose
	protected java.util.Date enterDate;
	@Expose
	protected String manager;
	@Expose
	protected Integer status;//状态：0可用，1不可用
	@Expose
	protected Long processRunId;

	/**
	 * Default Empty Constructor for class ProjectNew
	 */
	public ProjectNew () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ProjectNew
	 */
	public ProjectNew (
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

	public AppUser getProChargerUser() {
		return proChargerUser;
	}

	public void setProChargerUser(AppUser proChargerUser) {
		this.proChargerUser = proChargerUser;
	}

	public String getProAddr() {
		return proAddr;
	}

	public void setProAddr(String proAddr) {
		this.proAddr = proAddr;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
	 * 所属区域	 * @return String
	 * @hibernate.property column="area" type="java.lang.String" length="256" not-null="false" unique="false"
	 */
	public String getArea() {
		return this.area;
	}
	
	/**
	 * Set the area
	 */	
	public void setArea(String aValue) {
		this.area = aValue;
	}	

	/**
	 * 项目负责人	 * @return String
	 * @hibernate.property column="proCharger" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getProCharger() {
		return this.proCharger;
	}
	
	/**
	 * Set the proCharger
	 */	
	public void setProCharger(String aValue) {
		this.proCharger = aValue;
	}	

	/**
	 * 联系电话	 * @return String
	 * @hibernate.property column="proChargerTel" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getProChargerTel() {
		return this.proChargerTel;
	}
	
	/**
	 * Set the proChargerTel
	 */	
	public void setProChargerTel(String aValue) {
		this.proChargerTel = aValue;
	}	

	/**
	 * 跟踪预算员	 * @return String
	 * @hibernate.property column="proFollower" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getProFollower() {
		return this.proFollower;
	}
	
	/**
	 * Set the proFollower
	 */	
	public void setProFollower(String aValue) {
		this.proFollower = aValue;
	}	

	/**
	 * 预算员电话	 * @return String
	 * @hibernate.property column="proFollowerTel" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getProFollowerTel() {
		return this.proFollowerTel;
	}
	
	/**
	 * Set the proFollowerTel
	 */	
	public void setProFollowerTel(String aValue) {
		this.proFollowerTel = aValue;
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
	 * 设计负责人	 * @return String
	 * @hibernate.property column="designCharger" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getDesignCharger() {
		return this.designCharger;
	}
	
	/**
	 * Set the designCharger
	 */	
	public void setDesignCharger(String aValue) {
		this.designCharger = aValue;
	}	

	/**
	 * 设计负责人电话	 * @return String
	 * @hibernate.property column="designChargerTel" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getDesignChargerTel() {
		return this.designChargerTel;
	}
	
	/**
	 * Set the designChargerTel
	 */	
	public void setDesignChargerTel(String aValue) {
		this.designChargerTel = aValue;
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
	 * 建设负责人	 * @return String
	 * @hibernate.property column="buildCharger" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getBuildCharger() {
		return this.buildCharger;
	}
	
	/**
	 * Set the buildCharger
	 */	
	public void setBuildCharger(String aValue) {
		this.buildCharger = aValue;
	}	

	/**
	 * 建设负责人电话	 * @return String
	 * @hibernate.property column="buildChargerTel" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getBuildChargerTel() {
		return this.buildChargerTel;
	}
	
	/**
	 * Set the buildChargerTel
	 */	
	public void setBuildChargerTel(String aValue) {
		this.buildChargerTel = aValue;
	}	

	/**
	 * 监理单位	 * @return String
	 * @hibernate.property column="watchUnit" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getWatchUnit() {
		return this.watchUnit;
	}
	
	/**
	 * Set the watchUnit
	 */	
	public void setWatchUnit(String aValue) {
		this.watchUnit = aValue;
	}	

	/**
	 * 监理负责人	 * @return String
	 * @hibernate.property column="watchCharger" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getWatchCharger() {
		return this.watchCharger;
	}
	
	/**
	 * Set the watchCharger
	 */	
	public void setWatchCharger(String aValue) {
		this.watchCharger = aValue;
	}	

	/**
	 * 监理负责人电话	 * @return String
	 * @hibernate.property column="watchChargerTel" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getWatchChargerTel() {
		return this.watchChargerTel;
	}
	
	/**
	 * Set the watchChargerTel
	 */	
	public void setWatchChargerTel(String aValue) {
		this.watchChargerTel = aValue;
	}	

	/**
	 * 总包单位	 * @return String
	 * @hibernate.property column="totalUnit" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getTotalUnit() {
		return this.totalUnit;
	}
	
	/**
	 * Set the totalUnit
	 */	
	public void setTotalUnit(String aValue) {
		this.totalUnit = aValue;
	}	

	/**
	 * 总包负责人	 * @return String
	 * @hibernate.property column="totalCharger" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getTotalCharger() {
		return this.totalCharger;
	}
	
	/**
	 * Set the totalCharger
	 */	
	public void setTotalCharger(String aValue) {
		this.totalCharger = aValue;
	}	

	/**
	 * 总包负责人电话	 * @return String
	 * @hibernate.property column="totalChargerTel" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getTotalChargerTel() {
		return this.totalChargerTel;
	}
	
	/**
	 * Set the totalChargerTel
	 */	
	public void setTotalChargerTel(String aValue) {
		this.totalChargerTel = aValue;
	}	


	public Constructioncontract getContract() {
		return contract;
	}

	public void setContract(Constructioncontract contract) {
		this.contract = contract;
	}

	/**
	 * 开工日期	 * @return java.util.Date
	 * @hibernate.property column="startDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getStartDate() {
		return this.startDate;
	}
	
	/**
	 * Set the startDate
	 */	
	public void setStartDate(java.util.Date aValue) {
		this.startDate = aValue;
	}	

	/**
	 * 竣工日期	 * @return java.util.Date
	 * @hibernate.property column="endDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getEndDate() {
		return this.endDate;
	}
	
	/**
	 * Set the endDate
	 */	
	public void setEndDate(java.util.Date aValue) {
		this.endDate = aValue;
	}	

	/**
	 * 业务主办	 * @return String
	 * @hibernate.property column="businessMain" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getBusinessMain() {
		return this.businessMain;
	}
	
	/**
	 * Set the businessMain
	 */	
	public void setBusinessMain(String aValue) {
		this.businessMain = aValue;
	}	

	/**
	 * 进场日期	 * @return java.util.Date
	 * @hibernate.property column="enterDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getEnterDate() {
		return this.enterDate;
	}
	
	/**
	 * Set the enterDate
	 */	
	public void setEnterDate(java.util.Date aValue) {
		this.enterDate = aValue;
	}	

	/**
	 * 项目经理	 * @return String
	 * @hibernate.property column="manager" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getManager() {
		return this.manager;
	}
	
	/**
	 * Set the manager
	 */	
	public void setManager(String aValue) {
		this.manager = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ProjectNew)) {
			return false;
		}
		ProjectNew rhs = (ProjectNew) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.proName, rhs.proName)
				.append(this.proNo, rhs.proNo)
				.append(this.area, rhs.area)
				.append(this.proCharger, rhs.proCharger)
				.append(this.proChargerTel, rhs.proChargerTel)
				.append(this.proFollower, rhs.proFollower)
				.append(this.proFollowerTel, rhs.proFollowerTel)
				.append(this.designUnit, rhs.designUnit)
				.append(this.designCharger, rhs.designCharger)
				.append(this.designChargerTel, rhs.designChargerTel)
				.append(this.buildUnit, rhs.buildUnit)
				.append(this.buildCharger, rhs.buildCharger)
				.append(this.buildChargerTel, rhs.buildChargerTel)
				.append(this.watchUnit, rhs.watchUnit)
				.append(this.watchCharger, rhs.watchCharger)
				.append(this.watchChargerTel, rhs.watchChargerTel)
				.append(this.totalUnit, rhs.totalUnit)
				.append(this.totalCharger, rhs.totalCharger)
				.append(this.totalChargerTel, rhs.totalChargerTel)
				.append(this.startDate, rhs.startDate)
				.append(this.endDate, rhs.endDate)
				.append(this.businessMain, rhs.businessMain)
				.append(this.enterDate, rhs.enterDate)
				.append(this.manager, rhs.manager)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.proName) 
				.append(this.proNo) 
				.append(this.area) 
				.append(this.proCharger) 
				.append(this.proChargerTel) 
				.append(this.proFollower) 
				.append(this.proFollowerTel) 
				.append(this.designUnit) 
				.append(this.designCharger) 
				.append(this.designChargerTel) 
				.append(this.buildUnit) 
				.append(this.buildCharger) 
				.append(this.buildChargerTel) 
				.append(this.watchUnit) 
				.append(this.watchCharger) 
				.append(this.watchChargerTel) 
				.append(this.totalUnit) 
				.append(this.totalCharger) 
				.append(this.totalChargerTel) 
				.append(this.startDate) 
				.append(this.endDate) 
				.append(this.businessMain) 
				.append(this.enterDate) 
				.append(this.manager) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("proName", this.proName) 
				.append("proNo", this.proNo) 
				.append("area", this.area) 
				.append("proCharger", this.proCharger) 
				.append("proChargerTel", this.proChargerTel) 
				.append("proFollower", this.proFollower) 
				.append("proFollowerTel", this.proFollowerTel) 
				.append("designUnit", this.designUnit) 
				.append("designCharger", this.designCharger) 
				.append("designChargerTel", this.designChargerTel) 
				.append("buildUnit", this.buildUnit) 
				.append("buildCharger", this.buildCharger) 
				.append("buildChargerTel", this.buildChargerTel) 
				.append("watchUnit", this.watchUnit) 
				.append("watchCharger", this.watchCharger) 
				.append("watchChargerTel", this.watchChargerTel) 
				.append("totalUnit", this.totalUnit) 
				.append("totalCharger", this.totalCharger) 
				.append("totalChargerTel", this.totalChargerTel) 
				.append("startDate", this.startDate) 
				.append("endDate", this.endDate) 
				.append("businessMain", this.businessMain) 
				.append("enterDate", this.enterDate) 
				.append("manager", this.manager) 
				.toString();
	}



}
