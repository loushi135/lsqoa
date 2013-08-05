// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.xpsoft.oa.dao.flow.impl;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.flow.TaskSignDataDao;
import com.xpsoft.oa.model.flow.TaskSignData;
import java.util.List;

public class TaskSignDataDaoImpl extends BaseDaoImpl
	implements TaskSignDataDao
{

	public TaskSignDataDaoImpl()
	{
		super(TaskSignData.class);
	}

	public Long getVoteCounts(String s, Short short1)
	{
		String s1 = "select count(dataId) from TaskSignData tsd where tsd.taskId=? and tsd.isAgree=?";
		Object obj = findUnique(s1, new Object[] {
			s, short1
		});
		return new Long(obj.toString());
	}

	public List getByTaskId(String s)
	{
		String s1 = "from TaskSignData tsd where tsd.taskId=?";
		return findByHql(s1, new Object[] {
			s
		});
	}
}
