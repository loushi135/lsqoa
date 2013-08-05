package com.xpsoft.oa.model.statistics;


import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.Department;

/**
 * LocalProductApply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class LocalProductApply extends com.xpsoft.core.model.BaseModel {

	@Expose
    protected Long id;  
	@Expose
	protected AppUser applyUser;
	@Expose
	protected Department dept;
	@Expose
	protected java.util.Date applyDate;
	@Expose
	protected String applyReason;
	@Expose
	protected Long processRunId;


	/**
	 * Default Empty Constructor for class LocalProductApply
	 */
	public LocalProductApply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class LocalProductApply
	 */
	public LocalProductApply (
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


	public AppUser getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(AppUser applyUser) {
		this.applyUser = applyUser;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	/**
	 * 报告时间	 * @return java.util.Date
	 * @hibernate.property column="applyDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getApplyDate() {
		return this.applyDate;
	}
	
	/**
	 * Set the applyDate
	 */	
	public void setApplyDate(java.util.Date aValue) {
		this.applyDate = aValue;
	}	

	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	/**
	 * 	 * @return Long
	 * @hibernate.property column="processRunId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getProcessRunId() {
		return this.processRunId;
	}
	
	/**
	 * Set the processRunId
	 */	
	public void setProcessRunId(Long aValue) {
		this.processRunId = aValue;
	}	

}
