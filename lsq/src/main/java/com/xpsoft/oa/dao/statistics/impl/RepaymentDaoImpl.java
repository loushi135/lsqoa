package com.xpsoft.oa.dao.statistics.impl;


import java.math.BigDecimal;

import org.hibernate.Query;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.RepaymentDao;
import com.xpsoft.oa.model.statistics.Repayment;

public class RepaymentDaoImpl extends BaseDaoImpl<Repayment> implements RepaymentDao{

	public RepaymentDaoImpl() {
		super(Repayment.class);
	}

	@Override
	public BigDecimal getTotalReturn(Long paymentId) {
		// TODO Auto-generated method stub
		String hql = "select sum(returnAmount) from Repayment where payment.id=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, paymentId);
		BigDecimal temp = (BigDecimal)query.uniqueResult();
		if(temp==null){
			temp = new BigDecimal(0);
		}
		return temp;
	}

}