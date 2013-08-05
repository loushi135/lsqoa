// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.xpsoft.oa.service.flow;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.flow.TaskSignData;
import com.xpsoft.oa.model.system.AppUser;

import java.util.List;

public interface TaskSignDataService
	extends BaseService<TaskSignData>
{

	public abstract void addVote(String s, Short short1 ,AppUser curUser);

	public abstract Long getVoteCounts(String s, Short short1);

	public abstract List getByTaskId(String s);
}
