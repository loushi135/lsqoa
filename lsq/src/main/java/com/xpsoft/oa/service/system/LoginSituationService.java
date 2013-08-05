package com.xpsoft.oa.service.system;


import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.system.LoginSituation;

public interface LoginSituationService extends BaseService<LoginSituation>{
	public Long getMessageCount(Long userId);

	public void clearHourErr(Long userId);
}


