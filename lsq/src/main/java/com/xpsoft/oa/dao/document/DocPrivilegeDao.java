package com.xpsoft.oa.dao.document;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.document.DocPrivilege;
import com.xpsoft.oa.model.system.AppUser;
import java.util.List;

public abstract interface DocPrivilegeDao extends BaseDao<DocPrivilege>
{
  public abstract List<DocPrivilege> getAll(DocPrivilege paramDocPrivilege, Long paramLong, PagingBean paramPagingBean);

  public abstract List<DocPrivilege> getByPublic(DocPrivilege paramDocPrivilege, Long paramLong);

  public abstract List<Integer> getRightsByFolder(AppUser paramAppUser, Long paramLong);

  public abstract Integer getRightsByDocument(AppUser paramAppUser, Long paramLong);

  public abstract Integer countPrivilege();
}

