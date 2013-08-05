package com.xpsoft.oa.service.flow;


import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.flow.TaskAgent;

public interface TaskAgentService extends BaseService<TaskAgent>{
	/**
	 * 查询任务代办规则
	 * @param assignId
	 * @return
	 */
	public TaskAgent isExist(String assignId);
}


