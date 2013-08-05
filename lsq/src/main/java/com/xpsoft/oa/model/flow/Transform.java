package com.xpsoft.oa.model.flow;

import org.jbpm.pvm.internal.model.Activity;
import org.jbpm.pvm.internal.model.Transition;

public class Transform {
	private String name;
	private String destination;
	private String source;
	private String destinationDisplay;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDestinationDisplay() {
		if(destinationDisplay.contains("流程结束")||destinationDisplay.contains("结束")){
			return "审批通过";
		}else if(destinationDisplay.contains("分支")||destinationDisplay.contains("提交")||destinationDisplay.contains("决策")){
			return "审批通过";
		}else if(destinationDisplay.contains("不通过")||destinationDisplay.contains("取消")||destinationDisplay.contains("驳回")){
			return "审批不通过";
		}else{
			return this.destination;
		}
	}

	public void setDestinationDisplay(String destinationDisplay) {
		this.destinationDisplay = destinationDisplay;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Transform() {
	}

	public Transform(Transition jbpmtran) {
		this.name = jbpmtran.getName();
		this.source = jbpmtran.getSource().getName();
		this.destination = jbpmtran.getDestination().getName();
		this.destinationDisplay=jbpmtran.getDestination().getName();
	}
}