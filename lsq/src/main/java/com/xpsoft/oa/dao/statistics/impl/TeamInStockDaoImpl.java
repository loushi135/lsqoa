package com.xpsoft.oa.dao.statistics.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.TeamInStockDao;
import com.xpsoft.oa.model.statistics.TeamInStock;

public class TeamInStockDaoImpl extends BaseDaoImpl<TeamInStock> implements TeamInStockDao{

	public TeamInStockDaoImpl() {
		super(TeamInStock.class);
	}

}