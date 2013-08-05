package com.xpsoft.oa.dao.system.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.system.AppUserUpdateDao;
import com.xpsoft.oa.model.system.AppUserUpdate;

public class AppUserUpdateDaoImpl extends BaseDaoImpl<AppUserUpdate> implements AppUserUpdateDao{

	public AppUserUpdateDaoImpl() {
		super(AppUserUpdate.class);
	}

}