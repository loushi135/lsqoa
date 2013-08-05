package com.xpsoft.oa.dao.task;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.task.Appointment;
import java.util.List;

public abstract interface AppointmentDao extends BaseDao<Appointment>
{
  public abstract List showAppointmentByUserId(Long paramLong, PagingBean paramPagingBean);
}

