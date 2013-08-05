package com.xpsoft.oa.service.statistics;


import java.math.BigDecimal;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.statistics.PaymentList;

public interface PaymentListService extends BaseService<PaymentList>{
	public BigDecimal getOwedSum(String proId,String userId);
}


