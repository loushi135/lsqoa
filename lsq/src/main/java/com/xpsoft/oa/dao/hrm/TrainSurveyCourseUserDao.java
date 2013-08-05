package com.xpsoft.oa.dao.hrm;


import java.util.List;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.hrm.TrainSurveyCourseUser;

/**
 * 
 * @author 
 *
 */
public interface TrainSurveyCourseUserDao extends BaseDao<TrainSurveyCourseUser>{
	public void delBySql(String sql,Object[] obj);
	public List<Object> queryBySql(String sql,Object[] obj);
}