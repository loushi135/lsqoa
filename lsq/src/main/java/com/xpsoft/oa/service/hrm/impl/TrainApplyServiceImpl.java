package com.xpsoft.oa.service.hrm.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.hrm.TrainApplyDao;
import com.xpsoft.oa.model.hrm.TrainApply;
import com.xpsoft.oa.service.hrm.TrainApplyService;

public class TrainApplyServiceImpl extends BaseServiceImpl<TrainApply> implements TrainApplyService{
	private TrainApplyDao dao;
	
	public TrainApplyServiceImpl(TrainApplyDao dao) {
		super(dao);
		this.dao=dao;
	}

}