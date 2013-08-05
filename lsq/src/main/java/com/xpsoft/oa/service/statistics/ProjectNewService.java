package com.xpsoft.oa.service.statistics;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.statistics.ProjectRelatedData;

public interface ProjectNewService extends BaseService<ProjectNew>{
	/**
	 * 计算项目资金结余  = 
	 *  收款 - 管理费（管理费率*收款） - 付款（项目下的所有的材料发包合同的累计已付款的和+其他费用）
	 * @param proNo
	 * @return
	 */
	public BigDecimal getProLeftMoney(String proNo);
	
	public List<ProjectRelatedData> getAllData(PagingBean pagingBean,Map<String,String> dataMap);

	public ProjectNew getByProName(String trim);
	
	public ProjectNew getByProNo(String proNo);
	/**
	 * xls导入更新开工备案信息
	 * @param attachIds
	 * @return
	 */
	public String importsUpdate(String attachIds);
}


