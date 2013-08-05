package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.customer.SuppliersAssess;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Department;

/**
 * MaterialContract Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class MaterialContract extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected String contractNo;
	@Expose
	protected ProjectNew project;
	@Expose
	protected String proName;
	@Expose
	protected String proNo;
	@Expose
	protected String applyer;
	@Expose
	protected String dept;
	@Expose
	protected Department department;
	@Expose
	protected String contractAmount;
	@Expose
	protected String chValue;
	@Expose
	protected String areanum;
	@Expose
	protected String contractName;
	@Expose
	protected String mainItem;
	@Expose
	protected String sysItem;
	@Expose
	protected String xydw;
	@Expose
	protected String tel;
	@Expose
	protected String yfk;
	@Expose
	protected String jdk;
	@Expose
	protected String jdkReceive;
	@Expose
	protected String wgk;
	@Expose
	protected String jsk;
	@Expose
	protected String zbj;
	@Expose
	protected String usefull;
	@Expose
	protected String contractAttachIDs;
	@Expose
	protected String contractFile;
	@Expose
	protected String remark;
	@Expose
	protected SuppliersAssess suppliersAssess;
	@Expose
	protected AppUser materialDeptCharger;
	@Expose
	protected String materialDeptChargerName;
	@Expose
	protected Long processRunId;
	/**
	 * Default Empty Constructor for class MaterialContract
	 */
	public MaterialContract () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class MaterialContract
	 */
	public MaterialContract (
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

	public SuppliersAssess getSuppliersAssess() {
		return suppliersAssess;
	}

	public void setSuppliersAssess(SuppliersAssess suppliersAssess) {
		this.suppliersAssess = suppliersAssess;
	}

	public ProjectNew getProject() {
		return project;
	}

	public void setProject(ProjectNew project) {
		this.project = project;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * 	 * @return String
	 * @hibernate.property column="contractNo" type="java.lang.String" length="100" not-null="true" unique="false"
	 */
	public String getContractNo() {
		return this.contractNo;
	}
	
	public String getJdkReceive() {
		return jdkReceive;
	}

	public void setJdkReceive(String jdkReceive) {
		this.jdkReceive = jdkReceive;
	}

	/**
	 * Set the contractNo
	 * @spring.validator type="required"
	 */	
	public void setContractNo(String aValue) {
		this.contractNo = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="proName" type="java.lang.String" length="50" not-null="true" unique="false"
	 */
	public String getProName() {
		return this.proName;
	}
	
	
	/**
	 * Set the proName
	 * @spring.validator type="required"
	 */	
	public void setProName(String aValue) {
		this.proName = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="proNo" type="java.lang.String" length="50" not-null="true" unique="false"
	 */
	public String getProNo() {
		return this.proNo;
	}
	
	/**
	 * Set the proNo
	 * @spring.validator type="required"
	 */	
	public void setProNo(String aValue) {
		this.proNo = aValue;
	}	

	public AppUser getMaterialDeptCharger() {
		return materialDeptCharger;
	}

	public void setMaterialDeptCharger(AppUser materialDeptCharger) {
		this.materialDeptCharger = materialDeptCharger;
	}

	public String getMaterialDeptChargerName() {
		return materialDeptChargerName;
	}

	public void setMaterialDeptChargerName(String materialDeptChargerName) {
		this.materialDeptChargerName = materialDeptChargerName;
	}

	/**
	 * 	 * @return String
	 * @hibernate.property column="applyer" type="java.lang.String" length="50" not-null="true" unique="false"
	 */
	public String getApplyer() {
		return this.applyer;
	}
	
	/**
	 * Set the applyer
	 * @spring.validator type="required"
	 */	
	public void setApplyer(String aValue) {
		this.applyer = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="dept" type="java.lang.String" length="50" not-null="true" unique="false"
	 */
	public String getDept() {
		return this.dept;
	}
	
	/**
	 * Set the dept
	 * @spring.validator type="required"
	 */	
	public void setDept(String aValue) {
		this.dept = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="contractAmount" type="java.lang.String" length="50" not-null="true" unique="false"
	 */
	public String getContractAmount() {
		return this.contractAmount;
	}
	
	/**
	 * Set the contractAmount
	 * @spring.validator type="required"
	 */	
	public void setContractAmount(String aValue) {
		this.contractAmount = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="chValue" type="java.lang.String" length="50" not-null="true" unique="false"
	 */
	public String getChValue() {
		return this.chValue;
	}
	
	/**
	 * Set the chValue
	 * @spring.validator type="required"
	 */	
	public void setChValue(String aValue) {
		this.chValue = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="areanum" type="java.lang.String" length="10" not-null="true" unique="false"
	 */
	public String getAreanum() {
		return this.areanum;
	}
	
	/**
	 * Set the areanum
	 * @spring.validator type="required"
	 */	
	public void setAreanum(String aValue) {
		this.areanum = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="contractName" type="java.lang.String" length="200" not-null="true" unique="false"
	 */
	public String getContractName() {
		return this.contractName;
	}
	
	/**
	 * Set the contractName
	 * @spring.validator type="required"
	 */	
	public void setContractName(String aValue) {
		this.contractName = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="mainItem" type="java.lang.String" length="100" not-null="true" unique="false"
	 */
	public String getMainItem() {
		return this.mainItem;
	}
	
	/**
	 * Set the mainItem
	 * @spring.validator type="required"
	 */	
	public void setMainItem(String aValue) {
		this.mainItem = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="xydw" type="java.lang.String" length="100" not-null="true" unique="false"
	 */
	public String getXydw() {
		return this.xydw;
	}
	
	/**
	 * Set the xydw
	 * @spring.validator type="required"
	 */	
	public void setXydw(String aValue) {
		this.xydw = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="tel" type="java.lang.String" length="15" not-null="false" unique="false"
	 */
	public String getTel() {
		return this.tel;
	}
	
	/**
	 * Set the tel
	 */	
	public void setTel(String aValue) {
		this.tel = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="yfk" type="java.lang.String" length="10" not-null="false" unique="false"
	 */
	public String getYfk() {
		return this.yfk;
	}
	
	/**
	 * Set the yfk
	 */	
	public void setYfk(String aValue) {
		this.yfk = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="jdk" type="java.lang.String" length="10" not-null="false" unique="false"
	 */
	public String getJdk() {
		return this.jdk;
	}
	
	/**
	 * Set the jdk
	 */	
	public void setJdk(String aValue) {
		this.jdk = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="wgk" type="java.lang.String" length="10" not-null="false" unique="false"
	 */
	public String getWgk() {
		return this.wgk;
	}
	
	/**
	 * Set the wgk
	 */	
	public void setWgk(String aValue) {
		this.wgk = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="jsk" type="java.lang.String" length="10" not-null="false" unique="false"
	 */
	public String getJsk() {
		return this.jsk;
	}
	
	/**
	 * Set the jsk
	 */	
	public void setJsk(String aValue) {
		this.jsk = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="zbj" type="java.lang.String" length="10" not-null="false" unique="false"
	 */
	public String getZbj() {
		return this.zbj;
	}
	
	/**
	 * Set the zbj
	 */	
	public void setZbj(String aValue) {
		this.zbj = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="usefull" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getUsefull() {
		return this.usefull;
	}
	
	/**
	 * Set the usefull
	 */	
	public void setUsefull(String aValue) {
		this.usefull = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="contractAttachIDs" type="java.lang.String" length="10" not-null="false" unique="false"
	 */
	public String getContractAttachIDs() {
		return this.contractAttachIDs;
	}
	
	/**
	 * Set the contractAttachIDs
	 */	
	public void setContractAttachIDs(String aValue) {
		this.contractAttachIDs = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="contractFile" type="java.lang.String" length="5000" not-null="false" unique="false"
	 */
	public String getContractFile() {
		return this.contractFile;
	}
	
	/**
	 * Set the contractFile
	 */	
	public void setContractFile(String aValue) {
		this.contractFile = aValue;
	}	

	/**
	 * 	 * @return String
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
	

	public String getSysItem() {
		return sysItem;
	}

	public void setSysItem(String sysItem) {
		this.sysItem = sysItem;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof MaterialContract)) {
			return false;
		}
		MaterialContract rhs = (MaterialContract) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.contractNo, rhs.contractNo)
				.append(this.proName, rhs.proName)
				.append(this.proNo, rhs.proNo)
				.append(this.applyer, rhs.applyer)
				.append(this.dept, rhs.dept)
				.append(this.contractAmount, rhs.contractAmount)
				.append(this.chValue, rhs.chValue)
				.append(this.areanum, rhs.areanum)
				.append(this.contractName, rhs.contractName)
				.append(this.mainItem, rhs.mainItem)
				.append(this.sysItem, rhs.sysItem)
				.append(this.xydw, rhs.xydw)
				.append(this.tel, rhs.tel)
				.append(this.yfk, rhs.yfk)
				.append(this.jdk, rhs.jdk)
				.append(this.wgk, rhs.wgk)
				.append(this.jsk, rhs.jsk)
				.append(this.zbj, rhs.zbj)
				.append(this.usefull, rhs.usefull)
				.append(this.contractAttachIDs, rhs.contractAttachIDs)
				.append(this.contractFile, rhs.contractFile)
				.append(this.remark, rhs.remark)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.contractNo) 
				.append(this.proName) 
				.append(this.proNo) 
				.append(this.applyer) 
				.append(this.dept) 
				.append(this.contractAmount) 
				.append(this.chValue) 
				.append(this.areanum) 
				.append(this.contractName) 
				.append(this.mainItem) 
				.append(this.sysItem) 
				.append(this.xydw) 
				.append(this.tel) 
				.append(this.yfk) 
				.append(this.jdk) 
				.append(this.wgk) 
				.append(this.jsk) 
				.append(this.zbj) 
				.append(this.usefull) 
				.append(this.contractAttachIDs) 
				.append(this.contractFile) 
				.append(this.remark) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("contractNo", this.contractNo) 
				.append("proName", this.proName) 
				.append("proNo", this.proNo) 
				.append("applyer", this.applyer) 
				.append("dept", this.dept) 
				.append("contractAmount", this.contractAmount) 
				.append("chValue", this.chValue) 
				.append("areanum", this.areanum) 
				.append("contractName", this.contractName) 
				.append("mainItem", this.mainItem) 
				.append("xydw", this.xydw) 
				.append("tel", this.tel) 
				.append("yfk", this.yfk) 
				.append("jdk", this.jdk) 
				.append("wgk", this.wgk) 
				.append("jsk", this.jsk) 
				.append("zbj", this.zbj) 
				.append("usefull", this.usefull) 
				.append("contractAttachIDs", this.contractAttachIDs) 
				.append("contractFile", this.contractFile) 
				.append("remark", this.remark) 
				.toString();
	}



}
