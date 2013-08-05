package com.xpsoft.oa.dao.system.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.system.AnnounceDao;
import com.xpsoft.oa.model.system.Announce;

public class AnnounceDaoImpl extends BaseDaoImpl<Announce> implements AnnounceDao{

	public AnnounceDaoImpl() {
		super(Announce.class);
	}

}