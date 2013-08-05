package com.xpsoft.oa.model.flow;


import com.google.gson.annotations.Expose;

/**
 * HandleTask Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class HandleTask extends com.xpsoft.core.model.BaseModel {

	@Expose
    protected Long id;  
	@Expose
	protected Long taskId;
	@Expose
	protected Long runId;
	@Expose
	protected String subject;
	@Expose
	protected java.util.Date processrunCreatetime;
	@Expose
	protected String creater;
	@Expose
	protected String activityName;
	@Expose
	protected java.util.Date taskCreatetime;
	@Expose
	protected String assignee;
	@Expose
	protected Long assigneeId;
	@Expose
	protected Short runStatus;
	@Expose
	protected String url;
	@Expose
	protected String appsouce;


	/**
	 * Default Empty Constructor for class HandleTask
	 */
	public HandleTask () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class HandleTask
	 */
	public HandleTask (
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
	 * 	 * @return Long
	 * @hibernate.property column="taskId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getTaskId() {
		return this.taskId;
	}
	
	/**
	 * Set the taskId
	 */	
	public void setTaskId(Long aValue) {
		this.taskId = aValue;
	}	

	/**
	 * 	 * @return Long
	 * @hibernate.property column="runId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getRunId() {
		return this.runId;
	}
	
	/**
	 * Set the runId
	 */	
	public void setRunId(Long aValue) {
		this.runId = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="subject" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getSubject() {
		return this.subject;
	}
	
	/**
	 * Set the subject
	 */	
	public void setSubject(String aValue) {
		this.subject = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="processrun_createtime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getProcessrunCreatetime() {
		return this.processrunCreatetime;
	}
	
	/**
	 * Set the processrunCreatetime
	 */	
	public void setProcessrunCreatetime(java.util.Date aValue) {
		this.processrunCreatetime = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="creater" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getCreater() {
		return this.creater;
	}
	
	/**
	 * Set the creater
	 */	
	public void setCreater(String aValue) {
		this.creater = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="activityName" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getActivityName() {
		return this.activityName;
	}
	
	/**
	 * Set the activityName
	 */	
	public void setActivityName(String aValue) {
		this.activityName = aValue;
	}	

	/**
	 * 	 * @return java.util.Date
	 * @hibernate.property column="task_createtime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getTaskCreatetime() {
		return this.taskCreatetime;
	}
	
	/**
	 * Set the taskCreatetime
	 */	
	public void setTaskCreatetime(java.util.Date aValue) {
		this.taskCreatetime = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="assignee" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getAssignee() {
		return this.assignee;
	}
	
	/**
	 * Set the assignee
	 */	
	public void setAssignee(String aValue) {
		this.assignee = aValue;
	}	

	/**
	 * 	 * @return Long
	 * @hibernate.property column="assigneeId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	public Long getAssigneeId() {
		return this.assigneeId;
	}
	
	/**
	 * Set the assigneeId
	 */	
	public void setAssigneeId(Long aValue) {
		this.assigneeId = aValue;
	}	

	/**
	 * 	 * @return Integer
	 * @hibernate.property column="runStatus" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Short getRunStatus() {
		return this.runStatus;
	}
	
	/**
	 * Set the runStatus
	 */	
	public void setRunStatus(Short aValue) {
		this.runStatus = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="url" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getUrl() {
		return this.url;
	}
	
	/**
	 * Set the url
	 */	
	public void setUrl(String aValue) {
		this.url = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="appsouce" type="java.lang.String" length="255" not-null="false" unique="false"
	 */
	public String getAppsouce() {
		return this.appsouce;
	}
	
	/**
	 * Set the appsouce
	 */	
	public void setAppsouce(String aValue) {
		this.appsouce = aValue;
	}	

}
