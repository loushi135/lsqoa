package com.xpsoft.oa.model.flow;


import com.google.gson.annotations.Expose;

/**
 * ProDefNotice Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ProDefNotice extends com.xpsoft.core.model.BaseModel {

	@Expose
    protected Long defId;  
	@Expose
	protected ProDefinition proDefinition;
	@Expose
	protected String noticeUserIds;
	@Expose
	protected String noticeUserNames;
	@Expose
	protected String noticeDepIds;
	@Expose
	protected String noticeDepNames;
	@Expose
	protected String noticeRoleIds;
	@Expose
	protected String noticeRoleNames;


	/**
	 * Default Empty Constructor for class ProDefNotice
	 */
	public ProDefNotice () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ProDefNotice
	 */
	public ProDefNotice (
		 Long in_defId
        ) {
		this.setDefId(in_defId);
    }

    

	/**
	 * 	 * @return Long
     * @hibernate.id column="defId" type="java.lang.Long" generator-class="native"
	 */
	public Long getDefId() {
		return this.defId;
	}
	
	/**
	 * Set the defId
	 */	
	public void setDefId(Long aValue) {
		this.defId = aValue;
	}	

	/**
	 * 通知员工ID	 * @return String
	 * @hibernate.property column="noticeUserIds" type="java.lang.String" length="1000" not-null="false" unique="false"
	 */
	public String getNoticeUserIds() {
		return this.noticeUserIds;
	}
	
	/**
	 * Set the noticeUserIds
	 */	
	public void setNoticeUserIds(String aValue) {
		this.noticeUserIds = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="noticeUserNames" type="java.lang.String" length="1000" not-null="false" unique="false"
	 */
	public String getNoticeUserNames() {
		return this.noticeUserNames;
	}
	
	/**
	 * Set the noticeUserNames
	 */	
	public void setNoticeUserNames(String aValue) {
		this.noticeUserNames = aValue;
	}	

	/**
	 * 通知部门ID	 * @return String
	 * @hibernate.property column="noticeDepIds" type="java.lang.String" length="1000" not-null="false" unique="false"
	 */
	public String getNoticeDepIds() {
		return this.noticeDepIds;
	}
	
	/**
	 * Set the noticeDepIds
	 */	
	public void setNoticeDepIds(String aValue) {
		this.noticeDepIds = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="noticeDepNames" type="java.lang.String" length="1000" not-null="false" unique="false"
	 */
	public String getNoticeDepNames() {
		return this.noticeDepNames;
	}
	
	/**
	 * Set the noticeDepNames
	 */	
	public void setNoticeDepNames(String aValue) {
		this.noticeDepNames = aValue;
	}	

	public ProDefinition getProDefinition() {
		return proDefinition;
	}

	public void setProDefinition(ProDefinition proDefinition) {
		this.proDefinition = proDefinition;
	}

	/**
	 * 通知角色ID	 * @return String
	 * @hibernate.property column="noticeRoleIds" type="java.lang.String" length="1000" not-null="false" unique="false"
	 */
	public String getNoticeRoleIds() {
		return this.noticeRoleIds;
	}
	
	/**
	 * Set the noticeRoleIds
	 */	
	public void setNoticeRoleIds(String aValue) {
		this.noticeRoleIds = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="noticeRoleNames" type="java.lang.String" length="1000" not-null="false" unique="false"
	 */
	public String getNoticeRoleNames() {
		return this.noticeRoleNames;
	}
	
	/**
	 * Set the noticeRoleNames
	 */	
	public void setNoticeRoleNames(String aValue) {
		this.noticeRoleNames = aValue;
	}	

}
