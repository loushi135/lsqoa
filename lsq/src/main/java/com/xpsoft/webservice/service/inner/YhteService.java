package com.xpsoft.webservice.service.inner;

import org.model.YhoaHandleJob;

import com.xpsoft.oa.model.flow.HandleTask;

public interface YhteService {
	public String addHandleTask(HandleTask handleTask);
	public String updateHandleTaskRunStatus(Long runId);
    public String addYhteToGoldMantis(YhoaHandleJob yhoaHandleJob);
    public String processWaitHandleJob(String runId);
}
