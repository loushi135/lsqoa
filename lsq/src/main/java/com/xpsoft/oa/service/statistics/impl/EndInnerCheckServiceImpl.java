package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.EndInnerCheckDao;
import com.xpsoft.oa.model.statistics.EndInnerCheck;
import com.xpsoft.oa.service.statistics.EndInnerCheckService;

public class EndInnerCheckServiceImpl extends BaseServiceImpl<EndInnerCheck> implements EndInnerCheckService{
	private EndInnerCheckDao dao;
	
	public EndInnerCheckServiceImpl(EndInnerCheckDao dao) {
		super(dao);
		this.dao=dao;
	}

}