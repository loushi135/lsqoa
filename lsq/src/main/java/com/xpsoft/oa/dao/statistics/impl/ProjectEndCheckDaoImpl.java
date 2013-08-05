package com.xpsoft.oa.dao.statistics.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.ProjectEndCheckDao;
import com.xpsoft.oa.model.statistics.ProjectEndCheck;

public class ProjectEndCheckDaoImpl extends BaseDaoImpl<ProjectEndCheck> implements ProjectEndCheckDao{

	public ProjectEndCheckDaoImpl() {
		super(ProjectEndCheck.class);
	}

}