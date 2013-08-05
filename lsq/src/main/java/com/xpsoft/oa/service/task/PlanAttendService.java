package com.xpsoft.oa.service.task;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.task.PlanAttend;

public abstract interface PlanAttendService extends BaseService<PlanAttend>
{
  public abstract boolean deletePlanAttend(Long paramLong, Short paramShort1, Short paramShort2);
}

