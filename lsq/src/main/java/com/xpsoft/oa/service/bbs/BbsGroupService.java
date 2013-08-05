package com.xpsoft.oa.service.bbs;


import java.util.List;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.bbs.BbsGroup;

public interface BbsGroupService extends BaseService<BbsGroup>{

 public List<BbsGroup> findByParentId(Long id);
 public List<BbsGroup> findByGroupName(String paramString);
}


