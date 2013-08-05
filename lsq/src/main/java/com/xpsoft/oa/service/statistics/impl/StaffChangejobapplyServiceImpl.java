package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.StaffChangejobapplyDao;
import com.xpsoft.oa.model.statistics.StaffChangejobapply;
import com.xpsoft.oa.service.statistics.StaffChangejobapplyService;

public class StaffChangejobapplyServiceImpl extends BaseServiceImpl<StaffChangejobapply> implements StaffChangejobapplyService{
	private StaffChangejobapplyDao dao;
	
	public StaffChangejobapplyServiceImpl(StaffChangejobapplyDao dao) {
		super(dao);
		this.dao=dao;
	}

}