package com.xpsoft.oa.dao.statistics;


import java.math.BigDecimal;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.statistics.ProjectRepay;

/**
 * 
 * @author 
 *
 */
public interface ProjectRepayDao extends BaseDao<ProjectRepay>{
	/**
	 * 获取某项目已报销费用
	 * @param proNo
	 * @return
	 */
	public BigDecimal getRePayedByProNo(String proNo);
}