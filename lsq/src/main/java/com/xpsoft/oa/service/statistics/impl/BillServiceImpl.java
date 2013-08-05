package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.BillDao;
import com.xpsoft.oa.model.statistics.Bill;
import com.xpsoft.oa.service.statistics.BillService;

public class BillServiceImpl extends BaseServiceImpl<Bill> implements BillService{
	private BillDao dao;
	
	public BillServiceImpl(BillDao dao) {
		super(dao);
		this.dao=dao;
	}

}