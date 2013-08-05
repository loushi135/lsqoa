package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;

/**
 * Repayment Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class Repayment extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected PaymentList payment;
	@Expose
	protected java.math.BigDecimal returnAmount;
	@Expose
	protected java.util.Date returnDate;
	@Expose
	protected String operator;
	@Expose
	protected String returnAmountBig;
	@Expose
	protected String returnType;
	
	/**
	 * Default Empty Constructor for class Repayment
	 */
	public Repayment () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class Repayment
	 */
	public Repayment (
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

	

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getReturnAmountBig() {
		return returnAmountBig;
	}

	public void setReturnAmountBig(String returnAmountBig) {
		this.returnAmountBig = returnAmountBig;
	}

	
	public PaymentList getPayment() {
		return payment;
	}

	public void setPayment(PaymentList payment) {
		this.payment = payment;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 还款金额	 * @return java.math.BigDecimal
	 * @hibernate.property column="returnAmount" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getReturnAmount() {
		return this.returnAmount;
	}
	
	/**
	 * Set the returnAmount
	 */	
	public void setReturnAmount(java.math.BigDecimal aValue) {
		this.returnAmount = aValue;
	}	

	/**
	 * 还款日期	 * @return java.util.Date
	 * @hibernate.property column="returnDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getReturnDate() {
		return this.returnDate;
	}
	
	/**
	 * Set the returnDate
	 */	
	public void setReturnDate(java.util.Date aValue) {
		this.returnDate = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Repayment)) {
			return false;
		}
		Repayment rhs = (Repayment) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.returnAmount, rhs.returnAmount)
				.append(this.returnDate, rhs.returnDate)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.returnAmount) 
				.append(this.returnDate) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("returnAmount", this.returnAmount) 
				.append("returnDate", this.returnDate) 
				.toString();
	}



}
