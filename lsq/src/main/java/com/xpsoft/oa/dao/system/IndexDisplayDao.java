package com.xpsoft.oa.dao.system;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.system.IndexDisplay;
import java.util.List;

public abstract interface IndexDisplayDao extends BaseDao<IndexDisplay>
{
  public abstract List<IndexDisplay> findByUser(Long paramLong);
}

