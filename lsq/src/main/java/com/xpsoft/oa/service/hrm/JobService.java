package com.xpsoft.oa.service.hrm;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.hrm.Job;
import java.util.List;

public abstract interface JobService extends BaseService<Job>
{
  public abstract List<Job> findByDep(Long paramLong);
  
  public abstract Job findByDepAndJobName(Long depId,String jobName); 
  
  public abstract List<String> getAllJobName();
}

