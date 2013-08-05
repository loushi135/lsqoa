package com.xpsoft.oa.service.statistics;


import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.statistics.Paybase;

public interface PaybaseService extends BaseService<Paybase>{
	public BigDecimal getTotalByMaterialId(Long materialId);
	/**
	 * 获取此项目对应供应商下所有付款基数和
	 * @param proId
	 * @param suppId
	 * @return
	 */
	public BigDecimal getTotalByProAndSupp(Long proId, Long suppId);
	/**
	 * 
	 * @param proName 项目名称
	 * @return 
	 */
	public List<Paybase> getByProName(String proName);
	public List<Paybase> getAll(DetachedCriteria criteria);
	public List<Paybase> getByProName(String proName, int pageFirst, int pageSize);
	public int getCountByProName(String proName);
}


