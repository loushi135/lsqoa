package com.xpsoft.oa.dao.admin;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.admin.InStock;

public abstract interface InStockDao extends BaseDao<InStock>
{
  public abstract Integer findInCountByBuyId(Long paramLong);
}

