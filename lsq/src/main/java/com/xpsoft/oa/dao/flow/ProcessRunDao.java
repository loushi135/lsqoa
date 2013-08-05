package com.xpsoft.oa.dao.flow;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.flow.ProcessRun;
import java.util.List;

public abstract interface ProcessRunDao extends BaseDao<ProcessRun>
{
  public abstract ProcessRun getByPiId(String paramString);

  public abstract List<ProcessRun> getByDefId(Long paramLong, PagingBean paramPagingBean);

  public abstract List<ProcessRun> getByUserIdSubject(Long paramLong, String paramString, PagingBean paramPagingBean);
}

