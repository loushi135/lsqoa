package com.xpsoft.oa.dao.system;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.system.AppFunction;

public abstract interface AppFunctionDao extends BaseDao<AppFunction>
{
  public abstract AppFunction getByKey(String paramString);
}

