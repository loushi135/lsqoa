package com.xpsoft.oa.dao.statistics.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.FlowApplyDao;
import com.xpsoft.oa.model.statistics.FlowApply;

public class FlowApplyDaoImpl extends BaseDaoImpl<FlowApply> implements FlowApplyDao{

	public FlowApplyDaoImpl() {
		super(FlowApply.class);
	}

}