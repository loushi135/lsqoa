package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.StaffRecruitapplyDao;
import com.xpsoft.oa.model.statistics.StaffRecruitapply;
import com.xpsoft.oa.service.statistics.StaffRecruitapplyService;

public class StaffRecruitapplyServiceImpl extends BaseServiceImpl<StaffRecruitapply> implements StaffRecruitapplyService{
	private StaffRecruitapplyDao dao;
	
	public StaffRecruitapplyServiceImpl(StaffRecruitapplyDao dao) {
		super(dao);
		this.dao=dao;
	}

}