package com.xpsoft.oa.model.bbs;


import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.AppUser;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * BbsTopic Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class BbsTopic extends com.xpsoft.core.model.BaseModel {
	@Expose
    protected Long id;
	@Expose
	protected String title;
	@Expose
	protected java.util.Date publishTime;
	@Expose
	protected java.util.Date lastUpdateTime;
	@Expose
	protected Integer viewCount;
	@Expose
	protected Integer isTop;
	@Expose
	protected Integer isPrime;
	@Expose
	protected Integer isLock;
	@Expose
	protected Integer isReply;
	@Expose
	protected AppUser user;
	@Expose
	protected String content;
	@Expose
	protected BbsGroup group;
	@Expose
	protected Long topId;
	
	
	/**
	 * Default Empty Constructor for class BbsTopic
	 */
	public BbsTopic () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class BbsTopic
	 */
	public BbsTopic (
		 Long in_id
        ) {
		this.setId(in_id);
    }

	public Long getTopId() {
		return topId;
	}

	public void setTopId(Long topId) {
		this.topId = topId;
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
	 * 标题	 * @return String
	 * @hibernate.property column="title" type="java.lang.String" length="128" not-null="false" unique="false"
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Set the title
	 */	
	public void setTitle(String aValue) {
		this.title = aValue;
	}	

	/**
	 * 发布时间	 * @return java.util.Date
	 * @hibernate.property column="publishTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getPublishTime() {
		return this.publishTime;
	}
	
	/**
	 * Set the publishTime
	 */	
	public void setPublishTime(java.util.Date aValue) {
		this.publishTime = aValue;
	}	

	/**
	 * 最后更新时间	 * @return java.util.Date
	 * @hibernate.property column="lastUpdateTime" type="java.util.Date" length="19" not-null="false" unique="false"
	 */
	public java.util.Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}
	
	/**
	 * Set the lastUpdateTime
	 */	
	public void setLastUpdateTime(java.util.Date aValue) {
		this.lastUpdateTime = aValue;
	}	

	/**
	 * 浏览次数	 * @return Integer
	 * @hibernate.property column="viewCount" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getViewCount() {
		return this.viewCount;
	}
	
	/**
	 * Set the viewCount
	 */	
	public void setViewCount(Integer aValue) {
		this.viewCount = aValue;
	}	

	/**
	 * 是否至顶	 * @return Integer
	 * @hibernate.property column="isTop" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getIsTop() {
		return this.isTop;
	}
	
	/**
	 * Set the isTop
	 */	
	public void setIsTop(Integer aValue) {
		this.isTop = aValue;
	}	

	/**
	 * 是否加精	 * @return Integer
	 * @hibernate.property column="isPrime" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getIsPrime() {
		return this.isPrime;
	}
	
	/**
	 * Set the isPrime
	 */	
	public void setIsPrime(Integer aValue) {
		this.isPrime = aValue;
	}	

	/**
	 * 是否被锁定	 * @return Integer
	 * @hibernate.property column="isLock" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getIsLock() {
		return this.isLock;
	}
	
	/**
	 * Set the isLock
	 */	
	public void setIsLock(Integer aValue) {
		this.isLock = aValue;
	}	

	/**
	 * 是否能回复	 * @return Integer
	 * @hibernate.property column="isReply" type="java.lang.Integer" length="10" not-null="false" unique="false"
	 */
	public Integer getIsReply() {
		return this.isReply;
	}
	
	/**
	 * Set the isReply
	 */	
	public void setIsReply(Integer aValue) {
		this.isReply = aValue;
	}	

	/**
	 * 用户Id	 * @return Long
	 * @hibernate.property column="userId" type="java.lang.Long" length="19" not-null="false" unique="false"
	 */
	
	
	/**
	 * Set the userId
	 */	

	/**
	 * 内容	 * @return String
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

	
	public boolean equals(Object object) {
		if (!(object instanceof BbsTopic)) {
			return false;
		}
		BbsTopic rhs = (BbsTopic) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
				.append(this.title, rhs.title)
				.append(this.publishTime, rhs.publishTime)
				.append(this.lastUpdateTime, rhs.lastUpdateTime)
				.append(this.viewCount, rhs.viewCount)
				.append(this.isTop, rhs.isTop)
				.append(this.isPrime, rhs.isPrime)
				.append(this.isLock, rhs.isLock)
				.append(this.isReply, rhs.isReply)
				.append(this.content, rhs.content)
			
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
				.append(this.title) 
				.append(this.publishTime) 
				.append(this.lastUpdateTime) 
				.append(this.viewCount) 
				.append(this.isTop) 
				.append(this.isPrime) 
				.append(this.isLock) 
				.append(this.isReply) 
				.append(this.content) 
		 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
				.append("title", this.title) 
				.append("publishTime", this.publishTime) 
				.append("lastUpdateTime", this.lastUpdateTime) 
				.append("viewCount", this.viewCount) 
				.append("isTop", this.isTop) 
				.append("isPrime", this.isPrime) 
				.append("isLock", this.isLock) 
				.append("isReply", this.isReply) 
				.append("content", this.content) 
				.toString();
	}

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public BbsGroup getGroup() {
		return group;
	}

	public void setGroup(BbsGroup group) {
		this.group = group;
	}



}
