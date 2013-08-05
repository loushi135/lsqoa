package com.xpsoft.oa.dao.statistics.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.LeaseHouseDao;
import com.xpsoft.oa.model.statistics.LeaseHouse;

public class LeaseHouseDaoImpl extends BaseDaoImpl<LeaseHouse> implements LeaseHouseDao{

	public LeaseHouseDaoImpl() {
		super(LeaseHouse.class);
	}

}