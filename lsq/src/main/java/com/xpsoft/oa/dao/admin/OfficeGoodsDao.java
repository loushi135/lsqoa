package com.xpsoft.oa.dao.admin;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.admin.OfficeGoods;
import java.util.List;

public abstract interface OfficeGoodsDao extends BaseDao<OfficeGoods>
{
  public abstract List<OfficeGoods> findByWarm();
}

