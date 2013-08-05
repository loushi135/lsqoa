package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.EntrantDao;
import com.xpsoft.oa.model.statistics.Entrant;
import com.xpsoft.oa.service.statistics.EntrantService;

public class EntrantServiceImpl extends BaseServiceImpl<Entrant> implements EntrantService{
	private EntrantDao dao;
	
	public EntrantServiceImpl(EntrantDao dao) {
		super(dao);
		this.dao=dao;
	}

}