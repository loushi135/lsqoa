package com.xpsoft.oa.dao.system;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.system.Diary;
import java.util.List;

public abstract interface DiaryDao extends BaseDao<Diary>
{
  public abstract List<Diary> getSubDiary(String paramString, PagingBean paramPagingBean);
}

