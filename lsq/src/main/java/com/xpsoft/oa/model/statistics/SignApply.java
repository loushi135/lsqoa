package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.customer.SuppliersAssess;

/**
 * SignApply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class SignApply extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;  
	@Expose
	protected MaterialContract contract;
	@Expose
	protected String signNo;
	@Expose
	protected SuppliersAssess feeSupplier;
	@Expose
	protected String signReason;
	@Expose
	protected String signContent;
	@Expose
	protected String remark;
	@Expose
	protected String attachIds;
	@Expose
	protected String attachFiles;
	@Expose
	protected Long processRunId;


	/**
	 * Default Empty Constructor for class SignApply
	 */
	public SignApply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class SignApply
	 */
	public SignApply (
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
	 * 签证编号	 * @return String
	 * @hibernate.property column="signNo" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getSignNo() {
		return this.signNo;
	}
	
	/**
	 * Set the signNo
	 */	
	public void setSignNo(String aValue) {
		this.signNo = aValue;
	}	

	/**
	 * 承担费用单位	 * @return Long
	 * @hibernate.property column="supplierId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public SuppliersAssess getFeeSupplier() {
		return feeSupplier;
	}

	public void setFeeSupplier(SuppliersAssess feeSupplier) {
		this.feeSupplier = feeSupplier;
	}
	/**
	 * 签证原因	 * @return String
	 * @hibernate.property column="signReason" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getSignReason() {
		return this.signReason;
	}

	/**
	 * Set the signReason
	 */	
	public void setSignReason(String aValue) {
		this.signReason = aValue;
	}	

	/**
	 * 签证内容	 * @return String
	 * @hibernate.property column="signContent" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getSignContent() {
		return this.signContent;
	}
	
	/**
	 * Set the signContent
	 */	
	public void setSignContent(String aValue) {
		this.signContent = aValue;
	}	

	/**
	 * 备注说明	 * @return String
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
		if (!(object instanceof SignApply)) {
			return false;
		}
		SignApply rhs = (SignApply) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.signNo, rhs.signNo)
				.append(this.signReason, rhs.signReason)
				.append(this.signContent, rhs.signContent)
				.append(this.remark, rhs.remark)
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
				.append(this.signNo) 
				.append(this.signReason) 
				.append(this.signContent) 
				.append(this.remark) 
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
				.append("signNo", this.signNo) 
				.append("signReason", this.signReason) 
				.append("signContent", this.signContent) 
				.append("remark", this.remark) 
				.append("attachIds", this.attachIds) 
				.append("attachFiles", this.attachFiles) 
				.append("processRunId", this.processRunId) 
				.toString();
	}



}
