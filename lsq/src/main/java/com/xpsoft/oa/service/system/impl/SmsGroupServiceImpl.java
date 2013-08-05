package com.xpsoft.oa.service.system.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.system.SmsGroupDao;
import com.xpsoft.oa.model.system.SmsGroup;
import com.xpsoft.oa.service.system.SmsGroupService;

public class SmsGroupServiceImpl extends BaseServiceImpl<SmsGroup> implements SmsGroupService{
	private SmsGroupDao dao;
	
	public SmsGroupServiceImpl(SmsGroupDao dao) {
		super(dao);
		this.dao=dao;
	}

}