package com.xpsoft.oa.model.statistics;


import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Bankpayapplyother Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class Bankpayapplyother extends com.xpsoft.core.model.BaseModel {

    protected Long bankPayApplyId;
	protected String bpaProjectName;
	protected String bpaProjectNo;
	protected String bpaPayType;
	protected String bpaReceiptDept;
	protected String bpaReceiptReason;
	protected java.math.BigDecimal bpaContract;
	protected java.math.BigDecimal bpaSumMoney;
	protected java.math.BigDecimal bpaSumMoneyRatio;
	protected java.math.BigDecimal bpaInvoiceBalance;
	protected java.math.BigDecimal bpaApplyMoneyXX;
	protected String bpaApplyMoneyDX;
	protected String bpaRemark;
	protected Long processRunId;
	protected String attachIds;
	protected String attachFiles;
	protected Date payMoneyDate;
	/**
	 * Default Empty Constructor for class Bankpayapplyother
	 */
	public Bankpayapplyother () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class Bankpayapplyother
	 */
	public Bankpayapplyother (
		 Long in_bankPayApplyId
        ) {
		this.setBankPayApplyId(in_bankPayApplyId);
    }

    

	/**
	 * 	 * @return Long
     * @hibernate.id column="bankPayApplyId" type="java.lang.Long" generator-class="native"
	 */
	public Long getBankPayApplyId() {
		return this.bankPayApplyId;
	}
	
	
	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
	}

	/**
	 * Set the bankPayApplyId
	 */	
	public void setBankPayApplyId(Long aValue) {
		this.bankPayApplyId = aValue;
	}	

	public Date getPayMoneyDate() {
		return payMoneyDate;
	}

	public void setPayMoneyDate(Date payMoneyDate) {
		this.payMoneyDate = payMoneyDate;
	}

	/**
	 * 项目名称	 * @return String
	 * @hibernate.property column="bpaProjectName" type="java.lang.String" length="50" not-null="false" unique="false"
	 */
	public String getBpaProjectName() {
		return this.bpaProjectName;
	}
	
	/**
	 * Set the bpaProjectName
	 */	
	public void setBpaProjectName(String aValue) {
		this.bpaProjectName = aValue;
	}	

	public String getAttachIds() {
		return attachIds;
	}

	public void setAttachIds(String attachIds) {
		this.attachIds = attachIds;
	}

	public String getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String attachFiles) {
		this.attachFiles = attachFiles;
	}

	/**
	 * 项目编号	 * @return String
	 * @hibernate.property column="bpaProjectNo" type="java.lang.String" length="50" not-null="false" unique="false"
	 */
	public String getBpaProjectNo() {
		return this.bpaProjectNo;
	}
	
	/**
	 * Set the bpaProjectNo
	 */	
	public void setBpaProjectNo(String aValue) {
		this.bpaProjectNo = aValue;
	}	

	/**
	 * 付款类别	 * @return String
	 * @hibernate.property column="bpaPayType" type="java.lang.String" length="50" not-null="false" unique="false"
	 */
	public String getBpaPayType() {
		return this.bpaPayType;
	}
	
	/**
	 * Set the bpaPayType
	 */	
	public void setBpaPayType(String aValue) {
		this.bpaPayType = aValue;
	}	

	/**
	 * 收款单位	 * @return String
	 * @hibernate.property column="bpaReceiptDept" type="java.lang.String" length="50" not-null="false" unique="false"
	 */
	public String getBpaReceiptDept() {
		return this.bpaReceiptDept;
	}
	
	/**
	 * Set the bpaReceiptDept
	 */	
	public void setBpaReceiptDept(String aValue) {
		this.bpaReceiptDept = aValue;
	}	

	/**
	 * 收款事由	 * @return String
	 * @hibernate.property column="bpaReceiptReason" type="java.lang.String" length="200" not-null="false" unique="false"
	 */
	public String getBpaReceiptReason() {
		return this.bpaReceiptReason;
	}
	
	/**
	 * Set the bpaReceiptReason
	 */	
	public void setBpaReceiptReason(String aValue) {
		this.bpaReceiptReason = aValue;
	}	

	/**
	 * 合同/结算金额	 * @return java.math.BigDecimal
	 * @hibernate.property column="bpaContract" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getBpaContract() {
		return this.bpaContract;
	}
	
	/**
	 * Set the bpaContract
	 */	
	public void setBpaContract(java.math.BigDecimal aValue) {
		this.bpaContract = aValue;
	}	

	/**
	 * 累计已付款	 * @return java.math.BigDecimal
	 * @hibernate.property column="bpaSumMoney" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getBpaSumMoney() {
		return this.bpaSumMoney;
	}
	
	/**
	 * Set the bpaSumMoney
	 */	
	public void setBpaSumMoney(java.math.BigDecimal aValue) {
		this.bpaSumMoney = aValue;
	}	

	/**
	 * 已付款比例	 * @return java.math.BigDecimal
	 * @hibernate.property column="bpaSumMoneyRatio" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getBpaSumMoneyRatio() {
		return this.bpaSumMoneyRatio;
	}
	
	/**
	 * Set the bpaSumMoneyRatio
	 */	
	public void setBpaSumMoneyRatio(java.math.BigDecimal aValue) {
		this.bpaSumMoneyRatio = aValue;
	}	

	/**
	 * 发票结余	 * @return java.math.BigDecimal
	 * @hibernate.property column="bpaInvoiceBalance" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getBpaInvoiceBalance() {
		return this.bpaInvoiceBalance;
	}
	
	/**
	 * Set the bpaInvoiceBalance
	 */	
	public void setBpaInvoiceBalance(java.math.BigDecimal aValue) {
		this.bpaInvoiceBalance = aValue;
	}	

	/**
	 * 本次申请用款	 * @return java.math.BigDecimal
	 * @hibernate.property column="bpaApplyMoneyXX" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getBpaApplyMoneyXX() {
		return this.bpaApplyMoneyXX;
	}
	
	/**
	 * Set the bpaApplyMoneyXX
	 */	
	public void setBpaApplyMoneyXX(java.math.BigDecimal aValue) {
		this.bpaApplyMoneyXX = aValue;
	}	

	/**
	 * 本次申请用款	 * @return java.math.BigDecimal
	 * @hibernate.property column="bpaApplyMoneyDX" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public String getBpaApplyMoneyDX() {
		return this.bpaApplyMoneyDX;
	}
	
	/**
	 * Set the bpaApplyMoneyDX
	 */	
	public void setBpaApplyMoneyDX(String aValue) {
		this.bpaApplyMoneyDX = aValue;
	}	

	/**
	 * 经办人员（备注）	 * @return String
	 * @hibernate.property column="bpaRemark" type="java.lang.String" length="500" not-null="false" unique="false"
	 */
	public String getBpaRemark() {
		return this.bpaRemark;
	}
	
	/**
	 * Set the bpaRemark
	 */	
	public void setBpaRemark(String aValue) {
		this.bpaRemark = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Bankpayapplyother)) {
			return false;
		}
		Bankpayapplyother rhs = (Bankpayapplyother) object;
		return new EqualsBuilder()
				.append(this.bankPayApplyId, rhs.bankPayApplyId)
				.append(this.bpaProjectName, rhs.bpaProjectName)
				.append(this.bpaProjectNo, rhs.bpaProjectNo)
				.append(this.bpaPayType, rhs.bpaPayType)
				.append(this.bpaReceiptDept, rhs.bpaReceiptDept)
				.append(this.bpaReceiptReason, rhs.bpaReceiptReason)
				.append(this.bpaContract, rhs.bpaContract)
				.append(this.bpaSumMoney, rhs.bpaSumMoney)
				.append(this.bpaSumMoneyRatio, rhs.bpaSumMoneyRatio)
				.append(this.bpaInvoiceBalance, rhs.bpaInvoiceBalance)
				.append(this.bpaApplyMoneyXX, rhs.bpaApplyMoneyXX)
				.append(this.bpaApplyMoneyDX, rhs.bpaApplyMoneyDX)
				.append(this.bpaRemark, rhs.bpaRemark)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.bankPayApplyId) 
				.append(this.bpaProjectName) 
				.append(this.bpaProjectNo) 
				.append(this.bpaPayType) 
				.append(this.bpaReceiptDept) 
				.append(this.bpaReceiptReason) 
				.append(this.bpaContract) 
				.append(this.bpaSumMoney) 
				.append(this.bpaSumMoneyRatio) 
				.append(this.bpaInvoiceBalance) 
				.append(this.bpaApplyMoneyXX) 
				.append(this.bpaApplyMoneyDX) 
				.append(this.bpaRemark) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("bankPayApplyId", this.bankPayApplyId) 
				.append("bpaProjectName", this.bpaProjectName) 
				.append("bpaProjectNo", this.bpaProjectNo) 
				.append("bpaPayType", this.bpaPayType) 
				.append("bpaReceiptDept", this.bpaReceiptDept) 
				.append("bpaReceiptReason", this.bpaReceiptReason) 
				.append("bpaContract", this.bpaContract) 
				.append("bpaSumMoney", this.bpaSumMoney) 
				.append("bpaSumMoneyRatio", this.bpaSumMoneyRatio) 
				.append("bpaInvoiceBalance", this.bpaInvoiceBalance) 
				.append("bpaApplyMoneyXX", this.bpaApplyMoneyXX) 
				.append("bpaApplyMoneyDX", this.bpaApplyMoneyDX) 
				.append("bpaRemark", this.bpaRemark) 
				.toString();
	}



}
