package com.xpsoft.oa.model.bbs;

import com.google.gson.annotations.Expose;

public class BbsModel {

	@Expose
	private String id;
	@Expose
	private String content;
	@Expose
	private String username;
	@Expose
	private String photo;
	@Expose
	private String fullname;
	@Expose
	private String accessionTime;
	@Expose
	private String publishTime;
	@Expose
	private String description;
	@Expose
	private String  buttonType;
	public BbsModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BbsModel(String id, String content, String username, String photo,
			String fullname, String accessionTime, String publishTime,
			String description) {
		super();
		this.id = id;
		this.content = content;
		this.username = username;
		this.photo = photo;
		this.fullname = fullname;
		this.accessionTime = accessionTime;
		this.publishTime = publishTime;
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getButtonType() {
		return buttonType;
	}
	public void setButtonType(String b) {
		this.buttonType = b;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAccessionTime() {
		return accessionTime;
	}
	public void setAccessionTime(String accessionTime) {
		this.accessionTime = accessionTime;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
