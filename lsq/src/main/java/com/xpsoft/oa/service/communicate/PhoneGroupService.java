package com.xpsoft.oa.service.communicate;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.communicate.PhoneGroup;
import java.util.List;

public abstract interface PhoneGroupService extends BaseService<PhoneGroup>
{
  public abstract Integer findLastSn(Long paramLong);

  public abstract PhoneGroup findBySn(Integer paramInteger, Long paramLong);

  public abstract List<PhoneGroup> findBySnUp(Integer paramInteger, Long paramLong);

  public abstract List<PhoneGroup> findBySnDown(Integer paramInteger, Long paramLong);

  public abstract List<PhoneGroup> getAll(Long paramLong);
}
