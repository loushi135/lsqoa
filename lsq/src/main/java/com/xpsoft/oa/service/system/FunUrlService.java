package com.xpsoft.oa.service.system;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.system.FunUrl;

public abstract interface FunUrlService extends BaseService<FunUrl>
{
  public abstract FunUrl getByPathFunId(String paramString, Long paramLong);
}

