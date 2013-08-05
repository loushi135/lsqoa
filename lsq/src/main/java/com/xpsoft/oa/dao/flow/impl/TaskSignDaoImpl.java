// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.xpsoft.oa.dao.flow.impl;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.flow.TaskSignDao;
import com.xpsoft.oa.model.flow.TaskSign;

public class TaskSignDaoImpl extends BaseDaoImpl
	implements TaskSignDao
{

	public TaskSignDaoImpl()
	{
		super(TaskSign.class);
	}

	public TaskSign findByAssignId(Long long1)
	{
		String s = "from TaskSign ts where ts.proUserAssign.assignId=? ";
		return (TaskSign)findUnique(s, new Object[] {
			long1
		});
	}
}
