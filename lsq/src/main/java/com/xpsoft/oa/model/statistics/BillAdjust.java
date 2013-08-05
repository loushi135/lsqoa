package com.xpsoft.oa.model.statistics;


import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * BillAdjust Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class BillAdjust extends com.xpsoft.core.model.BaseModel {

	@Expose
    protected Long id;  
	protected Bill bill;
	@Expose
	protected java.math.BigDecimal oldAmount;
	@Expose
	protected java.math.BigDecimal newAmoumt;
	@Expose
	protected String newAmountBig;
	@Expose
	protected String adjustReason;
	@Expose
	protected java.util.Date adjustDate;
	@Expose
	protected AppUser adjustUser;


	/**
	 * Default Empty Constructor for class BillAdjust
	 */
	public BillAdjust () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class BillAdjust
	 */
	public BillAdjust (
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
	 * 原发票金额	 * @return java.math.BigDecimal
	 * @hibernate.property column="oldAmount" type="java.math.BigDecimal" length="13" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getOldAmount() {
		return this.oldAmount;
	}
	
	/**
	 * Set the oldAmount
	 */	
	public void setOldAmount(java.math.BigDecimal aValue) {
		this.oldAmount = aValue;
	}	

	/**
	 * 调整金额	 * @return java.math.BigDecimal
	 * @hibernate.property column="newAmoumt" type="java.math.BigDecimal" length="13" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getNewAmoumt() {
		return this.newAmoumt;
	}
	
	/**
	 * Set the newAmoumt
	 */	
	public void setNewAmoumt(java.math.BigDecimal aValue) {
		this.newAmoumt = aValue;
	}	

	/**
	 * 变更金额大写	 * @return String
	 * @hibernate.property column="newAmountBig" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getNewAmountBig() {
		return this.newAmountBig;
	}
	
	/**
	 * Set the newAmountBig
	 */	
	public void setNewAmountBig(String aValue) {
		this.newAmountBig = aValue;
	}	

	/**
	 * 调整原因	 * @return String
	 * @hibernate.property column="adjustReason" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getAdjustReason() {
		return this.adjustReason;
	}
	
	/**
	 * Set the adjustReason
	 */	
	public void setAdjustReason(String aValue) {
		this.adjustReason = aValue;
	}	

	/**
	 * 调整时间	 * @return java.util.Date
	 * @hibernate.property column="adjustDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getAdjustDate() {
		return this.adjustDate;
	}
	
	/**
	 * Set the adjustDate
	 */	
	public void setAdjustDate(java.util.Date aValue) {
		this.adjustDate = aValue;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public AppUser getAdjustUser() {
		return adjustUser;
	}

	public void setAdjustUser(AppUser adjustUser) {
		this.adjustUser = adjustUser;
	}	


}
