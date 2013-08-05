package com.xpsoft.oa.service.admin;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.admin.InStock;

public abstract interface InStockService extends BaseService<InStock>
{
  public abstract Integer findInCountByBuyId(Long paramLong);
}

