package com.xpsoft.oa.model.statistics;


import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * ProjectSealRecycle Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ProjectSealRecycle extends com.xpsoft.core.model.BaseModel {

	@Expose
    protected Long id;  
	@Expose
	protected ProjectSeal projectSeal;
	@Expose
	protected java.util.Date innerCheckTime;
	@Expose
	protected java.util.Date lastReturnTime;
	@Expose
	protected AppUser applyUser;
	@Expose
	protected Long processRunId;


	/**
	 * Default Empty Constructor for class ProjectSealRecycle
	 */
	public ProjectSealRecycle () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ProjectSealRecycle
	 */
	public ProjectSealRecycle (
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
	 * 项目完工内检时间	 * @return java.util.Date
	 * @hibernate.property column="innerCheckTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getInnerCheckTime() {
		return this.innerCheckTime;
	}
	
	/**
	 * Set the innerCheckTime
	 */	
	public void setInnerCheckTime(java.util.Date aValue) {
		this.innerCheckTime = aValue;
	}	

	/**
	 * 最晚归还时间	 * @return java.util.Date
	 * @hibernate.property column="lastReturnTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getLastReturnTime() {
		return this.lastReturnTime;
	}
	
	/**
	 * Set the lastReturnTime
	 */	
	public void setLastReturnTime(java.util.Date aValue) {
		this.lastReturnTime = aValue;
	}	


	public ProjectSeal getProjectSeal() {
		return projectSeal;
	}

	public void setProjectSeal(ProjectSeal projectSeal) {
		this.projectSeal = projectSeal;
	}

	public AppUser getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(AppUser applyUser) {
		this.applyUser = applyUser;
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
