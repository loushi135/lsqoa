package com.xpsoft.oa.service.statistics;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.statistics.ProjectReceive;
import com.xpsoft.oa.model.system.AppUser;

public interface ProjectReceiveService extends BaseService<ProjectReceive>{
	/**
	 * 获得某项目总收款
	 * @param proNo
	 * @return
	 */
	public BigDecimal getTotalReceiveByProNo(String proNo);
	/**
	 * 导入收款信息
	 * @param project
	 * @param attachIds 文件fliedIDs
	 * @return
	 */
	public String imports(ProjectNew project, String attachIds);
	/**
	 * 检测数据是否已导入
	 * @param amountBig 金额
	 * @param receiveTime	收款时间
	 * @param id	所属工程ID
	 * @return
	 */
	public boolean isHasImport(String amountBig, Date receiveTime, Long id) ;
	/**
	 * 按项目查询收款信息
	 * @param projectNews
	 * @return
	 */
	public ProjectReceive getByPro(ProjectNew projectNews);
	/**
	 * 获取我的收款信息
	 * @param filter
	 * @param currentUser
	 * @return
	 */
	public List<ProjectReceive> getMyData(QueryFilter filter,
			AppUser currentUser);
}


