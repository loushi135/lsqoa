package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.StaffPromoteapplyDao;
import com.xpsoft.oa.model.statistics.StaffPromoteapply;
import com.xpsoft.oa.service.statistics.StaffPromoteapplyService;

public class StaffPromoteapplyServiceImpl extends BaseServiceImpl<StaffPromoteapply> implements StaffPromoteapplyService{
	private StaffPromoteapplyDao dao;
	
	public StaffPromoteapplyServiceImpl(StaffPromoteapplyDao dao) {
		super(dao);
		this.dao=dao;
	}

}