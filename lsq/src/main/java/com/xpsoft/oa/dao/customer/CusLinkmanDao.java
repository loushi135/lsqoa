package com.xpsoft.oa.dao.customer;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.customer.CusLinkman;

public abstract interface CusLinkmanDao extends BaseDao<CusLinkman>
{
  public abstract boolean checkMainCusLinkman(Long paramLong1, Long paramLong2);
}

