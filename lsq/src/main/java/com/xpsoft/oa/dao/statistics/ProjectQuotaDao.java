package com.xpsoft.oa.dao.statistics;


import java.math.BigDecimal;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.statistics.ProjectQuota;

/**
 * 
 * @author 
 *
 */
public interface ProjectQuotaDao extends BaseDao<ProjectQuota>{
	/**
	 * 获取垫资审批额度
	 * @param proNo
	 */
	public BigDecimal getQuotaByProNo(String proNo);
}