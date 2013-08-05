package com.xpsoft.oa.dao.admin;

import java.util.List;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.admin.Meeting;



/**
 * 
 * @juql
 *
 */
public interface MeetingDao extends BaseDao<Meeting>{
	
	public List<Meeting> findByHql(final String hql);
	
}