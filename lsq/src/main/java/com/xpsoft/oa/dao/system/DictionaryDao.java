package com.xpsoft.oa.dao.system;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.system.Dictionary;
import java.util.List;

public abstract interface DictionaryDao extends BaseDao<Dictionary>
{
  public abstract List<String> getAllItems();

  public abstract List<String> getAllByItemName(String paramString);
}

