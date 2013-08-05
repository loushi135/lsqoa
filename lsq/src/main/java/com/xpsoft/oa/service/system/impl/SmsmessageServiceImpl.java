package com.xpsoft.oa.service.system.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.system.SmsmessageDao;
import com.xpsoft.oa.model.system.Smsmessage;
import com.xpsoft.oa.service.system.SmsmessageService;

public class SmsmessageServiceImpl extends BaseServiceImpl<Smsmessage> implements SmsmessageService{
	private SmsmessageDao dao;
	
	public SmsmessageServiceImpl(SmsmessageDao dao) {
		super(dao);
		this.dao=dao;
	}

}