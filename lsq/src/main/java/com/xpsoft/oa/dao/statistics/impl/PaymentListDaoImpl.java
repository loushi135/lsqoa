package com.xpsoft.oa.dao.statistics.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.PaymentListDao;
import com.xpsoft.oa.model.statistics.PaymentList;

public class PaymentListDaoImpl extends BaseDaoImpl<PaymentList> implements PaymentListDao{

	public PaymentListDaoImpl() {
		super(PaymentList.class);
	}

}