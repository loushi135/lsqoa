package com.xpsoft.oa.service.statistics;


import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.statistics.SignApply;

public interface SignApplyService extends BaseService<SignApply>{
	
	public String getMaxSignNo();
	
}


