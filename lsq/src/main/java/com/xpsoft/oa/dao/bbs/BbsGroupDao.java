package com.xpsoft.oa.dao.bbs;


import java.util.List;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.bbs.BbsGroup;

public interface BbsGroupDao extends BaseDao<BbsGroup>{
	 public List<BbsGroup> findByParentId(Long id);
	 public List<BbsGroup> findByGroupName(String paramString);
	
}