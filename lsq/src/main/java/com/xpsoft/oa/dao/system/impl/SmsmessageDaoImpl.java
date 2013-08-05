package com.xpsoft.oa.dao.system.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.system.SmsmessageDao;
import com.xpsoft.oa.model.system.Smsmessage;

public class SmsmessageDaoImpl extends BaseDaoImpl<Smsmessage> implements SmsmessageDao{

	public SmsmessageDaoImpl() {
		super(Smsmessage.class);
	}

}