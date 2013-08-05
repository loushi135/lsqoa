package com.xpsoft.oa.dao.statistics.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.SignApplyDao;
import com.xpsoft.oa.model.statistics.SignApply;

public class SignApplyDaoImpl extends BaseDaoImpl<SignApply> implements SignApplyDao{

	public SignApplyDaoImpl() {
		super(SignApply.class);
	}

}