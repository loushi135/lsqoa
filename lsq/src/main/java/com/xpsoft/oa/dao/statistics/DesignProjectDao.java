package com.xpsoft.oa.dao.statistics;


import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.statistics.DesignProject;
import com.xpsoft.oa.model.statistics.SalesProject;

/**
 * 
 * @author 
 *
 */
public interface DesignProjectDao extends BaseDao<DesignProject>{
	
	public String getNewProNo();
	
	public DesignProject getByProName(String proName);
}