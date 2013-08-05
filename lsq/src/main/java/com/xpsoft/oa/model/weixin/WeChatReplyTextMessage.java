package com.xpsoft.oa.model.weixin;

import com.xpsoft.core.model.BaseModel;

public class WeChatReplyTextMessage extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6833027559801121833L;

	private String toUserName;
	private String fromUserName;
	private String createTime;
	private String msgType;
	private String content;
	private String funcFlag;
	public WeChatReplyTextMessage() {
		super();
	}
	public WeChatReplyTextMessage(String toUserName, String fromUserName,
			String createTime, String msgType, String content, String funcFlag) {
		super();
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.msgType = msgType;
		this.content = content;
		this.funcFlag = funcFlag;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFuncFlag() {
		return funcFlag;
	}
	public void setFuncFlag(String funcFlag) {
		this.funcFlag = funcFlag;
	}
	
}
