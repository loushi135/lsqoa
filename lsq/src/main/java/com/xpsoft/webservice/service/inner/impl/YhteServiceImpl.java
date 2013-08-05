package com.xpsoft.webservice.service.inner.impl;

import org.model.YhoaHandleJob;

import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.model.flow.HandleTask;
import com.xpsoft.oa.service.flow.HandleTaskService;
import com.xpsoft.webservice.service.inner.GoldMantisService;
import com.xpsoft.webservice.service.inner.YhteService;

public class YhteServiceImpl implements YhteService {
	
	private HandleTaskService handleTaskService = (HandleTaskService)AppUtil.getBean("handleTaskService");
	
	public String addHandleTask(HandleTask handleTask){
		try {
			handleTaskService.save(handleTask);
		} catch (Exception e) {
			return "YhteServiceImpl.AddHandleTask  | 远程保存handleTask失败！";
		}
		return "YhteServiceImpl.AddHandleTask  |远程保存handleTask成功!";
	}
	
	public String updateHandleTaskRunStatus(Long taskId){
		try {
			HandleTask handleTask =handleTaskService.getByTaskId(taskId);
			handleTask.setRunStatus((short)2);
			handleTaskService.merge(handleTask);
		} catch (Exception e) {
			return "YhteServiceImpl.updateRunStatus | 远程更新handleTask失败！";
		}
		return "YhteServiceImpl.updateRunStatus  |远程更新handleTask成功!";
	}

	@Override
	public String addYhteToGoldMantis(YhoaHandleJob yhoaHandleJob) {
		try {
			GoldMantisService goldMantisService = (GoldMantisService)AppUtil.getBean("goldMantisService");
			goldMantisService.addYhteToGoldMantis(yhoaHandleJob);
		} catch (Exception e) {
			return "YhteServiceImpl.addYhteToGoldMantis | 向金螳螂增加待办事项失败！";
		}
		return "YhteServiceImpl.addYhteToGoldMantis | 向金螳螂增加待办事项成功!";
	}
	@Override
	public String processWaitHandleJob(String runId){
		try {
			GoldMantisService goldMantisService = (GoldMantisService)AppUtil.getBean("goldMantisService");
			goldMantisService.processWaitHandleJob(runId);
		} catch (Exception e) {
			return "YhteServiceImpl.processWaitHandleJob | 金螳螂删除待办事项失败！";
		}
		return "YhteServiceImpl.processWaitHandleJob | 金螳螂删除待办事项成功!";
	}

}
