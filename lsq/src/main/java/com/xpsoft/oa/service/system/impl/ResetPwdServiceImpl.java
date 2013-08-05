package com.xpsoft.oa.service.system.impl;


import javax.annotation.Resource;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.system.ResetPwdDao;
import com.xpsoft.oa.model.system.AppUser;
import com.xpsoft.oa.model.system.ResetPwd;
import com.xpsoft.oa.service.system.AppUserService;
import com.xpsoft.oa.service.system.LoginSituationService;
import com.xpsoft.oa.service.system.ResetPwdService;

public class ResetPwdServiceImpl extends BaseServiceImpl<ResetPwd> implements ResetPwdService{
	private ResetPwdDao dao;
	@Resource
	private AppUserService appUserService;
	@Resource
	private LoginSituationService loginSituationService;
	
	public ResetPwdServiceImpl(ResetPwdDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public void resetPwd(AppUser user, ResetPwd resetPwd) {
		user.setStatus((short)1);
		appUserService.save(user);
		dao.save(resetPwd);
		loginSituationService.clearHourErr(user.getUserId());
	}

}