package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.ProjectPercentageDao;
import com.xpsoft.oa.model.statistics.ProjectPercentage;
import com.xpsoft.oa.service.statistics.ProjectPercentageService;

public class ProjectPercentageServiceImpl extends BaseServiceImpl<ProjectPercentage> implements ProjectPercentageService{
	private ProjectPercentageDao dao;
	
	public ProjectPercentageServiceImpl(ProjectPercentageDao dao) {
		super(dao);
		this.dao=dao;
	}

}