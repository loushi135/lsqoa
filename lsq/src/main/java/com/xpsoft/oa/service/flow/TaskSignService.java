// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.xpsoft.oa.service.flow;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.flow.TaskSign;

public interface TaskSignService
	extends BaseService<TaskSign>
{

	public abstract TaskSign findByAssignId(Long long1);
}
