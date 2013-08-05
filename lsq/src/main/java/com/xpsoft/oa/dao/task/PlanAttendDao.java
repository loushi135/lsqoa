package com.xpsoft.oa.dao.task;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.task.PlanAttend;
import java.util.List;

public abstract interface PlanAttendDao extends BaseDao<PlanAttend>
{
  public abstract List<PlanAttend> FindPlanAttend(Long paramLong, Short paramShort1, Short paramShort2);
}

