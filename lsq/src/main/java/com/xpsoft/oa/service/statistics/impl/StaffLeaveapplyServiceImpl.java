package com.xpsoft.oa.service.statistics.impl;



import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.StaffLeaveapplyDao;
import com.xpsoft.oa.model.statistics.StaffLeaveapply;
import com.xpsoft.oa.service.statistics.StaffLeaveapplyService;

public class StaffLeaveapplyServiceImpl extends BaseServiceImpl<StaffLeaveapply> implements StaffLeaveapplyService{
	private StaffLeaveapplyDao dao;
	
	public StaffLeaveapplyServiceImpl(StaffLeaveapplyDao dao) {
		super(dao);
		this.dao=dao;
	}

}