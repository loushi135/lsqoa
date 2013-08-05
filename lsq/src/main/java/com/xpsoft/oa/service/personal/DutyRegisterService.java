package com.xpsoft.oa.service.personal;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.personal.DutyRegister;
import com.xpsoft.oa.model.system.AppUser;
import java.util.Date;

public abstract interface DutyRegisterService extends BaseService<DutyRegister>
{
  public abstract void signInOff(Long paramLong, Short paramShort, AppUser paramAppUser, Date paramDate);

  public abstract DutyRegister getTodayUserRegister(Long paramLong1, Short paramShort, Long paramLong2);
}

