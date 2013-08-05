package com.xpsoft.oa.model.statistics;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.FileAttach;

/**
 * OutTaxApply Base Java Bean, base class for the.oa.model, mapped directly to
 * database table
 * 
 * Avoid changing this file if not necessary, will be overwritten.
 * 
 * TODO: add class/table comments
 */
public class OutTaxApply extends com.xpsoft.core.model.BaseModel {

	@Expose
	protected Long id;
	@Expose
	protected ProjectNew projectNew;
	@Expose
	protected String attathType;
	@Expose
	protected String amountDX;
	@Expose
	protected java.math.BigDecimal amountXX;
	@Expose
	protected AppUser userCreate;
	@Expose
	protected java.util.Date timeCreate;
	@Expose
	protected Long processRunId;
	@Expose
	protected Set<FileAttach> fileAttachs = new HashSet<FileAttach>();

	/**
	 * Default Empty Constructor for class OutTaxApply
	 */
	public OutTaxApply() {
		super();
	}

	/**
	 * Default Key Fields Constructor for class OutTaxApply
	 */
	public OutTaxApply(Long in_id) {
		this.setId(in_id);
	}

	/**
	 * ID * @return Long
	 * 
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
	 * 项目ID * @return Long
	 * 
	 * @hibernate.property column="proId" type="java.lang.Long" length="19"
	 *                     not-null="false" unique="false"
	 */

	/**
	 * 附件类型 * @return String
	 * 
	 * @hibernate.property column="attathType" type="java.lang.String"
	 *                     length="128" not-null="false" unique="false"
	 */
	public String getAttathType() {
		return this.attathType;
	}


	public ProjectNew getProjectNew() {
		return projectNew;
	}

	public void setProjectNew(ProjectNew projectNew) {
		this.projectNew = projectNew;
	}

	/**
	 * Set the attathType
	 */
	public void setAttathType(String aValue) {
		this.attathType = aValue;
	}

	/**
	 * 金额大写 * @return String
	 * 
	 * @hibernate.property column="amountDX" type="java.lang.String"
	 *                     length="128" not-null="false" unique="false"
	 */
	public String getAmountDX() {
		return this.amountDX;
	}

	/**
	 * Set the amountDX
	 */
	public void setAmountDX(String aValue) {
		this.amountDX = aValue;
	}

	/**
	 * 金额小写 * @return java.math.BigDecimal
	 * 
	 * @hibernate.property column="amountXX" type="java.math.BigDecimal"
	 *                     length="15" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getAmountXX() {
		return this.amountXX;
	}

	/**
	 * Set the amountXX
	 */
	public void setAmountXX(java.math.BigDecimal aValue) {
		this.amountXX = aValue;
	}


	/**
	 * 审批通过时间 * @return java.util.Date
	 * 
	 * @hibernate.property column="timeCreate" type="java.util.Date" length="19"
	 *                     not-null="false" unique="false"
	 */
	public java.util.Date getTimeCreate() {
		return this.timeCreate;
	}

	public AppUser getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(AppUser userCreate) {
		this.userCreate = userCreate;
	}

	/**
	 * Set the timeCreate
	 */
	public void setTimeCreate(java.util.Date aValue) {
		this.timeCreate = aValue;
	}

	/**
	 * 流程ID * @return Long
	 * 
	 * @hibernate.property column="processRunId" type="java.lang.Long"
	 *                     length="19" not-null="false" unique="false"
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

	public Set<FileAttach> getFileAttachs() {
		return fileAttachs;
	}

	public void setFileAttachs(Set<FileAttach> fileAttachs) {
		this.fileAttachs = fileAttachs;
	}

}
