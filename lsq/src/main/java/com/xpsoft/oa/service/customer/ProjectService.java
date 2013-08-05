package com.xpsoft.oa.service.customer;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.customer.Project;

public abstract interface ProjectService extends BaseService<Project>
{
  public abstract boolean checkProjectNo(String paramString);
}

