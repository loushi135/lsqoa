package com.xpsoft.oa.model.statistics;


import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.FileAttach;

/**
 * EndInnerCheck Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class EndInnerCheck extends com.xpsoft.core.model.BaseModel {

	@Expose
    protected Long id;  
	@Expose
	protected ProjectNew project;
	@Expose
	protected java.util.Date checkTime;
	@Expose
	protected AppUser checkUser;
	@Expose
	protected String score;
	@Expose
	protected String customerSatisfy;
	@Expose
	protected String conclusion;
	@Expose
	protected String rewardOrPunish;
	@Expose
	protected AppUser applyUser;
	@Expose
	protected Long processRunId;
	@Expose
	protected Set<FileAttach> fileAttachs = new HashSet<FileAttach>();

	/**
	 * Default Empty Constructor for class EndInnerCheck
	 */
	public EndInnerCheck () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class EndInnerCheck
	 */
	public EndInnerCheck (
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
	 * 内检时间	 * @return java.util.Date
	 * @hibernate.property column="checkTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getCheckTime() {
		return this.checkTime;
	}
	
	/**
	 * Set the checkTime
	 */	
	public void setCheckTime(java.util.Date aValue) {
		this.checkTime = aValue;
	}	


	public Set<FileAttach> getFileAttachs() {
		return fileAttachs;
	}

	public void setFileAttachs(Set<FileAttach> fileAttachs) {
		this.fileAttachs = fileAttachs;
	}

	/**
	 * 内检评分	 * @return String
	 * @hibernate.property column="score" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getScore() {
		return this.score;
	}
	
	/**
	 * Set the score
	 */	
	public void setScore(String aValue) {
		this.score = aValue;
	}	

	/**
	 * 客户满意度	 * @return String
	 * @hibernate.property column="customerSatisfy" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getCustomerSatisfy() {
		return this.customerSatisfy;
	}
	
	/**
	 * Set the customerSatisfy
	 */	
	public void setCustomerSatisfy(String aValue) {
		this.customerSatisfy = aValue;
	}	

	/**
	 * 内检结论	 * @return String
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
	 * 奖罚建议	 * @return String
	 * @hibernate.property column="rewardOrPunish" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getRewardOrPunish() {
		return this.rewardOrPunish;
	}
	
	/**
	 * Set the rewardOrPunish
	 */	
	public void setRewardOrPunish(String aValue) {
		this.rewardOrPunish = aValue;
	}	

	public ProjectNew getProject() {
		return project;
	}

	public void setProject(ProjectNew project) {
		this.project = project;
	}

	public AppUser getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(AppUser checkUser) {
		this.checkUser = checkUser;
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
