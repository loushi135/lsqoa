package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.TicketApplyDao;
import com.xpsoft.oa.model.statistics.TicketApply;
import com.xpsoft.oa.service.statistics.TicketApplyService;

public class TicketApplyServiceImpl extends BaseServiceImpl<TicketApply> implements TicketApplyService{
	private TicketApplyDao dao;
	
	public TicketApplyServiceImpl(TicketApplyDao dao) {
		super(dao);
		this.dao=dao;
	}

}