package com.xpsoft.oa.service.flow.impl;


import java.util.List;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.flow.TaskAgentDao;
import com.xpsoft.oa.model.flow.TaskAgent;
import com.xpsoft.oa.service.flow.TaskAgentService;

public class TaskAgentServiceImpl extends BaseServiceImpl<TaskAgent> implements TaskAgentService{
	private TaskAgentDao dao;
	
	public TaskAgentServiceImpl(TaskAgentDao dao) {
		super(dao);
		this.dao=dao;
	}
	@Override
	public TaskAgent isExist(String assignId) {
	
		List<TaskAgent> taList=this.dao.findByHql("from TaskAgent where assignId = ? and status=1", new Object[]{Long.parseLong(assignId)});
		
		if(taList.size()>0){
			return taList.get(0);
		}
		return null;
	}
}