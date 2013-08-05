package com.xpsoft.oa.model.statistics;


import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.core.model.BaseModel;
import com.xpsoft.oa.model.customer.SuppliersAssess;
import com.xpsoft.oa.model.system.AppUser;

/**
 * Paybase Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class Paybase extends BaseModel {
	@Expose
    protected Long paymentBaseId;
	@Expose
	protected BigDecimal paymentBase;
	@Expose
	protected Date createtime;
	@Expose
	protected Date paymentTime;
	@Expose
	protected AppUser createUser;
	@Expose
	protected String paymentBaseBig;
//	@Expose
//	protected MaterialContract materialContract;
	@Expose
	protected ProjectNew projectNew;
	@Expose
	protected SuppliersAssess suppliersAssess;
	/**
	 * Default Empty Constructor for class Paybase
	 */
	public Paybase () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class Paybase
	 */
	public Paybase (
		 Long in_paymentBaseId
        ) {
		this.setPaymentBaseId(in_paymentBaseId);
    }

    

	/**
	 * 	 * @return Long
     * @hibernate.id column="paymentBaseId" type="java.lang.Long" generator-class="native"
	 */
	public Long getPaymentBaseId() {
		return this.paymentBaseId;
	}
	
	/**
	 * Set the paymentBaseId
	 */	
	public void setPaymentBaseId(Long aValue) {
		this.paymentBaseId = aValue;
	}	


	public String getPaymentBaseBig() {
		return paymentBaseBig;
	}

	public void setPaymentBaseBig(String paymentBaseBig) {
		this.paymentBaseBig = paymentBaseBig;
	}

	public BigDecimal getPaymentBase() {
		return paymentBase;
	}

	public void setPaymentBase(BigDecimal paymentBase) {
		this.paymentBase = paymentBase;
	}

//	public MaterialContract getMaterialContract() {
//		return materialContract;
//	}
//
//	public void setMaterialContract(MaterialContract materialContract) {
//		this.materialContract = materialContract;
//	}


	public Date getCreatetime() {
		return createtime;
	}

	public ProjectNew getProjectNew() {
		return projectNew;
	}

	public void setProjectNew(ProjectNew projectNew) {
		this.projectNew = projectNew;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public AppUser getCreateUser() {
		return createUser;
	}

	public void setCreateUser(AppUser createUser) {
		this.createUser = createUser;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Paybase)) {
			return false;
		}
		Paybase rhs = (Paybase) object;
		return new EqualsBuilder()
				.append(this.paymentBaseId, rhs.paymentBaseId)
				.append(this.paymentBase, rhs.paymentBase)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.paymentBaseId) 
				.append(this.paymentBase) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("paymentBaseId", this.paymentBaseId) 
				.append("paymentBase", this.paymentBase) 
				.toString();
	}

	public SuppliersAssess getSuppliersAssess() {
		return suppliersAssess;
	}

	public void setSuppliersAssess(SuppliersAssess suppliersAssess) {
		this.suppliersAssess = suppliersAssess;
	}
}
