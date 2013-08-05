package com.xpsoft.oa.service.statistics;


import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.statistics.SalesProject;

public interface SalesProjectService extends BaseService<SalesProject>{
	public String getNewProNo(String depName);
	
	public SalesProject getByProName(String proName);
}


