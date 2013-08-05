package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.FlowApplyDao;
import com.xpsoft.oa.model.statistics.FlowApply;
import com.xpsoft.oa.service.statistics.FlowApplyService;

public class FlowApplyServiceImpl extends BaseServiceImpl<FlowApply> implements FlowApplyService{
	private FlowApplyDao dao;
	
	public FlowApplyServiceImpl(FlowApplyDao dao) {
		super(dao);
		this.dao=dao;
	}

}