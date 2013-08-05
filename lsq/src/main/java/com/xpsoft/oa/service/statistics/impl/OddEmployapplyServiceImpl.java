package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.OddEmployapplyDao;
import com.xpsoft.oa.model.statistics.OddEmployapply;
import com.xpsoft.oa.service.statistics.OddEmployapplyService;

public class OddEmployapplyServiceImpl extends BaseServiceImpl<OddEmployapply> implements OddEmployapplyService{
	private OddEmployapplyDao dao;
	
	public OddEmployapplyServiceImpl(OddEmployapplyDao dao) {
		super(dao);
		this.dao=dao;
	}

}