package com.xpsoft.oa.service.admin;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.admin.DepreRecord;
import java.util.Date;

public abstract interface DepreRecordService extends BaseService<DepreRecord>
{
  public abstract Date findMaxDate(Long paramLong);
}

