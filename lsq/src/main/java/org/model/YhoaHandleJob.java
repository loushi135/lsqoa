package org.model;

import java.io.Serializable;

import com.xpsoft.core.model.BaseModel;

public class YhoaHandleJob extends BaseModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 826760291120931688L;
	Long id;
	/**
	 * 调用客户端,请一定传LJTOA，不然无法调用
	 * */
	String sourceApp;
	/**
	 * 错误信息,若调用成功 则为空
	 * */
	String message;
	/**
	 * 代办事项编号，编号建议同ERP系统中编号风格，建议"LJTOA-1212-0001"
	 * */
	String billCode;
	/**
	 * 代办ID，请确保唯一性,以便这里处理
	 * */
	String waitHandleJobId;
	/**
	 * 流程名称
	 * */
	String workflowName;
	/**
	 * 请求者工号
	 * */
	String requestJobCode;
	/**
	 * 处理者工号
	 * */
	String handlerJobCode;
	/**
	 * 当前节点名称
	 * */
	String workflowNodeName;
	/**
	 * 点击后跳转的url
	 * */
	String waitHandleJobUrl;
	/**
	 * 处理者工号
	 * */
	String factHandleJobCode;
	/**
	 * 请求方法
	 */
	String requestMethod;
	
	
	/**
	 * 流程表单主ID
	 */
	String runId;
	
	public YhoaHandleJob() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSourceApp() {
		return sourceApp;
	}
	public void setSourceApp(String sourceApp) {
		this.sourceApp = sourceApp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBillCode() {
		return billCode;
	}
	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}
	public String getWorkflowName() {
		return workflowName;
	}
	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}
	public String getRequestJobCode() {
		return requestJobCode;
	}
	public void setRequestJobCode(String requestJobCode) {
		this.requestJobCode = requestJobCode;
	}
	public String getHandlerJobCode() {
		return handlerJobCode;
	}
	public void setHandlerJobCode(String handlerJobCode) {
		this.handlerJobCode = handlerJobCode;
	}
	public String getWorkflowNodeName() {
		return workflowNodeName;
	}
	public void setWorkflowNodeName(String workflowNodeName) {
		this.workflowNodeName = workflowNodeName;
	}
	public String getWaitHandleJobUrl() {
		return waitHandleJobUrl;
	}
	public void setWaitHandleJobUrl(String waitHandleJobUrl) {
		this.waitHandleJobUrl = waitHandleJobUrl;
	}
	public String getWaitHandleJobId() {
		return waitHandleJobId;
	}
	public void setWaitHandleJobId(String waitHandleJobId) {
		this.waitHandleJobId = waitHandleJobId;
	}
	public String getFactHandleJobCode() {
		return factHandleJobCode;
	}
	public void setFactHandleJobCode(String factHandleJobCode) {
		this.factHandleJobCode = factHandleJobCode;
	}
	public String getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	public String getRunId() {
		return runId;
	}
	public void setRunId(String runId) {
		this.runId = runId;
	}
	
}
