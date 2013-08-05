package com.xpsoft.oa.dao.archive;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.archive.ArchFlowConf;

public abstract interface ArchFlowConfDao extends BaseDao<ArchFlowConf>
{
  public abstract ArchFlowConf getByFlowType(Short paramShort);
}

