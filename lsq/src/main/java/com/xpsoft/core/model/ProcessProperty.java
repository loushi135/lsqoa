package com.xpsoft.core.model;

public class ProcessProperty {
	
	private String processPropertyStr;
	private String isReceived;//0不能短信回复审批    1可以短信审批
	
	
	
	public ProcessProperty() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProcessProperty(String processPropertyStr, String isReceived) {
		this.processPropertyStr = processPropertyStr;
		this.isReceived = isReceived;
	}
	public String getProcessPropertyStr() {
		return processPropertyStr;
	}
	public void setProcessPropertyStr(String processPropertyStr) {
		this.processPropertyStr = processPropertyStr;
	}
	public String getIsReceived() {
		return isReceived;
	}
	public void setIsReceived(String isReceived) {
		this.isReceived = isReceived;
	}
	
	
	

}
