package com.xpsoft.oa.model.hrm;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.xpsoft.oa.model.hrm.TrainReport;
import com.xpsoft.oa.model.system.AppUser;

/**
 * TrainreportReply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class TrainreportReply extends com.xpsoft.core.model.BaseModel {


	protected Long replyId;
	protected TrainReport trainReport;
	protected String content;
	protected java.util.Date createDate;
	protected String fullname;
	protected AppUser appUser;


	/**
	 * Default Empty Constructor for class TrainreportReply
	 */
	public TrainreportReply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class TrainreportReply
	 */
	public TrainreportReply (
		 Long in_replyId
        ) {
		this.setReplyId(in_replyId);
    }

    

	/**
	 * 	 * @return Long
     * @hibernate.id column="replyId" type="java.lang.Long" generator-class="native"
	 */
	public Long getReplyId() {
		return this.replyId;
	}
	
	/**
	 * Set the replyId
	 */	
	public void setReplyId(Long aValue) {
		this.replyId = aValue;
	}	

	 public TrainReport getTrainReport() {
			return trainReport;
		}

		public void setTrainReport(TrainReport trainReport) {
			this.trainReport = trainReport;
		}

		public AppUser getAppUser() {
			return appUser;
		}

		public void setAppUser(AppUser appUser) {
			this.appUser = appUser;
		}

	/**
	 * 培训报告回复的内容	 * @return String
	 * @hibernate.property column="content" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * Set the content
	 */	
	public void setContent(String aValue) {
		this.content = aValue;
	}	

	/**
	 * 创建日期	 * @return java.util.Date
	 * @hibernate.property column="createDate" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * Set the createDate
	 */	
	public void setCreateDate(java.util.Date aValue) {
		this.createDate = aValue;
	}	

	/**
	 * 创建人姓名	 * @return String
	 * @hibernate.property column="fullname" type="java.lang.String" length="150" not-null="false" unique="false"
	 */
	public String getFullname() {
		return this.fullname;
	}
	
	/**
	 * Set the fullname
	 */	
	public void setFullname(String aValue) {
		this.fullname = aValue;
	}	

	/**
	 * 创建人Id	 * @return Long
	 * @hibernate.property column="userId" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof TrainreportReply)) {
			return false;
		}
		TrainreportReply rhs = (TrainreportReply) object;
		return new EqualsBuilder()
				.append(this.replyId, rhs.replyId)
				.append(this.content, rhs.content)
				.append(this.createDate, rhs.createDate)
				.append(this.fullname, rhs.fullname)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.replyId) 
				.append(this.content) 
				.append(this.createDate) 
				.append(this.fullname) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("replyId", this.replyId) 
				.append("content", this.content) 
				.append("createDate", this.createDate) 
				.append("fullname", this.fullname) 
				.toString();
	}
}
