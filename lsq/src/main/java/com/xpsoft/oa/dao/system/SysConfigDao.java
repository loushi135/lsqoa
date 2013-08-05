package com.xpsoft.oa.dao.system;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.system.SysConfig;
import java.util.List;

public abstract interface SysConfigDao extends BaseDao<SysConfig>
{
  public abstract SysConfig findByKey(String paramString);

  public abstract List<SysConfig> findConfigByTypeName(String paramString);

  public abstract List findTypeNames();
}

