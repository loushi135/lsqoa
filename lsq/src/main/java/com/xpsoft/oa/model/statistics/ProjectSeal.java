package com.xpsoft.oa.model.statistics;


import com.google.gson.annotations.Expose;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.statistics.ProjectNewService;

/**
 * ProjectSeal Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ProjectSeal extends com.xpsoft.core.model.BaseModel {

	@Expose
    protected Long id;  
	@Expose
	protected String content;
	@Expose
	protected AppUser keeper;
	@Expose
	protected java.util.Date applyDate;
	@Expose
	protected java.util.Date returnDate;
	@Expose
	protected AppUser applyUser;
	@Expose
	protected Long processRunId;
	@Expose
	protected Long proId;
	@Expose
	protected String proName;
	@Expose
	protected ProjectNew project;
	/**
	 * Default Empty Constructor for class ProjectSeal
	 */
	public ProjectSeal () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ProjectSeal
	 */
	public ProjectSeal (
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
	 * 项目章刻印内容	 * @return String
	 * @hibernate.property column="content" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * Set the content
	 */	
	public void setContent(String aValue) {
		this.content = aValue;
	}	

	/**
	 * 申领时间	 * @return java.util.Date
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

	/**
	 * 归还时间	 * @return java.util.Date
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


	public AppUser getKeeper() {
		return keeper;
	}

	public void setKeeper(AppUser keeper) {
		this.keeper = keeper;
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

	public Long getProId() {
		return proId;
	}

	public void setProId(Long proId) {
		this.proId = proId;
		
		ProjectNewService service=(ProjectNewService) AppUtil.getBean("projectNewService");
		
		this.project=service.get(proId);
		
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public ProjectNew getProject() {
		return project;
	}

	public void setProject(ProjectNew project) {
		this.project = project;
	}	
}
