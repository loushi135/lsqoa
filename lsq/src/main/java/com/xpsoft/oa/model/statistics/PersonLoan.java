package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * PersonLoan Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class PersonLoan extends com.xpsoft.core.model.BaseModel {

    protected Long id;
	protected String loanReport;
	protected String loanDepatment;
	protected String personCompanyName;
	protected java.math.BigDecimal loanMoney;
	protected String loanYear;
	protected String loanMonth;
	protected String loanCase;
	protected java.util.Date createDate;
	protected String loanMoneyBig;


	/**
	 * Default Empty Constructor for class PersonLoan
	 */
	public PersonLoan () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class PersonLoan
	 */
	public PersonLoan (
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
	 * 报告人	 * @return String
	 * @hibernate.property column="loanReport" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getLoanReport() {
		return this.loanReport;
	}
	
	/**
	 * Set the loanReport
	 */	
	public void setLoanReport(String aValue) {
		this.loanReport = aValue;
	}	

	/**
	 * 所属部门	 * @return String
	 * @hibernate.property column="loanDepatment" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getLoanDepatment() {
		return this.loanDepatment;
	}
	
	/**
	 * Set the loanDepatment
	 */	
	public void setLoanDepatment(String aValue) {
		this.loanDepatment = aValue;
	}	

	/**
	 * 公司名称	 * @return String
	 * @hibernate.property column="personCompanyName" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getPersonCompanyName() {
		return this.personCompanyName;
	}
	
	/**
	 * Set the personCompanyName
	 */	
	public void setPersonCompanyName(String aValue) {
		this.personCompanyName = aValue;
	}	

	/**
	 * 借款金额	 * @return java.math.BigDecimal
	 * @hibernate.property column="loanMoney" type="java.math.BigDecimal" length="15" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getLoanMoney() {
		return this.loanMoney;
	}
	
	/**
	 * Set the loanMoney
	 */	
	public void setLoanMoney(java.math.BigDecimal aValue) {
		this.loanMoney = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="loanYear" type="java.lang.String" length="50" not-null="false" unique="false"
	 */
	public String getLoanYear() {
		return this.loanYear;
	}
	
	/**
	 * Set the loanYear
	 */	
	public void setLoanYear(String aValue) {
		this.loanYear = aValue;
	}	

	/**
	 * 借款时长	 * @return String
	 * @hibernate.property column="loanMonth" type="java.lang.String" length="20" not-null="false" unique="false"
	 */
	public String getLoanMonth() {
		return this.loanMonth;
	}
	
	/**
	 * Set the loanMonth
	 */	
	public void setLoanMonth(String aValue) {
		this.loanMonth = aValue;
	}	

	/**
	 * 借款原因	 * @return String
	 * @hibernate.property column="loanCase" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getLoanCase() {
		return this.loanCase;
	}
	
	/**
	 * Set the loanCase
	 */	
	public void setLoanCase(String aValue) {
		this.loanCase = aValue;
	}	

	/**
	 * 审批通过日期	 * @return java.util.Date
	 * @hibernate.property column="createDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * Set the createDate
	 */	
	public void setCreateDate(java.util.Date aValue) {
		this.createDate = aValue;
	}	

	/**
	 * 金额大写	 * @return String
	 * @hibernate.property column="loanMoneyBig" type="java.lang.String" length="120" not-null="false" unique="false"
	 */
	public String getLoanMoneyBig() {
		return this.loanMoneyBig;
	}
	
	/**
	 * Set the loanMoneyBig
	 */	
	public void setLoanMoneyBig(String aValue) {
		this.loanMoneyBig = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof PersonLoan)) {
			return false;
		}
		PersonLoan rhs = (PersonLoan) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.loanReport, rhs.loanReport)
				.append(this.loanDepatment, rhs.loanDepatment)
				.append(this.personCompanyName, rhs.personCompanyName)
				.append(this.loanMoney, rhs.loanMoney)
				.append(this.loanYear, rhs.loanYear)
				.append(this.loanMonth, rhs.loanMonth)
				.append(this.loanCase, rhs.loanCase)
				.append(this.createDate, rhs.createDate)
				.append(this.loanMoneyBig, rhs.loanMoneyBig)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.loanReport) 
				.append(this.loanDepatment) 
				.append(this.personCompanyName) 
				.append(this.loanMoney) 
				.append(this.loanYear) 
				.append(this.loanMonth) 
				.append(this.loanCase) 
				.append(this.createDate) 
				.append(this.loanMoneyBig) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("loanReport", this.loanReport) 
				.append("loanDepatment", this.loanDepatment) 
				.append("personCompanyName", this.personCompanyName) 
				.append("loanMoney", this.loanMoney) 
				.append("loanYear", this.loanYear) 
				.append("loanMonth", this.loanMonth) 
				.append("loanCase", this.loanCase) 
				.append("createDate", this.createDate) 
				.append("loanMoneyBig", this.loanMoneyBig) 
				.toString();
	}



}
