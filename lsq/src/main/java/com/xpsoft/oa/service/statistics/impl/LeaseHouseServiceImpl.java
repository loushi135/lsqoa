package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.LeaseHouseDao;
import com.xpsoft.oa.model.statistics.LeaseHouse;
import com.xpsoft.oa.service.statistics.LeaseHouseService;

public class LeaseHouseServiceImpl extends BaseServiceImpl<LeaseHouse> implements LeaseHouseService{
	private LeaseHouseDao dao;
	
	public LeaseHouseServiceImpl(LeaseHouseDao dao) {
		super(dao);
		this.dao=dao;
	}

}