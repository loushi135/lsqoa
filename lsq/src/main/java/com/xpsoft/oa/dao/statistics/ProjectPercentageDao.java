package com.xpsoft.oa.dao.statistics;


import java.math.BigDecimal;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.statistics.ProjectPercentage;

/**
 * 
 * @author 
 *
 */
public interface ProjectPercentageDao extends BaseDao<ProjectPercentage>{
	/**
	 * 获得某项目的管理费率
	 * @param proNo
	 * @return
	 */
	public BigDecimal getPercentageByProNo(String proNo);
}