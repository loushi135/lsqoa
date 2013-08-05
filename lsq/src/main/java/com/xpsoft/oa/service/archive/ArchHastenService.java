package com.xpsoft.oa.service.archive;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.archive.ArchHasten;
import java.util.Date;

public abstract interface ArchHastenService extends BaseService<ArchHasten>
{
  public abstract Date getLeastRecordByUser(Long paramLong);
}

