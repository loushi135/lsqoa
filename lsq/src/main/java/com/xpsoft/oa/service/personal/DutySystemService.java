package com.xpsoft.oa.service.personal;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.personal.DutySystem;

public abstract interface DutySystemService extends BaseService<DutySystem>
{
  public abstract DutySystem getDefaultDutySystem();
}

