package com.xpsoft.oa.service.flow.impl;


import java.util.List;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.flow.HandleTaskDao;
import com.xpsoft.oa.model.flow.HandleTask;
import com.xpsoft.oa.service.flow.HandleTaskService;

public class HandleTaskServiceImpl extends BaseServiceImpl<HandleTask> implements HandleTaskService{
	private HandleTaskDao dao;
	
	public HandleTaskServiceImpl(HandleTaskDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	
	public List<HandleTask> getHandleTaskByAssigneeId(String assigneeId,Short runStatus){
		if(runStatus<0){
			return this.dao.findByHql("from HandleTask where assigneeId =?", new Object[]{Long.parseLong(assigneeId)});
		}else{
			return this.dao.findByHql("from HandleTask where assigneeId =? and runStatus = ?", new Object[]{Long.parseLong(assigneeId),runStatus});
		}
	}
	
	public HandleTask getByTaskId(Long taskId){
		List<HandleTask> list=dao.findByHql("from HandleTask where taskId =? ", new Object[]{taskId});
		return list.size()>0?list.get(0):null;
	}

}