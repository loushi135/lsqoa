package com.xpsoft.oa.service.statistics;


import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.statistics.LocalProductApply;

public interface LocalProductApplyService extends BaseService<LocalProductApply>{
	
	public void saveLocalProductApply(LocalProductApply localProductApply,String dataList);
}


