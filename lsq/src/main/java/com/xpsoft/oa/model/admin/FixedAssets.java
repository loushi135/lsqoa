package com.xpsoft.oa.model.admin;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * FixedAssets Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class FixedAssets extends com.xpsoft.core.model.BaseModel {

    protected Long assetsId;
	protected String assetsNo;
	protected String assetsName;
	protected String model;
	protected String manufacturer;
//	protected java.util.Date manuDate;
	protected java.util.Date buyDate;
	protected String beDep;
	protected String custodian;
	protected String notes;
	protected String configuration;
	protected String sn;
	protected java.math.BigDecimal assetscost;
	protected String user;
	protected String barcode;
	protected String fileindex;
	protected String inperson;
	protected Short labelStatus;
	protected Short status;
//	protected java.math.BigDecimal remainValRate;
//	protected java.util.Date startDepre;
//	protected java.math.BigDecimal intendTerm;
//	protected java.math.BigDecimal intendWorkGross;
//	protected String workGrossUnit;
//	protected java.math.BigDecimal assetValue;
//	protected java.math.BigDecimal assetCurValue;
//	protected java.math.BigDecimal depreRate;
//	protected java.math.BigDecimal defPerWorkGross;
	

//	protected com.xpsoft.oa.model.admin.DepreType depreType;
	protected com.xpsoft.oa.model.admin.AssetsType assetsType;

//	protected java.util.Set depreRecords = new java.util.HashSet();

	/**
	 * Default Empty Constructor for class FixedAssets
	 */
	public FixedAssets () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class FixedAssets
	 */
	public FixedAssets (
		 Long in_assetsId
        ) {
		this.setAssetsId(in_assetsId);
    }

	
	
//	public com.xpsoft.oa.model.admin.DepreType getDepreType () {
//		return depreType;
//	}	
//	
//	public void setDepreType (com.xpsoft.oa.model.admin.DepreType in_depreType) {
//		this.depreType = in_depreType;
//	}
	
	public com.xpsoft.oa.model.admin.AssetsType getAssetsType () {
		return assetsType;
	}	
	
	public void setAssetsType (com.xpsoft.oa.model.admin.AssetsType in_assetsType) {
		this.assetsType = in_assetsType;
	}

//	public java.util.Set getDepreRecords () {
//		return depreRecords;
//	}	
//	
//	public void setDepreRecords (java.util.Set in_depreRecords) {
//		this.depreRecords = in_depreRecords;
//	}
    

	/**
	 * 	 * @return Long
     * @hibernate.id column="assetsId" type="java.lang.Long" generator-class="native"
	 */
	public Long getAssetsId() {
		return this.assetsId;
	}
	
	/**
	 * Set the assetsId
	 */	
	public void setAssetsId(Long aValue) {
		this.assetsId = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="assetsNo" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getAssetsNo() {
		return this.assetsNo;
	}
	
	/**
	 * Set the assetsNo
	 */	
	public void setAssetsNo(String aValue) {
		this.assetsNo = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="assetsName" type="java.lang.String" length="128" not-null="true" unique="false"
	 */
	public String getAssetsName() {
		return this.assetsName;
	}
	
	/**
	 * Set the assetsName
	 * @spring.validator type="required"
	 */	
	public void setAssetsName(String aValue) {
		this.assetsName = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="model" type="java.lang.String" length="64" not-null="false" unique="false"
	 */
	public String getModel() {
		return this.model;
	}
	
	/**
	 * Set the model
	 */	
	public void setModel(String aValue) {
		this.model = aValue;
	}	

	/**
	 * 	 * @return Long
	 */
	public Long getAssetsTypeId() {
		return this.getAssetsType()==null?null:this.getAssetsType().getAssetsTypeId();
	}
	
	/**
	 * Set the assetsTypeId
	 */	
	public void setAssetsTypeId(Long aValue) {
	    if (aValue==null) {
	    	assetsType = null;
	    } else if (assetsType == null) {
	        assetsType = new com.xpsoft.oa.model.admin.AssetsType(aValue);
	        assetsType.setVersion(new Integer(0));//set a version to cheat hibernate only
	    } else {
			assetsType.setAssetsTypeId(aValue);
	    }
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="manufacturer" type="java.lang.String" length="64" not-null="false" unique="false"
	 */
	public String getManufacturer() {
		return this.manufacturer;
	}
	
	/**
	 * Set the manufacturer
	 */	
	public void setManufacturer(String aValue) {
		this.manufacturer = aValue;
	}	

	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="buyDate" type="java.util.Date" length="19" not-null="true" unique="false"
	 */
	public java.util.Date getBuyDate() {
		return this.buyDate;
	}
	
	/**
	 * Set the buyDate
	 * @spring.validator type="required"
	 */	
	public void setBuyDate(java.util.Date aValue) {
		this.buyDate = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="beDep" type="java.lang.String" length="64" not-null="true" unique="false"
	 */
	public String getBeDep() {
		return this.beDep;
	}
	
	/**
	 * Set the beDep
	 * @spring.validator type="required"
	 */	
	public void setBeDep(String aValue) {
		this.beDep = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="custodian" type="java.lang.String" length="32" not-null="false" unique="false"
	 */
	public String getCustodian() {
		return this.custodian;
	}
	
	/**
	 * Set the custodian
	 */	
	public void setCustodian(String aValue) {
		this.custodian = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="notes" type="java.lang.String" length="500" not-null="false" unique="false"
	 */
	public String getNotes() {
		return this.notes;
	}
	
	/**
	 * Set the notes
	 */	
	public void setNotes(String aValue) {
		this.notes = aValue;
	}	

	public String getConfiguration() {
		return configuration;
	}

	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public java.math.BigDecimal getAssetscost() {
		return assetscost;
	}

	public void setAssetscost(java.math.BigDecimal assetscost) {
		this.assetscost = assetscost;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getFileindex() {
		return fileindex;
	}

	public void setFileindex(String fileindex) {
		this.fileindex = fileindex;
	}

	public String getInperson() {
		return inperson;
	}

	public void setInperson(String inperson) {
		this.inperson = inperson;
	}
	public Short getLabelStatus() {
		return labelStatus;
	}

	public void setLabelStatus(Short labelStatus) {
		this.labelStatus = labelStatus;
	}
	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof FixedAssets)) {
			return false;
		}
		FixedAssets rhs = (FixedAssets) object;
		return new EqualsBuilder()
				.append(this.assetsId, rhs.assetsId)
				.append(this.assetsNo, rhs.assetsNo)
				.append(this.assetsName, rhs.assetsName)
				.append(this.model, rhs.model)
						.append(this.manufacturer, rhs.manufacturer)				
				.append(this.buyDate, rhs.buyDate)
				.append(this.beDep, rhs.beDep)
				.append(this.custodian, rhs.custodian)
				.append(this.notes, rhs.notes)
				.append(this.configuration, rhs.configuration)
				.append(this.sn, rhs.sn)
				.append(this.assetscost, rhs.assetscost)
				.append(this.user, rhs.user)
				.append(this.barcode, rhs.barcode)
				.append(this.fileindex, rhs.fileindex)
				.append(this.inperson, rhs.inperson)
				.append(this.labelStatus, rhs.labelStatus)
				.append(this.status, rhs.status)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.assetsId) 
				.append(this.assetsNo) 
				.append(this.assetsName) 
				.append(this.model) 
				.append(this.manufacturer) 				
				.append(this.buyDate) 
				.append(this.beDep) 
				.append(this.custodian) 
				.append(this.notes) 
				.append(this.configuration) 
				.append(this.sn) 
				.append(this.assetscost) 				
				.append(this.user) 
				.append(this.barcode) 
				.append(this.fileindex) 
				.append(this.inperson)
				.append(this.labelStatus)
				.append(this.status)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("assetsId", this.assetsId) 
				.append("assetsNo", this.assetsNo) 
				.append("assetsName", this.assetsName) 
				.append("model", this.model) 
				.append("manufacturer", this.manufacturer) 				
				.append("buyDate", this.buyDate) 
				.append("beDep", this.beDep) 
				.append("custodian", this.custodian) 
				.append("notes", this.notes) 
				.append("configuration", this.configuration) 
				.append("sn", this.sn) 
				.append("assetscost", this.assetscost) 				
				.append("user", this.user) 
				.append("barcode", this.barcode) 
				.append("fileindex", this.fileindex) 
				.append("inperson", this.inperson) 
				.append("labelStatus", this.labelStatus) 
				.append("status", this.status) 
				.toString();
	}



}
