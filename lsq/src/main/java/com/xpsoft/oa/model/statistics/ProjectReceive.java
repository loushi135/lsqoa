package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * ProjectReceive Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ProjectReceive extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;  
	@Expose
	protected String amountBig;
	@Expose
	protected java.math.BigDecimal amount;
	@Expose
	protected ProjectNew project;
	@Expose
	protected java.util.Date createtime;
	@Expose
	protected AppUser createUser;
	@Expose
	protected java.util.Date receiveTime;
	@Expose
	protected String pzzh;	//凭证字号
	@Expose
	protected String zy;	//摘要

	/**
	 * Default Empty Constructor for class ProjectReceive
	 */
	public ProjectReceive () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ProjectReceive
	 */
	public ProjectReceive (
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
	 * 收款金额大写	 * @return String
	 * @hibernate.property column="amountBig" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getAmountBig() {
		return this.amountBig;
	}
	
	/**
	 * Set the amountBig
	 */	
	public void setAmountBig(String aValue) {
		this.amountBig = aValue;
	}	

	/**
	 * 收款金额	 * @return java.math.BigDecimal
	 * @hibernate.property column="amount" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getAmount() {
		return this.amount;
	}
	
	/**
	 * Set the amount
	 */	
	public void setAmount(java.math.BigDecimal aValue) {
		this.amount = aValue;
	}	


	public AppUser getCreateUser() {
		return createUser;
	}

	public void setCreateUser(AppUser createUser) {
		this.createUser = createUser;
	}

	public ProjectNew getProject() {
		return project;
	}

	public void setProject(ProjectNew project) {
		this.project = project;
	}

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="createtime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	/**
	 * Set the createtime
	 */	
	public void setCreatetime(java.util.Date aValue) {
		this.createtime = aValue;
	}	


	/**
	 * 收款时间	 * @return java.util.Date
	 * @hibernate.property column="receiveTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getReceiveTime() {
		return this.receiveTime;
	}
	
	/**
	 * Set the receiveTime
	 */	
	public void setReceiveTime(java.util.Date aValue) {
		this.receiveTime = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ProjectReceive)) {
			return false;
		}
		ProjectReceive rhs = (ProjectReceive) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.amountBig, rhs.amountBig)
				.append(this.amount, rhs.amount)
				.append(this.project, rhs.project)
				.append(this.createtime, rhs.createtime)
				.append(this.createUser, rhs.createUser)
				.append(this.receiveTime, rhs.receiveTime)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.amountBig) 
				.append(this.amount) 
				.append(this.project) 
				.append(this.createtime) 
				.append(this.createUser) 
				.append(this.receiveTime) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("amountBig", this.amountBig) 
				.append("amount", this.amount) 
				.append("proId", this.project) 
				.append("createtime", this.createtime) 
				.append("createUser", this.createUser) 
				.append("receiveTime", this.receiveTime) 
				.toString();
	}

	public String getPzzh() {
		return pzzh;
	}

	public void setPzzh(String pzzh) {
		this.pzzh = pzzh;
	}

	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

}
