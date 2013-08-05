package com.xpsoft.oa.service.system;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.system.UserFlowconfig;




public interface UserFlowconfigService extends BaseService<UserFlowconfig>{
	
	public UserFlowconfig getByUserId(Long userId);
}


