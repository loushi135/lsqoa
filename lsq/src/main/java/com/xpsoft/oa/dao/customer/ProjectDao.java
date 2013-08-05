package com.xpsoft.oa.dao.customer;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.customer.Project;

public abstract interface ProjectDao extends BaseDao<Project>
{
  public abstract boolean checkProjectNo(String paramString);
}
