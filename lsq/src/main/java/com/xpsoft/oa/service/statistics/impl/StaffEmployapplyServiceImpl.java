package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.StaffEmployapplyDao;
import com.xpsoft.oa.model.statistics.StaffEmployapply;
import com.xpsoft.oa.service.statistics.StaffEmployapplyService;

public class StaffEmployapplyServiceImpl extends BaseServiceImpl<StaffEmployapply> implements StaffEmployapplyService{
	private StaffEmployapplyDao dao;
	
	public StaffEmployapplyServiceImpl(StaffEmployapplyDao dao) {
		super(dao);
		this.dao=dao;
	}

}