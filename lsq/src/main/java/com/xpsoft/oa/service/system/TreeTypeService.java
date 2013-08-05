package com.xpsoft.oa.service.system;


import java.util.List;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.system.TreeType;

public interface TreeTypeService extends BaseService<TreeType>{
	public List<TreeType> findByParentId(long id);
	
	public List<TreeType> findByParentIdAndClassName(Long parentId,String className);
}


