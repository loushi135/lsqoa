package com.xpsoft.oa.model.statistics;


import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.FileAttach;

/**
 * FlowApply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class FlowApply extends com.xpsoft.core.model.BaseModel {
	
	@Expose
    protected Long id;
	@Expose
	protected String flowName;
	@Expose
	protected String background;
	@Expose
	protected String flowDesc;
	@Expose
	protected AppUser applyUser;
	@Expose
	protected AppUser relatedUser;
	@Expose
	protected String flowNode;
	@Expose
	protected String remark;
	@Expose
	protected Long processRunId;
	@Expose
	protected Set<FileAttach> fileAttachs = new HashSet<FileAttach>();

	/**
	 * Default Empty Constructor for class FlowApply
	 */
	public FlowApply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class FlowApply
	 */
	public FlowApply (
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
	 * 流程名称	 * @return String
	 * @hibernate.property column="flowName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getFlowName() {
		return this.flowName;
	}
	
	/**
	 * Set the flowName
	 */	
	public void setFlowName(String aValue) {
		this.flowName = aValue;
	}	

	/**
	 * 需求背景	 * @return String
	 * @hibernate.property column="background" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getBackground() {
		return this.background;
	}
	
	/**
	 * Set the background
	 */	
	public void setBackground(String aValue) {
		this.background = aValue;
	}	

	/**
	 * 流程描述	 * @return String
	 * @hibernate.property column="flowDesc" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getFlowDesc() {
		return this.flowDesc;
	}
	
	public Set<FileAttach> getFileAttachs() {
		return fileAttachs;
	}

	public void setFileAttachs(Set<FileAttach> fileAttachs) {
		this.fileAttachs = fileAttachs;
	}

	/**
	 * Set the flowDesc
	 */	
	public void setFlowDesc(String aValue) {
		this.flowDesc = aValue;
	}	


	public Long getProcessRunId() {
		return processRunId;
	}

	public void setProcessRunId(Long processRunId) {
		this.processRunId = processRunId;
	}

	/**
	 * 流程节点	 * @return String
	 * @hibernate.property column="flowNode" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getFlowNode() {
		return this.flowNode;
	}
	
	/**
	 * Set the flowNode
	 */	
	public void setFlowNode(String aValue) {
		this.flowNode = aValue;
	}	

	/**
	 * 备注	 * @return String
	 * @hibernate.property column="remark" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getRemark() {
		return this.remark;
	}
	
	/**
	 * Set the remark
	 */	
	public void setRemark(String aValue) {
		this.remark = aValue;
	}

	public AppUser getApplyUser() {
		return applyUser;
	}

	public void setApplyUser(AppUser applyUser) {
		this.applyUser = applyUser;
	}

	public AppUser getRelatedUser() {
		return relatedUser;
	}

	public void setRelatedUser(AppUser relatedUser) {
		this.relatedUser = relatedUser;
	}	


}
