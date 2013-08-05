package com.xpsoft.oa.service.document;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.document.DocPrivilege;
import com.xpsoft.oa.model.system.AppUser;
import java.util.List;

public abstract interface DocPrivilegeService extends BaseService<DocPrivilege>
{
  public abstract List<DocPrivilege> getAll(DocPrivilege paramDocPrivilege, Long paramLong, PagingBean paramPagingBean);

  public abstract List<Integer> getRightsByFolder(AppUser paramAppUser, Long paramLong);

  public abstract Integer getRightsByDocument(AppUser paramAppUser, Long paramLong);
}

