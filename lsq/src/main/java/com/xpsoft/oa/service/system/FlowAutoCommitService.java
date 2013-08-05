package com.xpsoft.oa.service.system;

import org.jbpm.pvm.internal.task.TaskImpl;

import com.xpsoft.oa.action.flow.FlowRunInfo;

public interface FlowAutoCommitService {
	
	public boolean autoCommit(FlowRunInfo flowRunInfo);
	
	/**
	 * 检查当前审批人是否已经审批过
	 * @param executionId
	 * @param task
	 * @return
	 */
	public String checkCommit(FlowRunInfo flowRunInfo,TaskImpl task);

}
