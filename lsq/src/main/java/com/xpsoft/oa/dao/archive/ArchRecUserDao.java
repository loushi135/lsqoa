package com.xpsoft.oa.dao.archive;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.archive.ArchRecUser;
import java.util.List;

public abstract interface ArchRecUserDao extends BaseDao<ArchRecUser>
{
  public abstract List findDepAll();

  public abstract ArchRecUser getByDepId(Long paramLong);
}

