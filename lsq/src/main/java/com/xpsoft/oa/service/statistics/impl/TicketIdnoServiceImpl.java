package com.xpsoft.oa.service.statistics.impl;


import java.util.List;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.TicketIdnoDao;
import com.xpsoft.oa.model.statistics.TicketIdno;
import com.xpsoft.oa.service.statistics.TicketIdnoService;

public class TicketIdnoServiceImpl extends BaseServiceImpl<TicketIdno> implements TicketIdnoService{
	private TicketIdnoDao dao;
	
	public TicketIdnoServiceImpl(TicketIdnoDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<TicketIdno> getListNew(Long ticketId) {
		return dao.getListNew(ticketId);
	}


}