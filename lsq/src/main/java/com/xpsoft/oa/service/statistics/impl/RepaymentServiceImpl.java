package com.xpsoft.oa.service.statistics.impl;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.PaymentListDao;
import com.xpsoft.oa.dao.statistics.RepaymentDao;
import com.xpsoft.oa.model.statistics.PaymentList;
import com.xpsoft.oa.model.statistics.Repayment;
import com.xpsoft.oa.service.statistics.RepaymentService;

public class RepaymentServiceImpl extends BaseServiceImpl<Repayment> implements RepaymentService{
	private RepaymentDao dao;
	@Autowired
	private PaymentListDao paymentListDao;
	public RepaymentServiceImpl(RepaymentDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public void saveRepayment(Repayment repayment) {
		// TODO Auto-generated method stub
		dao.save(repayment);
		PaymentList paymentList = paymentListDao.get(repayment.getPayment().getId());
		BigDecimal totalReturn = dao.getTotalReturn(paymentList.getId());
		paymentList.setOwedSum(paymentList.getPaymentSumSmall().subtract(totalReturn).toString());
	}

	
}