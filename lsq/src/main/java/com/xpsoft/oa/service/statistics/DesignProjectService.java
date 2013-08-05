package com.xpsoft.oa.service.statistics;


import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.statistics.DesignProject;
import com.xpsoft.oa.model.statistics.SalesProject;

public interface DesignProjectService extends BaseService<DesignProject>{
	public String getNewProNo();
	
	public DesignProject getByProName(String proName);
}


