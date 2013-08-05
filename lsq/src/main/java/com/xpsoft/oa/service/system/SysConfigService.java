package com.xpsoft.oa.service.system;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.system.SysConfig;
import java.util.Map;

public abstract interface SysConfigService extends BaseService<SysConfig>
{
  public abstract SysConfig findByKey(String paramString);

  public abstract Map findByType();
}

