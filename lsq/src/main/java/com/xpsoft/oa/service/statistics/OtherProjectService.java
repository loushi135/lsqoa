package com.xpsoft.oa.service.statistics;


import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.statistics.OtherProject;

public interface OtherProjectService extends BaseService<OtherProject>{
	public String getNewProNo();
	
	public OtherProject getByProName(String proName);
}


