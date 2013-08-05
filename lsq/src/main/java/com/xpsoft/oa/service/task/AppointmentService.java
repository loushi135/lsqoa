package com.xpsoft.oa.service.task;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.task.Appointment;
import java.util.List;

public abstract interface AppointmentService extends BaseService<Appointment>
{
  public abstract List showAppointmentByUserId(Long paramLong, PagingBean paramPagingBean);
}

