package com.xpsoft.oa.service.info;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.info.ShortMessage;
import java.util.Date;
import java.util.List;

public abstract interface ShortMessageService extends BaseService<ShortMessage>
{
  public abstract List<ShortMessage> findAll(Long paramLong, PagingBean paramPagingBean);

  public abstract List<ShortMessage> findByUser(Long paramLong);

  public abstract List searchShortMessage(Long paramLong, ShortMessage paramShortMessage, Date paramDate1, Date paramDate2, PagingBean paramPagingBean);

  public abstract ShortMessage save(Long paramLong, String paramString1, String paramString2, Short paramShort);
}

