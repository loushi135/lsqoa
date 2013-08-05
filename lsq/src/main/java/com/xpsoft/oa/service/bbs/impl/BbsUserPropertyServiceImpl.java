package com.xpsoft.oa.service.bbs.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.bbs.BbsUserPropertyDao;
import com.xpsoft.oa.model.bbs.BbsUserProperty;
import com.xpsoft.oa.service.bbs.BbsUserPropertyService;

public class BbsUserPropertyServiceImpl extends BaseServiceImpl<BbsUserProperty> implements BbsUserPropertyService{
	private BbsUserPropertyDao dao;
	
	public BbsUserPropertyServiceImpl(BbsUserPropertyDao dao) {
		super(dao);
		this.dao=dao;
	}

}