package com.xpsoft.oa.service.task;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.task.CalendarPlan;
import java.util.Date;
import java.util.List;

public abstract interface CalendarPlanService extends BaseService<CalendarPlan>
{
  public abstract List<CalendarPlan> getTodayPlans(Long paramLong, PagingBean paramPagingBean);

  public abstract List<CalendarPlan> getByPeriod(Long paramLong, Date paramDate1, Date paramDate2);

  public abstract List showCalendarPlanByUserId(Long paramLong, PagingBean paramPagingBean);
}

