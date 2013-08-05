package com.xpsoft.oa.model.info;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.model.system.TreeType;

/**
 * ArticleSend Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * 公告
 */
public class ArticleSend extends com.xpsoft.core.model.BaseModel {

	@Expose
	private long noticeId;
	@Expose
	private String postName;
	@Expose
	private String noticeTitle;
	@Expose
	private String noticeContent;
	@Expose
	private Date effectiveDate;
	@Expose
	private Date expirationDate;
	@Expose
	private short state;
	@Expose
	protected TreeType treeType;

	@Expose
	protected Set<FileAttach> attachFiles = new HashSet();
	/**
	 * Default Empty Constructor for class ArticleSend
	 */
	public ArticleSend () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ArticleSend
	 */
	public ArticleSend (
		 Long in_noticeId
        ) {
		this.setNoticeId(in_noticeId);
    }

    

	/**
	 * 	 * @return Long
     * @hibernate.id column="noticeId" type="java.lang.Long" generator-class="native"
	 */
	public Long getNoticeId() {
		return this.noticeId;
	}
	
	/**
	 * Set the noticeId
	 */	
	public void setNoticeId(Long aValue) {
		this.noticeId = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="postName" type="java.lang.String" length="128" not-null="true" unique="false"
	 */
	public String getPostName() {
		return this.postName;
	}
	
	public Set<FileAttach> getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(Set<FileAttach> attachFiles) {
		this.attachFiles = attachFiles;
	}

	/**
	 * Set the postName
	 * @spring.validator type="required"
	 */	
	public void setPostName(String aValue) {
		this.postName = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="noticeTitle" type="java.lang.String" length="500" not-null="true" unique="false"
	 */
	public String getNoticeTitle() {
		return this.noticeTitle;
	}
	
	/**
	 * Set the noticeTitle
	 * @spring.validator type="required"
	 */	
	public void setNoticeTitle(String aValue) {
		this.noticeTitle = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="noticeContent" type="java.lang.String" length="16777215" not-null="false" unique="false"
	 */
	public String getNoticeContent() {
		return this.noticeContent;
	}
	
	/**
	 * Set the noticeContent
	 */	
	public void setNoticeContent(String aValue) {
		this.noticeContent = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="effectiveDate" type="java.util.Date" length="10" not-null="false" unique="false"
	 */
	public java.util.Date getEffectiveDate() {
		return this.effectiveDate;
	}
	
	/**
	 * Set the effectiveDate
	 */	
	public void setEffectiveDate(java.util.Date aValue) {
		this.effectiveDate = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="expirationDate" type="java.util.Date" length="10" not-null="false" unique="false"
	 */
	public java.util.Date getExpirationDate() {
		return this.expirationDate;
	}
	
	/**
	 * Set the expirationDate
	 */	
	public void setExpirationDate(java.util.Date aValue) {
		this.expirationDate = aValue;
	}	

	/**
	 * 	 * @return Short
	 * @hibernate.property column="state" type="java.lang.Short" length="5" not-null="true" unique="false"
	 */
	public Short getState() {
		return this.state;
	}
	
	/**
	 * Set the state
	 * @spring.validator type="required"
	 */	
	public void setState(Short aValue) {
		this.state = aValue;
	}

	public TreeType getTreeType() {
		return treeType;
	}

	public void setTreeType(TreeType treeType) {
		this.treeType = treeType;
	}

	public void setNoticeId(long noticeId) {
		this.noticeId = noticeId;
	}

	public void setState(short state) {
		this.state = state;
	}	

	

}
