package com.xpsoft.oa.dao.system;


import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.system.LoginSituation;

/**
 * 
 * @author 
 *
 */
public interface LoginSituationDao extends BaseDao<LoginSituation>{
	public Long  getMessageCount(Long userId);

	public void clearHourErr(Long userId); 
	
}