package com.xpsoft.oa.dao.hrm;


import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.hrm.TrainSurveyUser;

/**
 * 
 * @author 
 *
 */
public interface TrainSurveyUserDao extends BaseDao<TrainSurveyUser>{
	public void delBySql(String sql,Object[] obj);
}