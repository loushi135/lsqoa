// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package com.xpsoft.oa.dao.flow;

import com.xpsoft.core.dao.BaseDao;
import java.util.List;

public interface TaskSignDataDao
	extends BaseDao
{

	public abstract Long getVoteCounts(String s, Short short1);

	public abstract List getByTaskId(String s);
}
