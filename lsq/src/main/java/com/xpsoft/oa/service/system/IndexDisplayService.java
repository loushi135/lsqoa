package com.xpsoft.oa.service.system;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.system.IndexDisplay;
import java.util.List;

public abstract interface IndexDisplayService extends BaseService<IndexDisplay>
{
  public abstract List<IndexDisplay> findByUser(Long paramLong);
}

