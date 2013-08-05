package com.xpsoft.oa.model.statistics;


import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * ProjectAudit Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ProjectAudit extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected java.math.BigDecimal auditAmunt;
	@Expose
	protected String remark;
	@Expose
	protected ProjectNew project;
	@Expose
	protected java.util.Date createtime;
	@Expose
	protected AppUser createUser;


	/**
	 * Default Empty Constructor for class ProjectAudit
	 */
	public ProjectAudit () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ProjectAudit
	 */
	public ProjectAudit (
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
	 * 审定金额	 * @return java.math.BigDecimal
	 * @hibernate.property column="auditAmunt" type="java.math.BigDecimal" length="10" not-null="false" unique="false"
	 */
	public java.math.BigDecimal getAuditAmunt() {
		return this.auditAmunt;
	}
	
	/**
	 * Set the auditAmunt
	 */	
	public void setAuditAmunt(java.math.BigDecimal aValue) {
		this.auditAmunt = aValue;
	}	

	/**
	 * 说明	 * @return String
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

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="createtime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	/**
	 * Set the createtime
	 */	
	public void setCreatetime(java.util.Date aValue) {
		this.createtime = aValue;
	}	


	public AppUser getCreateUser() {
		return createUser;
	}

	public void setCreateUser(AppUser createUser) {
		this.createUser = createUser;
	}

	public ProjectNew getProject() {
		return project;
	}

	public void setProject(ProjectNew project) {
		this.project = project;
	}




}
