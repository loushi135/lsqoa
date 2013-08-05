package com.xpsoft.oa.service.hrm;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.hrm.EmpProfile;

public abstract interface EmpProfileService extends BaseService<EmpProfile>
{
  public abstract boolean checkProfileNo(String paramString);
}

