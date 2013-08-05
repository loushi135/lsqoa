// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.xpsoft.oa.service.flow.impl;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.flow.TaskSignDao;
import com.xpsoft.oa.model.flow.TaskSign;
import com.xpsoft.oa.service.flow.TaskSignService;

public class TaskSignServiceImpl extends BaseServiceImpl<TaskSign>
	implements TaskSignService
{

	private TaskSignDao dao;

	public TaskSignServiceImpl(TaskSignDao tasksigndao)
	{
		super(tasksigndao);
		dao = tasksigndao;
	}

	public TaskSign findByAssignId(Long long1)
	{
		return dao.findByAssignId(long1);
	}
}
