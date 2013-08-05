package com.xpsoft.oa.service.statistics.impl;


import java.util.Date;

import javax.annotation.Resource;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.oa.dao.statistics.BillAdjustDao;
import com.xpsoft.oa.dao.statistics.BillDao;
import com.xpsoft.oa.model.statistics.Bill;
import com.xpsoft.oa.model.statistics.BillAdjust;
import com.xpsoft.oa.service.statistics.BillAdjustService;

public class BillAdjustServiceImpl extends BaseServiceImpl<BillAdjust> implements BillAdjustService{
	private BillAdjustDao dao;
	@Resource
	private BillDao billDao;
	
	public BillAdjustServiceImpl(BillAdjustDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public void saveBillAndBillAdjust(BillAdjust billAdjust) {
		billAdjust.setAdjustDate(new Date());
		billAdjust.setAdjustUser(ContextUtil.getCurrentUser());
		dao.save(billAdjust);
		Bill bill = billDao.get(billAdjust.getBill().getId());
		bill.setAmount(billAdjust.getNewAmoumt());
		bill.setAmountBig(billAdjust.getNewAmountBig());
		billDao.save(bill);
	}

}