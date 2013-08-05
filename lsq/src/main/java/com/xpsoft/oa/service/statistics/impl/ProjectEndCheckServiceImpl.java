package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.ProjectEndCheckDao;
import com.xpsoft.oa.model.statistics.ProjectEndCheck;
import com.xpsoft.oa.service.statistics.ProjectEndCheckService;

public class ProjectEndCheckServiceImpl extends BaseServiceImpl<ProjectEndCheck> implements ProjectEndCheckService{
	private ProjectEndCheckDao dao;
	
	public ProjectEndCheckServiceImpl(ProjectEndCheckDao dao) {
		super(dao);
		this.dao=dao;
	}

}