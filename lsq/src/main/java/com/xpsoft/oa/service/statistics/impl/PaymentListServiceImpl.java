package com.xpsoft.oa.service.statistics.impl;


import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.oa.dao.statistics.PaymentListDao;
import com.xpsoft.oa.dao.statistics.RepaymentDao;
import com.xpsoft.oa.model.statistics.PaymentList;
import com.xpsoft.oa.model.statistics.Repayment;
import com.xpsoft.oa.service.statistics.PaymentListService;

public class PaymentListServiceImpl extends BaseServiceImpl<PaymentList> implements PaymentListService{
	private PaymentListDao dao;
	@Autowired
	private RepaymentDao repaymentDao;
	public PaymentListServiceImpl(PaymentListDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public BigDecimal getOwedSum(String proId,String userId) {
		// TODO Auto-generated method stub
		Long applyUserId = ContextUtil.getCurrentUserId();
		if(StringUtils.isNotBlank(userId)){
			applyUserId = Long.valueOf(userId);
		}
		if(StringUtils.isNotBlank(proId)){//项目加人
			List<PaymentList> paymentList = dao.findByHql("from PaymentList where project.id=? and user.userId=? and payOption='工程'", 
					new Object[]{new Long(proId),applyUserId});
			BigDecimal boorAmount = new BigDecimal(0);
			BigDecimal returnAmount = new BigDecimal(0);
			for(PaymentList payment:paymentList){
				boorAmount = boorAmount.add(payment.getPaymentSumSmall());
				List<Repayment> repaymentList = repaymentDao.findByHql("from Repayment where payment.id=?", 
						new Object[]{payment.getId()});
				for(Repayment repayment:repaymentList){
					returnAmount = returnAmount.add(repayment.getReturnAmount());
				}
			}
			return boorAmount.subtract(returnAmount);
		}else{// 个人暂支
			List<PaymentList> paymentList = dao.findByHql("from PaymentList where user.userId=? and payOption='其他'", 
					new Object[]{applyUserId});
			BigDecimal boorAmount = new BigDecimal(0);
			BigDecimal returnAmount = new BigDecimal(0);
			for(PaymentList payment:paymentList){
				boorAmount = boorAmount.add(payment.getPaymentSumSmall());
				List<Repayment> repaymentList = repaymentDao.findByHql("from Repayment where payment.id=?", 
						new Object[]{payment.getId()});
				for(Repayment repayment:repaymentList){
					returnAmount = returnAmount.add(repayment.getReturnAmount());
				}
			}
			return boorAmount.subtract(returnAmount);
		}
	}

}