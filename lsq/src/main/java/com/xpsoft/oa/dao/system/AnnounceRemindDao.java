package com.xpsoft.oa.dao.system;


import java.util.List;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.system.AnnounceRemind;

/**
 * 
 * @author 
 *
 */
public interface AnnounceRemindDao extends BaseDao<AnnounceRemind>{
	public void deleteByAnnounceId(long announceId);
	public List findBySql(String sql);
}