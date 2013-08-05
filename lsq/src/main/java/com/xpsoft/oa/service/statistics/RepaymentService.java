package com.xpsoft.oa.service.statistics;


import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.statistics.Repayment;

public interface RepaymentService extends BaseService<Repayment>{
	public void saveRepayment(Repayment repayment);
}


