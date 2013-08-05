package com.xpsoft.oa.service.flow;


import java.util.List;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.flow.HandleTask;

public interface HandleTaskService extends BaseService<HandleTask>{
	
	
	public List<HandleTask> getHandleTaskByAssigneeId(String assigneeId,Short runStatus);
	
	public HandleTask getByTaskId(Long taskId);
	
}


