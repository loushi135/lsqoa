package com.xpsoft.oa.service.personal.impl;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.personal.LeaveapplyDao;
import com.xpsoft.oa.model.personal.Leaveapply;
import com.xpsoft.oa.service.personal.LeaveapplyService;

public class LeaveapplyServiceImpl extends BaseServiceImpl<Leaveapply> implements LeaveapplyService{
	private LeaveapplyDao dao;
	
	public LeaveapplyServiceImpl(LeaveapplyDao dao) {
		super(dao);
		this.dao=dao;
	}
}