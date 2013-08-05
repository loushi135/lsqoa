package com.xpsoft.oa.service.system.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.system.UserLogDao;
import com.xpsoft.oa.model.system.UserLog;
import com.xpsoft.oa.service.system.UserLogService;

public class UserLogServiceImpl extends BaseServiceImpl<UserLog> implements UserLogService{
	private UserLogDao dao;
	
	public UserLogServiceImpl(UserLogDao dao) {
		super(dao);
		this.dao=dao;
	}

}