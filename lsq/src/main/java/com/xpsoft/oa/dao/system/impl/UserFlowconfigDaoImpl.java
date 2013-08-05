package com.xpsoft.oa.dao.system.impl;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.system.UserFlowconfigDao;
import com.xpsoft.oa.model.system.UserFlowconfig;




public class UserFlowconfigDaoImpl extends BaseDaoImpl<UserFlowconfig> implements UserFlowconfigDao{

	public UserFlowconfigDaoImpl() {
		super(UserFlowconfig.class);
	}

}