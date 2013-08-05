package com.xpsoft.oa.dao.statistics.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.TicketApplyDao;
import com.xpsoft.oa.model.statistics.TicketApply;

public class TicketApplyDaoImpl extends BaseDaoImpl<TicketApply> implements TicketApplyDao{

	public TicketApplyDaoImpl() {
		super(TicketApply.class);
	}

}