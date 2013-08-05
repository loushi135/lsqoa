package com.xpsoft.oa.dao.communicate;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.communicate.PhoneGroup;
import java.util.List;

public abstract interface PhoneGroupDao extends BaseDao<PhoneGroup>
{
  public abstract Integer findLastSn(Long paramLong);

  public abstract PhoneGroup findBySn(Integer paramInteger, Long paramLong);

  public abstract List<PhoneGroup> findBySnUp(Integer paramInteger, Long paramLong);

  public abstract List<PhoneGroup> findBySnDown(Integer paramInteger, Long paramLong);

  public abstract List<PhoneGroup> getAll(Long paramLong);
}

