package com.xpsoft.oa.service.admin;

import java.util.List;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.admin.AssetsApply;

public interface AssetsApplyService extends BaseService<AssetsApply>{

	List<AssetsApply> getAll2000(QueryFilter filter);
	
}


