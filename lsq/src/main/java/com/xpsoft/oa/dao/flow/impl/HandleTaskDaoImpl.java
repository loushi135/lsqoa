package com.xpsoft.oa.dao.flow.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.flow.HandleTaskDao;
import com.xpsoft.oa.model.flow.HandleTask;

public class HandleTaskDaoImpl extends BaseDaoImpl<HandleTask> implements HandleTaskDao{

	public HandleTaskDaoImpl() {
		super(HandleTask.class);
	}

}