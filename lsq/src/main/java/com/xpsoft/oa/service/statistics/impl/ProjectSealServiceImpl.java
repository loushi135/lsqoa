package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.ProjectSealDao;
import com.xpsoft.oa.model.statistics.ProjectSeal;
import com.xpsoft.oa.service.statistics.ProjectSealService;

public class ProjectSealServiceImpl extends BaseServiceImpl<ProjectSeal> implements ProjectSealService{
	private ProjectSealDao dao;
	
	public ProjectSealServiceImpl(ProjectSealDao dao) {
		super(dao);
		this.dao=dao;
	}

}