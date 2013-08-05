package com.xpsoft.oa.dao.statistics;


import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.statistics.OtherProject;

/**
 * 
 * @author 
 *
 */
public interface OtherProjectDao extends BaseDao<OtherProject>{
	public String getNewProNo();
	
	public OtherProject getByProName(String proName);
}