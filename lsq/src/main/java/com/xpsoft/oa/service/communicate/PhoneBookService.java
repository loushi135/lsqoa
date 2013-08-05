package com.xpsoft.oa.service.communicate;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.communicate.PhoneBook;
import java.util.List;

public abstract interface PhoneBookService extends BaseService<PhoneBook>
{
  public abstract List<PhoneBook> sharedPhoneBooks(String paramString1, String paramString2, PagingBean paramPagingBean);
}

