package com.xpsoft.oa.dao.statistics;


import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.statistics.SalesProject;

/**
 * 
 * @author 
 *
 */
public interface SalesProjectDao extends BaseDao<SalesProject>{
	public String getNewProNo(String depName);
	
	public SalesProject getByProName(String proName);
}