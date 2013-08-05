package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.ProjectSealRecycleDao;
import com.xpsoft.oa.model.statistics.ProjectSealRecycle;
import com.xpsoft.oa.service.statistics.ProjectSealRecycleService;

public class ProjectSealRecycleServiceImpl extends BaseServiceImpl<ProjectSealRecycle> implements ProjectSealRecycleService{
	private ProjectSealRecycleDao dao;
	
	public ProjectSealRecycleServiceImpl(ProjectSealRecycleDao dao) {
		super(dao);
		this.dao=dao;
	}

}