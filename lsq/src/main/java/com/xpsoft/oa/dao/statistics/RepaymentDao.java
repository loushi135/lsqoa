package com.xpsoft.oa.dao.statistics;


import java.math.BigDecimal;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.statistics.Repayment;

/**
 * 
 * @author 
 *
 */
public interface RepaymentDao extends BaseDao<Repayment>{
   public BigDecimal getTotalReturn(Long paymentId);
}