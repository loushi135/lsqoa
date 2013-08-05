package com.xpsoft.oa.dao.statistics;


import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.statistics.Paybase;

/**
 * 
 * @author 
 *
 */
public interface PaybaseDao extends BaseDao<Paybase>{
	/**
	 * 获取此材料发包合同下所有付款基数和
	 * @param materialId
	 * @return
	 */
	public BigDecimal getTotalByMaterialId(Long materialId);
	
	/**
	 * 获取此项目下所有付款基数和
	 * @param proNo
	 * @return
	 */
	public BigDecimal getTotalByProNo(String proNo);
	/**
	 * 获取此项目对应供应商下所有付款基数和
	 * @param proId
	 * @param suppId
	 * @return
	 */
	public BigDecimal getTotalByProAndSupp(Long proId, Long suppId);

	public List<Paybase> getByProAndSupp(Long proId, Long suppliersId);

	public List<Paybase> getByProName(String proName);

	public List<Paybase> getAll(DetachedCriteria criteria);

	public List<Paybase> getByProName(String proName, int pageFirst,
			int pageSize);

	public int getCountByProName(String proName);
}