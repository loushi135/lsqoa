package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.ProjectQuotaDao;
import com.xpsoft.oa.model.statistics.ProjectQuota;
import com.xpsoft.oa.service.statistics.ProjectQuotaService;

public class ProjectQuotaServiceImpl extends BaseServiceImpl<ProjectQuota> implements ProjectQuotaService{
	private ProjectQuotaDao dao;
	
	public ProjectQuotaServiceImpl(ProjectQuotaDao dao) {
		super(dao);
		this.dao=dao;
	}

}