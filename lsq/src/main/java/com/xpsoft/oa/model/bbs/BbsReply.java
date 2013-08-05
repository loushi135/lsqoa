package com.xpsoft.oa.model.bbs;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;

/**
 * BbsReply Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class BbsReply extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected String replyContent;
	@Expose
	protected java.util.Date replyTime;
	@Expose
	protected AppUser replyUser;
	@Expose
	protected BbsTopic topic;


	/**
	 * Default Empty Constructor for class BbsReply
	 */
	public BbsReply () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class BbsReply
	 */
	public BbsReply (
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
	 * 回复内容	 * @return String
	 * @hibernate.property column="replyContent" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getReplyContent() {
		return this.replyContent;
	}
	
	/**
	 * Set the replyContent
	 */	
	public void setReplyContent(String aValue) {
		this.replyContent = aValue;
	}	

	/**
	 * 回复时间	 * @return java.util.Date
	 * @hibernate.property column="replyTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getReplyTime() {
		return this.replyTime;
	}
	
	/**
	 * Set the replyTime
	 */	
	public void setReplyTime(java.util.Date aValue) {
		this.replyTime = aValue;
	}	


	public AppUser getReplyUser() {
		return replyUser;
	}

	public void setReplyUser(AppUser replyUser) {
		this.replyUser = replyUser;
	}


	public BbsTopic getTopic() {
		return topic;
	}

	public void setTopic(BbsTopic topic) {
		this.topic = topic;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BbsReply)) {
			return false;
		}
		BbsReply rhs = (BbsReply) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.replyContent, rhs.replyContent)
				.append(this.replyTime, rhs.replyTime)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.replyContent) 
				.append(this.replyTime) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("replyContent", this.replyContent) 
				.append("replyTime", this.replyTime) 
				.toString();
	}



}
