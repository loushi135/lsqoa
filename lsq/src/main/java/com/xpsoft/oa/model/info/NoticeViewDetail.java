package com.xpsoft.oa.model.info;


import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * NoticeViewDetail Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class NoticeViewDetail extends com.xpsoft.core.model.BaseModel {

	@Expose
    protected Long id;  
	@Expose
	protected Notice notice;
	@Expose
	protected AppUser appUser;
	@Expose
	protected java.util.Date viewDate;


	/**
	 * Default Empty Constructor for class NoticeViewDetail
	 */
	public NoticeViewDetail () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class NoticeViewDetail
	 */
	public NoticeViewDetail (
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


	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	/**
	 * 查看时间	 * @return java.util.Date
	 * @hibernate.property column="viewDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getViewDate() {
		return this.viewDate;
	}
	
	/**
	 * Set the viewDate
	 */	
	public void setViewDate(java.util.Date aValue) {
		this.viewDate = aValue;
	}	

}
