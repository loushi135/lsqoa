package com.xpsoft.oa.service.system;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.system.Dictionary;
import java.util.List;

public abstract interface DictionaryService extends BaseService<Dictionary>
{
  public abstract List<String> getAllItems();

  public abstract List<String> getAllByItemName(String paramString);
}

