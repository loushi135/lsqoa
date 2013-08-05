package com.xpsoft.oa.service.system.impl;


import java.util.Date;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.dao.system.AppUserUpdateDao;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.AppUserUpdate;
import com.xpsoft.oa.service.system.AppUserUpdateService;
import com.xpsoft.oa.service.system.LoginSituationService;

public class AppUserUpdateServiceImpl extends BaseServiceImpl<AppUserUpdate> implements AppUserUpdateService{
	private AppUserUpdateDao dao;
	
	public AppUserUpdateServiceImpl(AppUserUpdateDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public void saveAppUserUpdate(AppUser appUser) {
		// TODO Auto-generated method stub
		AppUserUpdate appUserUpdate = dao.get(appUser.getUserId());
		if(appUserUpdate==null){
			appUserUpdate = new AppUserUpdate();
			appUserUpdate.setAppUser(appUser);
		}
		appUserUpdate.setUpdateTime(new Date());
		if(appUser.getStatus()==1){
			//清除登陆失败的信息
			 LoginSituationService loginSituationService=(LoginSituationService) AppUtil.getBean("loginSituationService");
			 loginSituationService.clearHourErr(appUser.getUserId());
		}
		dao.save(appUserUpdate);
	}

}