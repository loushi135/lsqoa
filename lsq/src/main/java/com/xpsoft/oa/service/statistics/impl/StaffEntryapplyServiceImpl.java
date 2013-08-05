package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.StaffEntryapplyDao;
import com.xpsoft.oa.model.statistics.StaffEntryapply;
import com.xpsoft.oa.service.statistics.StaffEntryapplyService;

public class StaffEntryapplyServiceImpl extends BaseServiceImpl<StaffEntryapply> implements StaffEntryapplyService{
	private StaffEntryapplyDao dao;
	
	public StaffEntryapplyServiceImpl(StaffEntryapplyDao dao) {
		super(dao);
		this.dao=dao;
	}

}