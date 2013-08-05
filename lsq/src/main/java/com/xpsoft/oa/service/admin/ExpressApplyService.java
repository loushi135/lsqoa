package com.xpsoft.oa.service.admin;


import java.util.Map;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.admin.ExpressApply;

public interface ExpressApplyService extends BaseService<ExpressApply>{
	
	public void export(Map<String,Object> dataMap)throws Exception;
}


