package com.xpsoft.oa.service.personal;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.personal.Duty;
import java.util.Date;

public abstract interface DutyService extends BaseService<Duty>
{
  public abstract boolean isExistDutyForUser(Long paramLong, Date paramDate1, Date paramDate2);

  public abstract Duty getCurUserDuty(Long paramLong);
}

