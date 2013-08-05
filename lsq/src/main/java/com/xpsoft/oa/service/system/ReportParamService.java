package com.xpsoft.oa.service.system;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.system.ReportParam;
import java.util.List;

public abstract interface ReportParamService extends BaseService<ReportParam>
{
  public abstract List<ReportParam> findByRepTemp(Long paramLong);
}

