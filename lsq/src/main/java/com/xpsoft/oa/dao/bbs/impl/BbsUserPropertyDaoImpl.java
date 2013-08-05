package com.xpsoft.oa.dao.bbs.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.bbs.BbsUserPropertyDao;
import com.xpsoft.oa.model.bbs.BbsUserProperty;

public class BbsUserPropertyDaoImpl extends BaseDaoImpl<BbsUserProperty> implements BbsUserPropertyDao{

	public BbsUserPropertyDaoImpl() {
		super(BbsUserProperty.class);
	}

}