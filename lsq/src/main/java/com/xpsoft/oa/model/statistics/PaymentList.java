package com.xpsoft.oa.model.statistics;


import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * PaymentList Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class PaymentList extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected ProjectNew project;
	@Expose
	protected String deptName;
	@Expose
	protected String paymentSumBig;
	@Expose
	protected BigDecimal paymentSumSmall;
	@Expose
	protected java.util.Date preNowReturnTime;
	@Expose
	protected String owedSum;
	@Expose
	protected java.util.Date preOwedReturnTime;
	@Expose
	protected String payOption;
	@Expose
	protected String borrower;
	@Expose
	protected String paymentCause;
	@Expose
	protected String paymentType;
	@Expose
	protected AppUser user;
	@Expose
	protected Long processRunId;
	@Expose
	protected String attachIds;
	@Expose
	protected String attachFiles;
	@Expose
	protected Date payMoneyDate;
	/**
	 * Default Empty Constructor for class PaymentList
	 */
	public PaymentList () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class PaymentList
	 */
	public PaymentList (
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


	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Date getPayMoneyDate() {
		return payMoneyDate;
	}

	public void setPayMoneyDate(Date payMoneyDate) {
		this.payMoneyDate = payMoneyDate;
	}

	/**
	 * 部门名称	 * @return String
	 * @hibernate.property column="deptName" type="java.lang.String" length="80" not-null="true" unique="false"
	 */
	public String getDeptName() {
		return this.deptName;
	}
	
	/**
	 * Set the deptName
	 * @spring.validator type="required"
	 */	
	public void setDeptName(String aValue) {
		this.deptName = aValue;
	}	

	public ProjectNew getProject() {
		return project;
	}

	public void setProject(ProjectNew project) {
		this.project = project;
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
	 * 本次借款金额大写	 * @return String
	 * @hibernate.property column="paymentSumBig" type="java.lang.String" length="128" not-null="true" unique="false"
	 */
	public String getPaymentSumBig() {
		return this.paymentSumBig;
	}
	
	/**
	 * Set the paymentSumBig
	 * @spring.validator type="required"
	 */	
	public void setPaymentSumBig(String aValue) {
		this.paymentSumBig = aValue;
	}	

	/**
	 * 本次借款金额小写	 * @return String
	 * @hibernate.property column="paymentSumSmall" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public BigDecimal getPaymentSumSmall() {
		return paymentSumSmall;
	}

	public void setPaymentSumSmall(BigDecimal paymentSumSmall) {
		this.paymentSumSmall = paymentSumSmall;
	}

	/**
	 * 本次借款预计归还时间	 * @return java.util.Date
	 * @hibernate.property column="preNowReturnTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getPreNowReturnTime() {
		return this.preNowReturnTime;
	}
	

	/**
	 * Set the preNowReturnTime
	 */	
	public void setPreNowReturnTime(java.util.Date aValue) {
		this.preNowReturnTime = aValue;
	}	

	/**
	 * 目前欠款金额	 * @return String
	 * @hibernate.property column="owedSum" type="java.lang.String" length="128" not-null="true" unique="false"
	 */
	public String getOwedSum() {
		return this.owedSum;
	}
	
	/**
	 * Set the owedSum
	 * @spring.validator type="required"
	 */	
	public void setOwedSum(String aValue) {
		this.owedSum = aValue;
	}	

	/**
	 * 欠款 预计归还时间	 * @return java.util.Date
	 * @hibernate.property column="preOwedReturnTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getPreOwedReturnTime() {
		return this.preOwedReturnTime;
	}
	
	/**
	 * Set the preOwedReturnTime
	 */	
	public void setPreOwedReturnTime(java.util.Date aValue) {
		this.preOwedReturnTime = aValue;
	}	

	/**
	 * 借款人	 * @return String
	 * @hibernate.property column="borrower" type="java.lang.String" length="128" not-null="true" unique="false"
	 */
	public String getBorrower() {
		return this.borrower;
	}
	
	/**
	 * Set the borrower
	 * @spring.validator type="required"
	 */	
	public void setBorrower(String aValue) {
		this.borrower = aValue;
	}	

	/**
	 * 暂支类别	 * @return String
	 * @hibernate.property column="paymentType" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getPaymentType() {
		return this.paymentType;
	}
	
	/**
	 * Set the paymentType
	 */	
	public void setPaymentType(String aValue) {
		this.paymentType = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof PaymentList)) {
			return false;
		}
		PaymentList rhs = (PaymentList) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.deptName, rhs.deptName)
				.append(this.paymentSumBig, rhs.paymentSumBig)
				.append(this.paymentSumSmall, rhs.paymentSumSmall)
				.append(this.preNowReturnTime, rhs.preNowReturnTime)
				.append(this.owedSum, rhs.owedSum)
				.append(this.preOwedReturnTime, rhs.preOwedReturnTime)
				.append(this.borrower, rhs.borrower)
				.append(this.paymentType, rhs.paymentType)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.deptName) 
				.append(this.paymentSumBig) 
				.append(this.paymentSumSmall) 
				.append(this.preNowReturnTime) 
				.append(this.owedSum) 
				.append(this.preOwedReturnTime) 
				.append(this.borrower) 
				.append(this.paymentType) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("deptName", this.deptName) 
				.append("paymentSumBig", this.paymentSumBig) 
				.append("paymentSumSmall", this.paymentSumSmall) 
				.append("preNowReturnTime", this.preNowReturnTime) 
				.append("owedSum", this.owedSum) 
				.append("preOwedReturnTime", this.preOwedReturnTime) 
				.append("borrower", this.borrower) 
				.append("paymentType", this.paymentType) 
				.toString();
	}

	public String getPayOption() {
		return payOption;
	}

	public void setPayOption(String payOption) {
		this.payOption = payOption;
	}

	public String getPaymentCause() {
		return paymentCause;
	}

	public void setPaymentCause(String paymentCause) {
		this.paymentCause = paymentCause;
	}


}
