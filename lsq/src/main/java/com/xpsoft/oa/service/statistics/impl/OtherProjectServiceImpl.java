package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.OtherProjectDao;
import com.xpsoft.oa.model.statistics.OtherProject;
import com.xpsoft.oa.service.statistics.OtherProjectService;

public class OtherProjectServiceImpl extends BaseServiceImpl<OtherProject> implements OtherProjectService{
	private OtherProjectDao dao;
	
	public OtherProjectServiceImpl(OtherProjectDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public String getNewProNo() {
		return dao.getNewProNo();
	}

	@Override
	public OtherProject getByProName(String proName) {
		return dao.getByProName(proName);
	}

}