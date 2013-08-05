package com.xpsoft.oa.service.customer;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.customer.CusLinkman;

public abstract interface CusLinkmanService extends BaseService<CusLinkman>
{
  public abstract boolean checkMainCusLinkman(Long paramLong1, Long paramLong2);
}

