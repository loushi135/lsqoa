package com.xpsoft.oa.dao.system.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.system.ResetPwdDao;
import com.xpsoft.oa.model.system.ResetPwd;

public class ResetPwdDaoImpl extends BaseDaoImpl<ResetPwd> implements ResetPwdDao{

	public ResetPwdDaoImpl() {
		super(ResetPwd.class);
	}

}