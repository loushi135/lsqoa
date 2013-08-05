package com.xpsoft.oa.model.statistics;


import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * BankpayapplyUpdate Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class BankpayapplyUpdate extends com.xpsoft.core.model.BaseModel {

	@Expose
    protected Long id;  
	@Expose
	protected java.math.BigDecimal oldApplyMoneyXX;
	@Expose
	protected java.math.BigDecimal nowApplyMoneyXX;
	@Expose
	protected AppUser userCreate;
	@Expose
	protected java.util.Date timeCreate;
	protected Bankpayapply bankpayapply;
	@Expose
	protected String updateReason;

	/**
	 * Default Empty Constructor for class BankpayapplyUpdate
	 */
	public BankpayapplyUpdate () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class BankpayapplyUpdate
	 */
	public BankpayapplyUpdate (
		 Long in_id
        ) {
		this.setId(in_id);
    }

    

	/**
	 * ID	 * @return Long
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
	 * 更改前金额	 * @return java.math.BigDecimal
	 * @hibernate.property column="oldApplyMoneyXX" type="java.math.BigDecimal" length="15" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getOldApplyMoneyXX() {
		return this.oldApplyMoneyXX;
	}
	
	/**
	 * Set the oldApplyMoneyXX
	 */	
	public void setOldApplyMoneyXX(java.math.BigDecimal aValue) {
		this.oldApplyMoneyXX = aValue;
	}	

	/**
	 * 更改后金额	 * @return java.math.BigDecimal
	 * @hibernate.property column="nowApplyMoneyXX" type="java.math.BigDecimal" length="15" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getNowApplyMoneyXX() {
		return this.nowApplyMoneyXX;
	}
	
	/**
	 * Set the nowApplyMoneyXX
	 */	
	public void setNowApplyMoneyXX(java.math.BigDecimal aValue) {
		this.nowApplyMoneyXX = aValue;
	}	

	/**
	 * 更改人ID	 * @return Long
	 * @hibernate.property column="userIdcreate" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */

	/**
	 * 更新时间	 * @return java.util.Date
	 * @hibernate.property column="timeCreate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getTimeCreate() {
		return this.timeCreate;
	}
	
	public AppUser getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(AppUser userCreate) {
		this.userCreate = userCreate;
	}

	/**
	 * Set the timeCreate
	 */	
	public void setTimeCreate(java.util.Date aValue) {
		this.timeCreate = aValue;
	}

	public Bankpayapply getBankpayapply() {
		return bankpayapply;
	}

	public void setBankpayapply(Bankpayapply bankpayapply) {
		this.bankpayapply = bankpayapply;
	}

	public String getUpdateReason() {
		return updateReason;
	}

	public void setUpdateReason(String updateReason) {
		this.updateReason = updateReason;
	}	

	/**
	 * 所属银行付款ID	 * @return Long
	 * @hibernate.property column="applyID" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */

}
