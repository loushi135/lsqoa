package com.xpsoft.oa.dao.statistics.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.ProjectAuditDao;
import com.xpsoft.oa.model.statistics.ProjectAudit;

public class ProjectAuditDaoImpl extends BaseDaoImpl<ProjectAudit> implements ProjectAuditDao{

	public ProjectAuditDaoImpl() {
		super(ProjectAudit.class);
	}

}