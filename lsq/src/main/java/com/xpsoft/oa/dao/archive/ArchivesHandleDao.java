package com.xpsoft.oa.dao.archive;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.archive.ArchivesHandle;
import java.util.List;

public abstract interface ArchivesHandleDao extends BaseDao<ArchivesHandle>
{
  public abstract ArchivesHandle findByUAIds(Long paramLong1, Long paramLong2);

  public abstract List<ArchivesHandle> findByAid(Long paramLong);
}

