package com.xpsoft.oa.dao.flow.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.flow.TaskAgentDao;
import com.xpsoft.oa.model.flow.TaskAgent;

public class TaskAgentDaoImpl extends BaseDaoImpl<TaskAgent> implements TaskAgentDao{

	public TaskAgentDaoImpl() {
		super(TaskAgent.class);
	}

}