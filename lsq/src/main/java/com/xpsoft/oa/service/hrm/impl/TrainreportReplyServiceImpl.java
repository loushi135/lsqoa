package com.xpsoft.oa.service.hrm.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.hrm.TrainreportReplyDao;
import com.xpsoft.oa.model.hrm.TrainreportReply;
import com.xpsoft.oa.service.hrm.TrainreportReplyService;

public class TrainreportReplyServiceImpl extends BaseServiceImpl<TrainreportReply> implements TrainreportReplyService{
	private TrainreportReplyDao dao;
	
	public TrainreportReplyServiceImpl(TrainreportReplyDao dao) {
		super(dao);
		this.dao=dao;
	}

}