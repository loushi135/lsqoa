package com.xpsoft.oa.dao.system;


import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.system.UserMessage;

/**
 * 
 * @author 
 *
 */
public interface UserMessageDao extends BaseDao<UserMessage>{
	public Long  getMessageCount(Long userId); 
	
}