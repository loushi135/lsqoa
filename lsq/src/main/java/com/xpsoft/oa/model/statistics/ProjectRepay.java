package com.xpsoft.oa.model.statistics;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * ProjectRepay Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ProjectRepay extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;  
	@Expose
	protected String amountBig;
	@Expose
	protected java.math.BigDecimal amount;
	@Expose
	protected Long proId;
	@Expose
	protected ProjectNew project;
	@Expose
	protected DesignProject designProject;
	@Expose
	protected SalesProject salesProject;
	@Expose
	protected OtherProject otherProject;
	@Expose
	protected java.util.Date createtime;
	@Expose
	protected AppUser createUser;
	@Expose
	protected java.util.Date repayTime;
	@Expose
	protected AppUser repayUser;
	/**
	 * 报销类别： 1 工程类 ，2 设计类   3 营销类  4 其他类
	 */
	@Expose
	protected Integer repayType;

	/**
	 * Default Empty Constructor for class ProjectRepay
	 */
	public ProjectRepay () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ProjectRepay
	 */
	public ProjectRepay (
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

	public Long getProId() {
		return proId;
	}

	public void setProId(Long proId) {
		this.proId = proId;
	}

	public SalesProject getSalesProject() {
		return salesProject;
	}

	public void setSalesProject(SalesProject salesProject) {
		this.salesProject = salesProject;
	}

	public OtherProject getOtherProject() {
		return otherProject;
	}

	public void setOtherProject(OtherProject otherProject) {
		this.otherProject = otherProject;
	}

	/**
	 * 报销金额大写	 * @return String
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


	public DesignProject getDesignProject() {
		return designProject;
	}

	public void setDesignProject(DesignProject designProject) {
		this.designProject = designProject;
	}

	/**
	 * 报销金额	 * @return java.math.BigDecimal
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

	

	public Integer getRepayType() {
		return repayType;
	}

	public void setRepayType(Integer repayType) {
		this.repayType = repayType;
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
	 * 报销时间	 * @return java.util.Date
	 * @hibernate.property column="repayTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getRepayTime() {
		return this.repayTime;
	}
	
	/**
	 * Set the repayTime
	 */	
	public void setRepayTime(java.util.Date aValue) {
		this.repayTime = aValue;
	}	


	public AppUser getCreateUser() {
		return createUser;
	}

	public void setCreateUser(AppUser createUser) {
		this.createUser = createUser;
	}

	public AppUser getRepayUser() {
		return repayUser;
	}

	public void setRepayUser(AppUser repayUser) {
		this.repayUser = repayUser;
	}

	public ProjectNew getProject() {
		return project;
	}

	public void setProject(ProjectNew project) {
		this.project = project;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ProjectRepay)) {
			return false;
		}
		ProjectRepay rhs = (ProjectRepay) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.amountBig, rhs.amountBig)
				.append(this.amount, rhs.amount)
				.append(this.createtime, rhs.createtime)
				.append(this.createUser, rhs.createUser)
				.append(this.repayTime, rhs.repayTime)
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
				.append(this.createtime) 
				.append(this.createUser) 
				.append(this.repayTime) 
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
				.append("createtime", this.createtime) 
				.append("createUser", this.createUser) 
				.append("repayTime", this.repayTime) 
				.toString();
	}



}
