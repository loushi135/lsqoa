package com.xpsoft.oa.service.system;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.system.Diary;
import java.util.List;

public abstract interface DiaryService extends BaseService<Diary>
{
  public abstract List<Diary> getAllBySn(PagingBean paramPagingBean);

  public abstract List<Diary> getSubDiary(String paramString, PagingBean paramPagingBean);
}

