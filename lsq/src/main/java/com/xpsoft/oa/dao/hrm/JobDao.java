package com.xpsoft.oa.dao.hrm;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.hrm.Job;
import java.util.List;

public abstract interface JobDao extends BaseDao<Job>
{
  public abstract List<Job> findByDep(Long paramLong);
  
  public abstract List<String> getAllJobName();
}

