package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.SalesProjectDao;
import com.xpsoft.oa.model.statistics.SalesProject;
import com.xpsoft.oa.service.statistics.SalesProjectService;

public class SalesProjectServiceImpl extends BaseServiceImpl<SalesProject> implements SalesProjectService{
	private SalesProjectDao dao;
	
	public SalesProjectServiceImpl(SalesProjectDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public String getNewProNo(String depName) {
		return dao.getNewProNo(depName);
	}

	@Override
	public SalesProject getByProName(String proName) {
		return dao.getByProName(proName);
	}

}