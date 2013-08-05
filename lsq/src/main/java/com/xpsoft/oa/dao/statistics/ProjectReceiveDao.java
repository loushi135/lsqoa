package com.xpsoft.oa.dao.statistics;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.statistics.ProjectNew;
import com.xpsoft.oa.model.statistics.ProjectReceive;
import com.xpsoft.oa.model.system.AppUser;

/**
 * 
 * @author 
 *
 */
public interface ProjectReceiveDao extends BaseDao<ProjectReceive>{
	/**
	 * 获得某项目总收款
	 * @param proNo
	 * @return
	 */
	public BigDecimal getTotalReceiveByProNo(String proNo);

	public boolean checkHasImport(String amountBig, Date receiveTime, Long id);

	public boolean checkHasImport(String pzzh, String zy, String amountBig,
			Date receiveTime, Long id);

	public ProjectReceive getByPro(ProjectNew projectNews);

	public List<ProjectReceive> getMyData(QueryFilter filter,
			AppUser currentUser);
}