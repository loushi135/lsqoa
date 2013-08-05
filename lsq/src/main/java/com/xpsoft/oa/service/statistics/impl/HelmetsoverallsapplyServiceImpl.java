package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.HelmetsoverallsapplyDao;
import com.xpsoft.oa.model.statistics.Helmetsoverallsapply;
import com.xpsoft.oa.service.statistics.HelmetsoverallsapplyService;

public class HelmetsoverallsapplyServiceImpl extends BaseServiceImpl<Helmetsoverallsapply> implements HelmetsoverallsapplyService{
	private HelmetsoverallsapplyDao dao;
	
	public HelmetsoverallsapplyServiceImpl(HelmetsoverallsapplyDao dao) {
		super(dao);
		this.dao=dao;
	}

}