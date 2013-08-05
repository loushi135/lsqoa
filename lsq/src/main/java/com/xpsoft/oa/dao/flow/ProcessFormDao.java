package com.xpsoft.oa.dao.flow;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.flow.ProcessForm;
import java.util.List;
import java.util.Map;

public abstract interface ProcessFormDao extends BaseDao<ProcessForm>
{
  public abstract List getByRunId(Long paramLong);
  
  public List getByRunId(Long runId,Integer maxVersion);

  public abstract ProcessForm getByRunIdActivityName(Long paramLong, String paramString);

  public abstract Long getActvityExeTimes(Long paramLong, String paramString);

  public abstract Map getVariables(Long paramLong);
  
  public Integer getMaxVersion(Long runId);
  
  public List<ProcessForm> getBeforeProcessForm(Long processRunId,String activityName);
}

