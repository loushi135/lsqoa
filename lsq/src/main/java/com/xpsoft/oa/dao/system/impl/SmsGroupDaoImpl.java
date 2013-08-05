package com.xpsoft.oa.dao.system.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.system.SmsGroupDao;
import com.xpsoft.oa.model.system.SmsGroup;

public class SmsGroupDaoImpl extends BaseDaoImpl<SmsGroup> implements SmsGroupDao{

	public SmsGroupDaoImpl() {
		super(SmsGroup.class);
	}

}