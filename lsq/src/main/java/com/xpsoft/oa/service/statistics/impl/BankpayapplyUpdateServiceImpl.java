package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.BankpayapplyUpdateDao;
import com.xpsoft.oa.model.statistics.BankpayapplyUpdate;
import com.xpsoft.oa.service.statistics.BankpayapplyUpdateService;

public class BankpayapplyUpdateServiceImpl extends BaseServiceImpl<BankpayapplyUpdate> implements BankpayapplyUpdateService{
	private BankpayapplyUpdateDao dao;
	
	public BankpayapplyUpdateServiceImpl(BankpayapplyUpdateDao dao) {
		super(dao);
		this.dao=dao;
	}

}