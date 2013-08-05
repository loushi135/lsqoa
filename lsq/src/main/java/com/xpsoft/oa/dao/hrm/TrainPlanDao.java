package com.xpsoft.oa.dao.hrm;


import java.util.List;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.core.web.paging.PagingBean;
import com.xpsoft.oa.model.hrm.TrainPlan;

/**
 * 
 * @author 
 *
 */
public interface TrainPlanDao extends BaseDao<TrainPlan>{
	public List<TrainPlan> getListNew();
	public int queryCount(String year);
}