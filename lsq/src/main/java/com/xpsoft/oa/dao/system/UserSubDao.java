package com.xpsoft.oa.dao.system;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.system.UserSub;
import java.util.List;

public abstract interface UserSubDao extends BaseDao<UserSub>
{
  public abstract List<Long> upUser(Long paramLong);

  public abstract List<Long> subUsers(Long paramLong);
}

