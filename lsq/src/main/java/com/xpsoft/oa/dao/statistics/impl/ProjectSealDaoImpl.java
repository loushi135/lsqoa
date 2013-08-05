package com.xpsoft.oa.dao.statistics.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.ProjectSealDao;
import com.xpsoft.oa.model.statistics.ProjectSeal;

public class ProjectSealDaoImpl extends BaseDaoImpl<ProjectSeal> implements ProjectSealDao{

	public ProjectSealDaoImpl() {
		super(ProjectSeal.class);
	}

}