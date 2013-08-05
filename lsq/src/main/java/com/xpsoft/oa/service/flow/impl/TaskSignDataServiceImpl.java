// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.xpsoft.oa.service.flow.impl;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.oa.dao.flow.TaskSignDataDao;
import com.xpsoft.oa.model.flow.TaskSignData;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.service.flow.TaskSignDataService;
import java.util.Date;
import java.util.List;

public class TaskSignDataServiceImpl extends BaseServiceImpl<TaskSignData>
	implements TaskSignDataService
{

	private TaskSignDataDao dao;

	public TaskSignDataServiceImpl(TaskSignDataDao tasksigndatadao)
	{
		super(tasksigndatadao);
		dao = tasksigndatadao;
	}

	public void addVote(String s, Short short1,AppUser curUser)
	{
		if(null==curUser){
			curUser=ContextUtil.getCurrentUser();
		}
		TaskSignData tasksigndata = new TaskSignData();
		tasksigndata.setTaskId(s);
		tasksigndata.setIsAgree(short1);
		tasksigndata.setVoteId(curUser.getUserId());
		tasksigndata.setVoteName(curUser.getFullname());
		tasksigndata.setVoteTime(new Date());
		save(tasksigndata);
	}

	public Long getVoteCounts(String s, Short short1)
	{
		return dao.getVoteCounts(s, short1);
	}

	public List getByTaskId(String s)
	{
		return dao.getByTaskId(s);
	}
}
