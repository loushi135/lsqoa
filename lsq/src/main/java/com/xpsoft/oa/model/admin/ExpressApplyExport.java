package com.xpsoft.oa.model.admin;

import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.system.Department;

/**
 * ExpressApply Base Java Bean, base class for the.oa.model, mapped directly to
 * database table
 * 
 * Avoid changing this file if not necessary, will be overwritten.
 * 
 * TODO: add class/table comments
 */
public class ExpressApplyExport extends com.xpsoft.core.model.BaseModel {

	protected String applyer;
	protected java.util.Date applyDate;
	protected String expressType;
	protected String province;
	protected String city;
	protected String toWhereName;
	protected String expressName;
	protected String expressNo;
	protected Department dept;
	protected ProjectNew projectNew;
	protected String dispatchType;

	/**
	 * Default Empty Constructor for class ExpressApply
	 */
	public ExpressApplyExport() {
		super();
	}

	public String getApplyer() {
		return applyer;
	}

	public void setApplyer(String applyer) {
		this.applyer = applyer;
	}

	public java.util.Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(java.util.Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getExpressType() {
		return expressType;
	}

	public void setExpressType(String expressType) {
		this.expressType = expressType;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getToWhereName() {
		return toWhereName;
	}

	public void setToWhereName(String toWhereName) {
		this.toWhereName = toWhereName;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public ProjectNew getProjectNew() {
		return projectNew;
	}

	public void setProjectNew(ProjectNew projectNew) {
		this.projectNew = projectNew;
	}

	public String getDispatchType() {
		return dispatchType;
	}

	public void setDispatchType(String dispatchType) {
		this.dispatchType = dispatchType;
	}

}
