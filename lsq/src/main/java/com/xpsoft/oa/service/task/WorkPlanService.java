package com.xpsoft.oa.service.task;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.task.WorkPlan;
import java.util.List;

public abstract interface WorkPlanService extends BaseService<WorkPlan>
{
  public abstract List<WorkPlan> findByDepartment(WorkPlan paramWorkPlan, AppUser paramAppUser, PagingBean paramPagingBean);
}

