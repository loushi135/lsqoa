package com.xpsoft.oa.model.statistics;


import java.util.Date;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Helmetsoverallsapply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class Helmetsoverallsapply extends com.xpsoft.core.model.BaseModel {

    protected Long id;
	protected Long areaId;
	protected String areaName;
	protected String proName;
	protected Long proID;
	protected String address;
	protected Long designChargerId;
	protected String designChargerName;
	protected String designChargerTel;
	protected String takeUserName;
	protected Long takeUserId;
	protected String takeUserTel;
	protected String remark;
	protected Integer redNum;
	protected Integer yellowNum;
	protected Integer whiteNum;
	protected Integer blueNum;
	protected Integer longSleeveXL;
	protected Integer longSleeveXXL;
	protected Integer longSleeveXXXL;
	protected Integer shortSleeveXL;
	protected Integer shortSleeveXXL;
	protected Integer shortSleeveXXXL;
	protected Long processRunId;
	protected Date timeCreate;

	/**
	 * Default Empty Constructor for class Helmetsoverallsapply
	 */
	public Helmetsoverallsapply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class Helmetsoverallsapply
	 */
	public Helmetsoverallsapply (
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
	 * 施工区域ID	 * @return Long
	 * @hibernate.property column="areaId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getAreaId() {
		return this.areaId;
	}
	
	/**
	 * Set the areaId
	 */	
	public void setAreaId(Long aValue) {
		this.areaId = aValue;
	}	

	/**
	 * 施工区域名称	 * @return String
	 * @hibernate.property column="areaName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getAreaName() {
		return this.areaName;
	}
	
	/**
	 * Set the areaName
	 */	
	public void setAreaName(String aValue) {
		this.areaName = aValue;
	}	

	/**
	 * 工程名称	 * @return String
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

	/**
	 * 工程ID	 * @return Long
	 * @hibernate.property column="proID" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getProID() {
		return this.proID;
	}
	
	/**
	 * Set the proID
	 */	
	public void setProID(Long aValue) {
		this.proID = aValue;
	}	

	/**
	 * 工程详细地址	 * @return String
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
	 * 负责人ID	 * @return Long
	 * @hibernate.property column="designChargerId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getDesignChargerId() {
		return this.designChargerId;
	}
	
	/**
	 * Set the designChargerId
	 */	
	public void setDesignChargerId(Long aValue) {
		this.designChargerId = aValue;
	}	

	/**
	 * 负责人姓名	 * @return String
	 * @hibernate.property column="designChargerName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getDesignChargerName() {
		return this.designChargerName;
	}
	
	/**
	 * Set the designChargerName
	 */	
	public void setDesignChargerName(String aValue) {
		this.designChargerName = aValue;
	}	

	/**
	 * 负责人电话	 * @return String
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
	 * 收货人姓名	 * @return String
	 * @hibernate.property column="takeUserName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getTakeUserName() {
		return this.takeUserName;
	}
	
	/**
	 * Set the takeUserName
	 */	
	public void setTakeUserName(String aValue) {
		this.takeUserName = aValue;
	}	

	/**
	 * 收货人ID	 * @return Long
	 * @hibernate.property column="takeUserId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getTakeUserId() {
		return this.takeUserId;
	}
	
	/**
	 * Set the takeUserId
	 */	
	public void setTakeUserId(Long aValue) {
		this.takeUserId = aValue;
	}	

	/**
	 * 收货人电话号码	 * @return String
	 * @hibernate.property column="takeUserTel" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getTakeUserTel() {
		return this.takeUserTel;
	}
	
	/**
	 * Set the takeUserTel
	 */	
	public void setTakeUserTel(String aValue) {
		this.takeUserTel = aValue;
	}	

	/**
	 * 备注	 * @return String
	 * @hibernate.property column="remark" type="java.lang.String" length="5000" not-null="false" unique="false"
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

	/**
	 * 红色数量	 * @return Integer
	 * @hibernate.property column="redNum" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getRedNum() {
		return this.redNum;
	}
	
	/**
	 * Set the redNum
	 */	
	public void setRedNum(Integer aValue) {
		this.redNum = aValue;
	}	

	/**
	 * 黄色数量	 * @return Integer
	 * @hibernate.property column="yellowNum" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getYellowNum() {
		return this.yellowNum;
	}
	
	/**
	 * Set the yellowNum
	 */	
	public void setYellowNum(Integer aValue) {
		this.yellowNum = aValue;
	}	

	/**
	 * 白色数量	 * @return Integer
	 * @hibernate.property column="whiteNum" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getWhiteNum() {
		return this.whiteNum;
	}
	
	/**
	 * Set the whiteNum
	 */	
	public void setWhiteNum(Integer aValue) {
		this.whiteNum = aValue;
	}	

	/**
	 * 蓝色数量	 * @return Integer
	 * @hibernate.property column="blueNum" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getBlueNum() {
		return this.blueNum;
	}
	
	/**
	 * Set the blueNum
	 */	
	public void setBlueNum(Integer aValue) {
		this.blueNum = aValue;
	}	

	/**
	 * 长袖XL数量	 * @return Integer
	 * @hibernate.property column="longSleeveXL" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getLongSleeveXL() {
		return this.longSleeveXL;
	}
	
	/**
	 * Set the longSleeveXL
	 */	
	public void setLongSleeveXL(Integer aValue) {
		this.longSleeveXL = aValue;
	}	

	/**
	 * 长袖XXL数量	 * @return Integer
	 * @hibernate.property column="longSleeveXXL" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getLongSleeveXXL() {
		return this.longSleeveXXL;
	}
	
	/**
	 * Set the longSleeveXXL
	 */	
	public void setLongSleeveXXL(Integer aValue) {
		this.longSleeveXXL = aValue;
	}	

	/**
	 * 长袖XXXL数量	 * @return Integer
	 * @hibernate.property column="longSleeveXXXL" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getLongSleeveXXXL() {
		return this.longSleeveXXXL;
	}
	
	/**
	 * Set the longSleeveXXXL
	 */	
	public void setLongSleeveXXXL(Integer aValue) {
		this.longSleeveXXXL = aValue;
	}	

	/**
	 * 短袖XL数量	 * @return Integer
	 * @hibernate.property column="shortSleeveXL" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getShortSleeveXL() {
		return this.shortSleeveXL;
	}
	
	/**
	 * Set the shortSleeveXL
	 */	
	public void setShortSleeveXL(Integer aValue) {
		this.shortSleeveXL = aValue;
	}	

	/**
	 * 短袖XXL数量	 * @return Integer
	 * @hibernate.property column="shortSleeveXXL" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getShortSleeveXXL() {
		return this.shortSleeveXXL;
	}
	
	/**
	 * Set the shortSleeveXXL
	 */	
	public void setShortSleeveXXL(Integer aValue) {
		this.shortSleeveXXL = aValue;
	}	

	/**
	 * 短袖XXXL数量	 * @return Integer
	 * @hibernate.property column="shortSleeveXXXL" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getShortSleeveXXXL() {
		return this.shortSleeveXXXL;
	}
	
	/**
	 * Set the shortSleeveXXXL
	 */	
	public void setShortSleeveXXXL(Integer aValue) {
		this.shortSleeveXXXL = aValue;
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

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Helmetsoverallsapply)) {
			return false;
		}
		Helmetsoverallsapply rhs = (Helmetsoverallsapply) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.areaId, rhs.areaId)
				.append(this.areaName, rhs.areaName)
				.append(this.proName, rhs.proName)
				.append(this.proID, rhs.proID)
				.append(this.address, rhs.address)
				.append(this.designChargerId, rhs.designChargerId)
				.append(this.designChargerName, rhs.designChargerName)
				.append(this.designChargerTel, rhs.designChargerTel)
				.append(this.takeUserName, rhs.takeUserName)
				.append(this.takeUserId, rhs.takeUserId)
				.append(this.takeUserTel, rhs.takeUserTel)
				.append(this.remark, rhs.remark)
				.append(this.redNum, rhs.redNum)
				.append(this.yellowNum, rhs.yellowNum)
				.append(this.whiteNum, rhs.whiteNum)
				.append(this.blueNum, rhs.blueNum)
				.append(this.longSleeveXL, rhs.longSleeveXL)
				.append(this.longSleeveXXL, rhs.longSleeveXXL)
				.append(this.longSleeveXXXL, rhs.longSleeveXXXL)
				.append(this.shortSleeveXL, rhs.shortSleeveXL)
				.append(this.shortSleeveXXL, rhs.shortSleeveXXL)
				.append(this.shortSleeveXXXL, rhs.shortSleeveXXXL)
				.append(this.processRunId, rhs.processRunId)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.areaId) 
				.append(this.areaName) 
				.append(this.proName) 
				.append(this.proID) 
				.append(this.address) 
				.append(this.designChargerId) 
				.append(this.designChargerName) 
				.append(this.designChargerTel) 
				.append(this.takeUserName) 
				.append(this.takeUserId) 
				.append(this.takeUserTel) 
				.append(this.remark) 
				.append(this.redNum) 
				.append(this.yellowNum) 
				.append(this.whiteNum) 
				.append(this.blueNum) 
				.append(this.longSleeveXL) 
				.append(this.longSleeveXXL) 
				.append(this.longSleeveXXXL) 
				.append(this.shortSleeveXL) 
				.append(this.shortSleeveXXL) 
				.append(this.shortSleeveXXXL) 
				.append(this.processRunId) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("areaId", this.areaId) 
				.append("areaName", this.areaName) 
				.append("proName", this.proName) 
				.append("proID", this.proID) 
				.append("address", this.address) 
				.append("designChargerId", this.designChargerId) 
				.append("designChargerName", this.designChargerName) 
				.append("designChargerTel", this.designChargerTel) 
				.append("takeUserName", this.takeUserName) 
				.append("takeUserId", this.takeUserId) 
				.append("takeUserTel", this.takeUserTel) 
				.append("remark", this.remark) 
				.append("redNum", this.redNum) 
				.append("yellowNum", this.yellowNum) 
				.append("whiteNum", this.whiteNum) 
				.append("blueNum", this.blueNum) 
				.append("longSleeveXL", this.longSleeveXL) 
				.append("longSleeveXXL", this.longSleeveXXL) 
				.append("longSleeveXXXL", this.longSleeveXXXL) 
				.append("shortSleeveXL", this.shortSleeveXL) 
				.append("shortSleeveXXL", this.shortSleeveXXL) 
				.append("shortSleeveXXXL", this.shortSleeveXXXL) 
				.append("processRunId", this.processRunId) 
				.toString();
	}

	public Date getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(Date timeCreate) {
		this.timeCreate = timeCreate;
	}
}
