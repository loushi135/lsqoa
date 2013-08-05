package com.xpsoft.oa.service.system.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.system.LoginSituationDao;
import com.xpsoft.oa.model.system.LoginSituation;
import com.xpsoft.oa.service.system.LoginSituationService;

public class LoginSituationServiceImpl extends BaseServiceImpl<LoginSituation> implements LoginSituationService{
	private LoginSituationDao dao;
	
	public LoginSituationServiceImpl(LoginSituationDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public Long getMessageCount(Long userId) {
		return dao.getMessageCount(userId);
	}

	@Override
	public void clearHourErr(Long userId) {
		dao.clearHourErr(userId);
	}
}