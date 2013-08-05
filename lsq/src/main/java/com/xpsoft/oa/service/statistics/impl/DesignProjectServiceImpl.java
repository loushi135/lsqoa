package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.DesignProjectDao;
import com.xpsoft.oa.model.statistics.DesignProject;
import com.xpsoft.oa.service.statistics.DesignProjectService;

public class DesignProjectServiceImpl extends BaseServiceImpl<DesignProject> implements DesignProjectService{
	private DesignProjectDao dao;
	
	public DesignProjectServiceImpl(DesignProjectDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public String getNewProNo() {
		return dao.getNewProNo();
	}

	@Override
	public DesignProject getByProName(String proName) {
		return dao.getByProName(proName);
	}

}