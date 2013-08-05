package com.xpsoft.oa.model.info;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.annotations.Expose;
import com.xpsoft.oa.model.system.FileAttach;

public class Notice {
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
	private Integer viewCounts;
	@Expose
	protected Set<FileAttach> attachFiles = new HashSet();

	public String getPostName() {
		return this.postName;
	}

	public long getNoticeId() {
		return this.noticeId;
	}

	public void setNoticeId(long noticeId) {
		this.noticeId = noticeId;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getNoticeTitle() {
		return this.noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return this.noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Integer getViewCounts() {
		return viewCounts;
	}

	public void setViewCounts(Integer viewCounts) {
		this.viewCounts = viewCounts;
	}

	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public short getState() {
		return this.state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public Set<FileAttach> getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(Set<FileAttach> attachFiles) {
		this.attachFiles = attachFiles;
	}
}
