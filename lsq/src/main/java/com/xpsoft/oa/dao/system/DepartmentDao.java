package com.xpsoft.oa.dao.system;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.system.Department;
import java.util.List;

public abstract interface DepartmentDao extends BaseDao<Department>
{
  public abstract List<Department> findByParentId(Long paramLong);
  
  public abstract List<Department> findByParentIdAndStatus(Long paramLong,Integer status);

  public abstract List<Department> findByVo(Department paramDepartment, PagingBean paramPagingBean);

  public abstract List<Department> findByDepName(String paramString);

  public abstract List<Department> findByPath(String paramString);
}

