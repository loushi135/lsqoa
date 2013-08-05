package com.xpsoft.oa.dao.system;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.system.ReportParam;
import java.util.List;

public abstract interface ReportParamDao extends BaseDao<ReportParam>
{
  public abstract List<ReportParam> findByRepTemp(Long paramLong);
}

