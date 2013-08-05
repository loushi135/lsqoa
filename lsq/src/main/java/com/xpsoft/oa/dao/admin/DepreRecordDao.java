package com.xpsoft.oa.dao.admin;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.admin.DepreRecord;
import java.util.Date;

public abstract interface DepreRecordDao extends BaseDao<DepreRecord>
{
  public abstract Date findMaxDate(Long paramLong);
}

