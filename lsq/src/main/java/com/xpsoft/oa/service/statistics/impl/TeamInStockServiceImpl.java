package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.TeamInStockDao;
import com.xpsoft.oa.model.statistics.TeamInStock;
import com.xpsoft.oa.service.statistics.TeamInStockService;

public class TeamInStockServiceImpl extends BaseServiceImpl<TeamInStock> implements TeamInStockService{
	private TeamInStockDao dao;
	
	public TeamInStockServiceImpl(TeamInStockDao dao) {
		super(dao);
		this.dao=dao;
	}

}