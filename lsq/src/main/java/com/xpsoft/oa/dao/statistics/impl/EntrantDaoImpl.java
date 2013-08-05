package com.xpsoft.oa.dao.statistics.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.EntrantDao;
import com.xpsoft.oa.model.statistics.Entrant;

public class EntrantDaoImpl extends BaseDaoImpl<Entrant> implements EntrantDao{

	public EntrantDaoImpl() {
		super(Entrant.class);
	}

}