package com.xpsoft.oa.model.statistics;


import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * ProjectEndCheck Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ProjectEndCheck extends com.xpsoft.core.model.BaseModel {

	@Expose
    protected Long id;  
	@Expose
	protected ProjectNew project;
	@Expose
	protected AppUser qualityUser;
	@Expose
	protected AppUser workUser;
	@Expose
	protected AppUser safeUser;
	@Expose
	protected AppUser preCalUser;
	@Expose
	protected AppUser engineeUser;
	@Expose
	protected String conclusion;
	@Expose
	protected java.util.Date actualWorkDate;
	@Expose
	protected java.util.Date actualFinishDate;
	@Expose
	protected AppUser applyUser;
	@Expose
	protected Long processRunId;


	/**
	 * Default Empty Constructor for class ProjectEndCheck
	 */
	public ProjectEndCheck () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ProjectEndCheck
	 */
	public ProjectEndCheck (
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
	 * 项目部自检结果	 * @return String
	 * @hibernate.property column="conclusion" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getConclusion() {
		return this.conclusion;
	}
	
	/**
	 * Set the conclusion
	 */	
	public void setConclusion(String aValue) {
		this.conclusion = aValue;
	}	

	/**
	 * 实际开工时间	 * @return java.util.Date
	 * @hibernate.property column="actualWorkDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getActualWorkDate() {
		return this.actualWorkDate;
	}
	
	/**
	 * Set the actualWorkDate
	 */	
	public void setActualWorkDate(java.util.Date aValue) {
		this.actualWorkDate = aValue;
	}	

	/**
	 * 实际完工时间	 * @return java.util.Date
	 * @hibernate.property column="actualFinishDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getActualFinishDate() {
		return this.actualFinishDate;
	}
	
	/**
	 * Set the actualFinishDate
	 */	
	public void setActualFinishDate(java.util.Date aValue) {
		this.actualFinishDate = aValue;
	}	


	public ProjectNew getProject() {
		return project;
	}

	public void setProject(ProjectNew project) {
		this.project = project;
	}

	public AppUser getQualityUser() {
		return qualityUser;
	}

	public void setQualityUser(AppUser qualityUser) {
		this.qualityUser = qualityUser;
	}

	public AppUser getWorkUser() {
		return workUser;
	}

	public void setWorkUser(AppUser workUser) {
		this.workUser = workUser;
	}

	public AppUser getSafeUser() {
		return safeUser;
	}

	public void setSafeUser(AppUser safeUser) {
		this.safeUser = safeUser;
	}

	public AppUser getPreCalUser() {
		return preCalUser;
	}

	public void setPreCalUser(AppUser preCalUser) {
		this.preCalUser = preCalUser;
	}

	public AppUser getEngineeUser() {
		return engineeUser;
	}

	public void setEngineeUser(AppUser engineeUser) {
		this.engineeUser = engineeUser;
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
