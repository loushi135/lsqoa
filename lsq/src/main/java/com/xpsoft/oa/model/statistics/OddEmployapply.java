package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;

/**
 * OddEmployapply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class OddEmployapply extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;  
	@Expose
	protected MaterialContract contract;
	@Expose
	protected String employType;
	@Expose
	protected Integer num;
	@Expose
	protected String contact;
	@Expose
	protected String employTime;
	@Expose
	protected java.math.BigDecimal employFee;
	@Expose
	protected String employReason;
	@Expose
	protected String employContent;
	@Expose
	protected String attachIds;
	@Expose
	protected String attachFiles;
	@Expose
	protected Long processRunId;


	/**
	 * Default Empty Constructor for class OddEmployapply
	 */
	public OddEmployapply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class OddEmployapply
	 */
	public OddEmployapply (
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


	public MaterialContract getContract() {
		return contract;
	}

	public void setContract(MaterialContract contract) {
		this.contract = contract;
	}

	/**
	 * 类别	 * @return String
	 * @hibernate.property column="employType" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getEmployType() {
		return this.employType;
	}
	
	/**
	 * Set the employType
	 */	
	public void setEmployType(String aValue) {
		this.employType = aValue;
	}	

	/**
	 * 人数	 * @return Integer
	 * @hibernate.property column="num" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getNum() {
		return this.num;
	}
	
	/**
	 * Set the num
	 */	
	public void setNum(Integer aValue) {
		this.num = aValue;
	}	

	/**
	 * 姓名/联系方式	 * @return String
	 * @hibernate.property column="contact" type="java.lang.String" length="200" not-null="false" unique="false"
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
	 * 用工时间	 * @return String
	 * @hibernate.property column="employTime" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getEmployTime() {
		return this.employTime;
	}
	
	/**
	 * Set the employTime
	 */	
	public void setEmployTime(String aValue) {
		this.employTime = aValue;
	}	

	/**
	 * 用工费用	 * @return java.math.BigDecimal
	 * @hibernate.property column="employFee" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getEmployFee() {
		return this.employFee;
	}
	
	/**
	 * Set the employFee
	 */	
	public void setEmployFee(java.math.BigDecimal aValue) {
		this.employFee = aValue;
	}	

	/**
	 * 用工原因	 * @return String
	 * @hibernate.property column="employReason" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getEmployReason() {
		return this.employReason;
	}
	
	/**
	 * Set the employReason
	 */	
	public void setEmployReason(String aValue) {
		this.employReason = aValue;
	}	

	/**
	 * 用工内容	 * @return String
	 * @hibernate.property column="employContent" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getEmployContent() {
		return this.employContent;
	}
	
	/**
	 * Set the employContent
	 */	
	public void setEmployContent(String aValue) {
		this.employContent = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="attachIds" type="java.lang.String" length="200" not-null="false" unique="false"
	 */
	public String getAttachIds() {
		return this.attachIds;
	}
	
	/**
	 * Set the attachIds
	 */	
	public void setAttachIds(String aValue) {
		this.attachIds = aValue;
	}	

	/**
	 * 附件	 * @return String
	 * @hibernate.property column="attachFiles" type="java.lang.String" length="5000" not-null="false" unique="false"
	 */
	public String getAttachFiles() {
		return this.attachFiles;
	}
	
	/**
	 * Set the attachFiles
	 */	
	public void setAttachFiles(String aValue) {
		this.attachFiles = aValue;
	}	

	/**
	 * 	 * @return Long
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
		if (!(object instanceof OddEmployapply)) {
			return false;
		}
		OddEmployapply rhs = (OddEmployapply) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.employType, rhs.employType)
				.append(this.num, rhs.num)
				.append(this.contact, rhs.contact)
				.append(this.employTime, rhs.employTime)
				.append(this.employFee, rhs.employFee)
				.append(this.employReason, rhs.employReason)
				.append(this.employContent, rhs.employContent)
				.append(this.attachIds, rhs.attachIds)
				.append(this.attachFiles, rhs.attachFiles)
				.append(this.processRunId, rhs.processRunId)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.employType) 
				.append(this.num) 
				.append(this.contact) 
				.append(this.employTime) 
				.append(this.employFee) 
				.append(this.employReason) 
				.append(this.employContent) 
				.append(this.attachIds) 
				.append(this.attachFiles) 
				.append(this.processRunId) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("employType", this.employType) 
				.append("num", this.num) 
				.append("contact", this.contact) 
				.append("employTime", this.employTime) 
				.append("employFee", this.employFee) 
				.append("employReason", this.employReason) 
				.append("employContent", this.employContent) 
				.append("attachIds", this.attachIds) 
				.append("attachFiles", this.attachFiles) 
				.append("processRunId", this.processRunId) 
				.toString();
	}



}
