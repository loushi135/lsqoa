package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.BankpayapplyotherDao;
import com.xpsoft.oa.model.statistics.Bankpayapplyother;
import com.xpsoft.oa.service.statistics.BankpayapplyotherService;

public class BankpayapplyotherServiceImpl extends BaseServiceImpl<Bankpayapplyother> implements BankpayapplyotherService{
	private BankpayapplyotherDao dao;
	
	public BankpayapplyotherServiceImpl(BankpayapplyotherDao dao) {
		super(dao);
		this.dao=dao;
	}

}