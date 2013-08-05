
package com.xpsoft.oa.model.statistics;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.core.model.BaseModel;
import com.xpsoft.oa.model.customer.SuppliersAssess;
import com.xpsoft.oa.model.system.AppUser;

/**
 * Bankpayapply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class Bankpayapply extends BaseModel {
	@Expose
    protected Long bankPayApplyId;
	@Expose
	protected String bpaProjectName;
	@Expose
	protected String bpaProjectNo;
	@Expose
	protected String bpaPayType;
	@Expose
	protected String bpaReceiptDept;
	@Expose
	protected String bpaReceiptReason;
	@Expose
	protected java.math.BigDecimal bpaContract;
	@Expose
	protected java.math.BigDecimal bpaSumMoney;
	@Expose
	protected java.math.BigDecimal bpaSumMoneyRatio;
	@Expose
	protected java.math.BigDecimal bpaFundBalance;
	@Expose
	protected java.math.BigDecimal bpaPayRatio;
	@Expose
	protected java.math.BigDecimal bpaInvoiceBalance;
	@Expose
	protected java.math.BigDecimal bpaApplyMoneyXX;//项目----- 工程银行付款 实际
	@Expose
	protected String bpaApplyMoneyDX;
	
	@Expose
	protected java.math.BigDecimal bpaApplyFirstMoneyXX;//项目----- 工程银行付款 申请
	@Expose
	protected String bpaApplyFirstMoneyDX;
	
	@Expose
	protected String bpaRemark;
	@Expose
	protected String attachIds;
	@Expose
	protected String attachFiles;
	@Expose
	protected SuppliersAssess suppliersAssess;
	@Expose
	protected AppUser bpaDeptCharger;
	@Expose
	protected String bpaDeptChargerName;
	@Expose
	protected Long processRunId;
	@Expose
	protected Date payMoneyDate;
	@Expose
	protected MaterialContract materialContract;
	@Expose
	protected Set<BankpayapplyUpdate> bankpayapplyUpdates=new HashSet<BankpayapplyUpdate>();
	
	/**
	 * Default Empty Constructor for class Bankpayapply
	 */
	public Bankpayapply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class Bankpayapply
	 */
	public Bankpayapply (
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

	public MaterialContract getMaterialContract() {
		return materialContract;
	}

	public void setMaterialContract(MaterialContract materialContract) {
		this.materialContract = materialContract;
	}

	/**
	 * 项目名称	 * @return String
	 * @hibernate.property column="bpaProjectName" type="java.lang.String" length="50" not-null="false" unique="false"
	 */
	public String getBpaProjectName() {
		return this.bpaProjectName;
	}
	
	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
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
	 * Set the bpaProjectName
	 */	
	public void setBpaProjectName(String aValue) {
		this.bpaProjectName = aValue;
	}	

	public SuppliersAssess getSuppliersAssess() {
		return suppliersAssess;
	}

	public void setSuppliersAssess(SuppliersAssess suppliersAssess) {
		this.suppliersAssess = suppliersAssess;
	}

	public AppUser getBpaDeptCharger() {
		return bpaDeptCharger;
	}

	public void setBpaDeptCharger(AppUser bpaDeptCharger) {
		this.bpaDeptCharger = bpaDeptCharger;
	}

	public String getBpaDeptChargerName() {
		return bpaDeptChargerName;
	}

	public void setBpaDeptChargerName(String bpaDeptChargerName) {
		this.bpaDeptChargerName = bpaDeptChargerName;
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


	public java.math.BigDecimal getBpaSumMoneyRatio() {
		return bpaSumMoneyRatio;
	}

	public void setBpaSumMoneyRatio(java.math.BigDecimal bpaSumMoneyRatio) {
		this.bpaSumMoneyRatio = bpaSumMoneyRatio;
	}

	/**
	 * 资金结余	 * @return java.math.BigDecimal
	 * @hibernate.property column="bpaFundBalance" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getBpaFundBalance() {
		return this.bpaFundBalance;
	}
	
	/**
	 * Set the bpaFundBalance
	 */	
	public void setBpaFundBalance(java.math.BigDecimal aValue) {
		this.bpaFundBalance = aValue;
	}	

	/**
	 * 垫资审批额度	 * @return java.math.BigDecimal
	 * @hibernate.property column="bpaPayRatio" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getBpaPayRatio() {
		return this.bpaPayRatio;
	}
	
	/**
	 * Set the bpaPayRatio
	 */	
	public void setBpaPayRatio(java.math.BigDecimal aValue) {
		this.bpaPayRatio = aValue;
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
	 * @hibernate.property column="bpaApplyMoney" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	
	
	public java.math.BigDecimal getBpaApplyMoneyXX() {
		return bpaApplyMoneyXX;
	}

	public void setBpaApplyMoneyXX(java.math.BigDecimal bpaApplyMoneyXX) {
		this.bpaApplyMoneyXX = bpaApplyMoneyXX;
	}

	public String getBpaApplyMoneyDX() {
		return bpaApplyMoneyDX;
	}

	public void setBpaApplyMoneyDX(String bpaApplyMoneyDX) {
		this.bpaApplyMoneyDX = bpaApplyMoneyDX;
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
		if (!(object instanceof Bankpayapply)) {
			return false;
		}
		Bankpayapply rhs = (Bankpayapply) object;
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
				.append(this.bpaFundBalance, rhs.bpaFundBalance)
				.append(this.bpaPayRatio, rhs.bpaPayRatio)
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
				.append(this.bpaFundBalance) 
				.append(this.bpaPayRatio) 
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
				.append("bpaFundBalance", this.bpaFundBalance) 
				.append("bpaPayRatio", this.bpaPayRatio) 
				.append("bpaInvoiceBalance", this.bpaInvoiceBalance)
				.append("bpaApplyMoneyXX", this.bpaApplyMoneyXX) 
				.append("bpaApplyMoneyDX", this.bpaApplyMoneyDX) 
				.append("bpaRemark", this.bpaRemark) 
				.toString();
	}

	public Set<BankpayapplyUpdate> getBankpayapplyUpdates() {
		return bankpayapplyUpdates;
	}

	public void setBankpayapplyUpdates(Set<BankpayapplyUpdate> bankpayapplyUpdates) {
		this.bankpayapplyUpdates = bankpayapplyUpdates;
	}

	public java.math.BigDecimal getBpaApplyFirstMoneyXX() {
		return bpaApplyFirstMoneyXX;
	}

	public void setBpaApplyFirstMoneyXX(java.math.BigDecimal bpaApplyFirstMoneyXX) {
		this.bpaApplyFirstMoneyXX = bpaApplyFirstMoneyXX;
	}

	public String getBpaApplyFirstMoneyDX() {
		return bpaApplyFirstMoneyDX;
	}

	public void setBpaApplyFirstMoneyDX(String bpaApplyFirstMoneyDX) {
		this.bpaApplyFirstMoneyDX = bpaApplyFirstMoneyDX;
	}
}
