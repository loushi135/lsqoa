package com.xpsoft.oa.model.statistics;


import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.Department;
import com.xpsoft.oa.model.system.FileAttach;

/**
 * DesignProject Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class DesignProject extends com.xpsoft.core.model.BaseModel {

	@Expose
    protected Long id;  
	@Expose
	protected String proNo;
	@Expose
	protected String proName;
	@Expose
	protected String remark;
	@Expose
	protected Set<Department> depts = new HashSet<Department>();

	/**
	 * Default Empty Constructor for class DesignProject
	 */
	public DesignProject () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class DesignProject
	 */
	public DesignProject (
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
	 * 项目编号	 * @return String
	 * @hibernate.property column="proNo" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getProNo() {
		return this.proNo;
	}
	
	/**
	 * Set the proNo
	 */	
	public void setProNo(String aValue) {
		this.proNo = aValue;
	}	

	/**
	 * 项目名称	 * @return String
	 * @hibernate.property column="proName" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getProName() {
		return this.proName;
	}
	
	/**
	 * Set the proName
	 */	
	public void setProName(String aValue) {
		this.proName = aValue;
	}	


	/**
	 * 备注	 * @return String
	 * @hibernate.property column="remark" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getRemark() {
		return this.remark;
	}
	

	public Set<Department> getDepts() {
		return depts;
	}

	public void setDepts(Set<Department> depts) {
		this.depts = depts;
	}

	/**
	 * Set the remark
	 */	
	public void setRemark(String aValue) {
		this.remark = aValue;
	}	

}
