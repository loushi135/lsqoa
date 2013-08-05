package com.xpsoft.oa.dao.personal;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.personal.Duty;
import java.util.Date;
import java.util.List;

public abstract interface DutyDao extends BaseDao<Duty>
{
  public abstract List<Duty> getUserDutyByTime(Long paramLong, Date paramDate1, Date paramDate2);

  public abstract List<Duty> getCurUserDuty(Long paramLong);
}

