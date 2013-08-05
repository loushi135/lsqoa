package com.xpsoft.webservice.service.inner;

import org.model.HandleJob;
import org.model.YhoaHandleJob;


public interface GoldMantisService {

	/**
	 *  添加代办事项
	 * String workflowName：流程名称
	 * String requestJobCode：请求者工号  流程发启人
	 * String handlerJobCode：处理者工号  下一个审批人
	 * String workflowNodeName：当前节点名称 
	 * String waitHandleJobUrl：点击后跳转的url
	 * String piId 实例id
	 */
	public void AddWaitHandleJob(HandleJob handleJob);
	/**
	 * 
	 * @param runId 流程运行ID
	 */
	public void processWaitHandleJob(String runId);
	
	public void addYhteToGoldMantis(YhoaHandleJob yhoaHandleJob)throws Exception ;
}
