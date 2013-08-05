package com.xpsoft.oa.dao.system.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.system.UserLogDao;
import com.xpsoft.oa.model.system.UserLog;

public class UserLogDaoImpl extends BaseDaoImpl<UserLog> implements UserLogDao{

	public UserLogDaoImpl() {
		super(UserLog.class);
	}

}