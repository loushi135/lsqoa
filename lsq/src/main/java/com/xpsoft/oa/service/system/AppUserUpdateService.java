package com.xpsoft.oa.service.system;


import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.AppUserUpdate;

public interface AppUserUpdateService extends BaseService<AppUserUpdate>{
	
	public void saveAppUserUpdate(AppUser appUser);
}


