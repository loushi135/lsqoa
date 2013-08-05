package com.xpsoft.oa.dao.bbs;


import java.util.Date;
import java.util.List;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.bbs.BbsTopic;

/**
 * 
 * @author 
 *
 */
public interface BbsTopicDao extends BaseDao<BbsTopic>{
	public void topList(String ids[]);
	public abstract Long getMaxId();
	
}