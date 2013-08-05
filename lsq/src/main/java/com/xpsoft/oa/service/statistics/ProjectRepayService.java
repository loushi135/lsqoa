package com.xpsoft.oa.service.statistics;


import java.math.BigDecimal;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.statistics.ProjectRepay;

public interface ProjectRepayService extends BaseService<ProjectRepay>{
	/**
	 * 获取某项目已报销费用
	 * @param proNo
	 * @return
	 */
	public BigDecimal getRePayedByProNo(String proNo);

	/**
	 * 导入报销
	 * @param projectNew
	 * @param attachIds
	 * @return
	 */
	public String imports(ProjectNew projectNew, String attachIds);
	
	/**
	 * 导入报销
	 * @param attachIds
	 * @return
	 */
	public String imports(String attachIds);
}


