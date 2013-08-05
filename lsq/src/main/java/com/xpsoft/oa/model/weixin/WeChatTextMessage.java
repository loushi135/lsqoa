package com.xpsoft.oa.model.weixin;

import com.xpsoft.core.model.BaseModel;

public class WeChatTextMessage extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8165137762153871249L;
	private String toUserName;
	private String fromUserName;
	private String createTime;
	private String msgType;
	private String content;
	private String msgId;

	public WeChatTextMessage() {
		super();
	}

	public WeChatTextMessage(String toUserName, String fromUserName,
			String createTime, String msgType, String content, String msgId) {
		super();
		this.toUserName = toUserName;
		this.fromUserName = fromUserName;
		this.createTime = createTime;
		this.msgType = msgType;
		this.content = content;
		this.msgId = msgId;
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

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

}
