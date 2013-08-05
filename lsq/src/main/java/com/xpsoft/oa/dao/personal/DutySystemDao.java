package com.xpsoft.oa.dao.personal;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.personal.DutySystem;
import java.util.List;

public abstract interface DutySystemDao extends BaseDao<DutySystem>
{
  public abstract void updateForNotDefult();

  public abstract List<DutySystem> getDefaultDutySystem();
}

