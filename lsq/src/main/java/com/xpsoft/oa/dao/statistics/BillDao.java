package com.xpsoft.oa.dao.statistics;


import java.math.BigDecimal;
import java.util.List;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.statistics.Bill;

/**
 * 
 * @author 
 *
 */
public interface BillDao extends BaseDao<Bill>{
	public BigDecimal getTotalByMaterialId(Long materialId);

	public BigDecimal getTotalByProAndSupp(Long proId, Long suppId);

	public List<Bill> getByProAndSupp(Long id, Long suppliersId);
}