package com.xpsoft.oa.model.statistics;


import com.google.gson.annotations.Expose;

/**
 * OtherProject Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class OtherProject extends com.xpsoft.core.model.BaseModel {

	@Expose
    protected Long id;  
	@Expose
	protected String proNo;
	@Expose
	protected String proName;
	@Expose
	protected String remark;


	/**
	 * Default Empty Constructor for class OtherProject
	 */
	public OtherProject () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class OtherProject
	 */
	public OtherProject (
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
	
	/**
	 * Set the remark
	 */	
	public void setRemark(String aValue) {
		this.remark = aValue;
	}	

}
