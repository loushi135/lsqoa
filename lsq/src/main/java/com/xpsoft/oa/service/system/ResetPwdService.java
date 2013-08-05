package com.xpsoft.oa.service.system;


import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.ResetPwd;

public interface ResetPwdService extends BaseService<ResetPwd>{
	public void resetPwd(AppUser user,ResetPwd resetPwd);
}


