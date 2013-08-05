package com.xpsoft.oa.dao.archive;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.archive.ArchHasten;
import java.util.Date;

public abstract interface ArchHastenDao extends BaseDao<ArchHasten>
{
  public abstract Date getLeastRecordByUser(Long paramLong);
}

