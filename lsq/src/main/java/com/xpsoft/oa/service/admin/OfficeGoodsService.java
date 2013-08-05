package com.xpsoft.oa.service.admin;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.admin.OfficeGoods;

public abstract interface OfficeGoodsService extends BaseService<OfficeGoods>
{
  public abstract void sendWarmMessage();
}

