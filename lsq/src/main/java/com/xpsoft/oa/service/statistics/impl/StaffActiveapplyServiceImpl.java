package com.xpsoft.oa.service.statistics.impl;



import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.StaffActiveapplyDao;
import com.xpsoft.oa.model.statistics.StaffActiveapply;
import com.xpsoft.oa.service.statistics.StaffActiveapplyService;

public class StaffActiveapplyServiceImpl extends BaseServiceImpl<StaffActiveapply> implements StaffActiveapplyService{
	private StaffActiveapplyDao dao;
	
	public StaffActiveapplyServiceImpl(StaffActiveapplyDao dao) {
		super(dao);
		this.dao=dao;
	}

}