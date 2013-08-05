package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.ProjectAuditDao;
import com.xpsoft.oa.model.statistics.ProjectAudit;
import com.xpsoft.oa.service.statistics.ProjectAuditService;

public class ProjectAuditServiceImpl extends BaseServiceImpl<ProjectAudit> implements ProjectAuditService{
	private ProjectAuditDao dao;
	
	public ProjectAuditServiceImpl(ProjectAuditDao dao) {
		super(dao);
		this.dao=dao;
	}

}