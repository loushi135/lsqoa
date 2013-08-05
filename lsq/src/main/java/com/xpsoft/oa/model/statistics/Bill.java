package com.xpsoft.oa.model.statistics;


import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.customer.SuppliersAssess;
import com.xpsoft.oa.model.system.AppUser;

/**
 * Bill Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class Bill extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;  
	@Expose
	protected String amountBig;
	@Expose
	protected java.math.BigDecimal amount;
//	@Expose
//	protected MaterialContract materialContract;
	@Expose
	protected java.util.Date createtime;
	@Expose
	protected AppUser createUser;
	@Expose
	protected java.util.Date billTime;
	@Expose
	protected ProjectNew projectNew;		//项目
	@Expose
	protected SuppliersAssess suppliersAssess;//供应商
	@Expose
	protected String invoiceType;			//发票类型
	@Expose
	protected Long processRunId;			//对应流程的ID
	@Expose
	protected Set<BillAdjust> billAdjusts = new HashSet<BillAdjust>();
	/**
	 * Default Empty Constructor for class Bill
	 */
	public Bill () {
		super();
	}
	
	public ProjectNew getProjectNew() {
		return projectNew;
	}

	public void setProjectNew(ProjectNew projectNew) {
		this.projectNew = projectNew;
	}

	public SuppliersAssess getSuppliersAssess() {
		return suppliersAssess;
	}

	public void setSuppliersAssess(SuppliersAssess suppliersAssess) {
		this.suppliersAssess = suppliersAssess;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	/**
	 * Default Key Fields Constructor for class Bill
	 */
	public Bill (
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
	
	public Set<BillAdjust> getBillAdjusts() {
		return billAdjusts;
	}

	public void setBillAdjusts(Set<BillAdjust> billAdjusts) {
		this.billAdjusts = billAdjusts;
	}

	/**
	 * Set the id
	 */	
	public void setId(Long aValue) {
		this.id = aValue;
	}	

	/**
	 * 发票金额大写	 * @return String
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

	public AppUser getCreateUser() {
		return createUser;
	}

	/**
	 * 发票金额	 * @return java.math.BigDecimal
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

	
	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="createtime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	/**
	 * 材料发包合同	 * @return Long
	 * @hibernate.property column="materialId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */

//	public MaterialContract getMaterialContract() {
//		return materialContract;
//	}
//
//	public void setMaterialContract(MaterialContract materialContract) {
//		this.materialContract = materialContract;
//	}

	public void setCreateUser(AppUser createUser) {
		this.createUser = createUser;
	}

	/**
	 * Set the createtime
	 */	
	public void setCreatetime(java.util.Date aValue) {
		this.createtime = aValue;
	}	


	/**
	 * 发票时间	 * @return java.util.Date
	 * @hibernate.property column="billTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getBillTime() {
		return this.billTime;
	}
	
	/**
	 * Set the billTime
	 */	
	public void setBillTime(java.util.Date aValue) {
		this.billTime = aValue;
	}

	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
	}	
}
