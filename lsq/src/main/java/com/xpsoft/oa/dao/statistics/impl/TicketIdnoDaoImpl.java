package com.xpsoft.oa.dao.statistics.impl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.TicketIdnoDao;
import com.xpsoft.oa.model.statistics.TicketIdno;

public class TicketIdnoDaoImpl extends BaseDaoImpl<TicketIdno> implements TicketIdnoDao{

	public TicketIdnoDaoImpl() {
		super(TicketIdno.class);
	}
	public List<TicketIdno> getListNew(Long ticketId) {
		String sql="select * from  ticket_idno where ticketId = '"+ticketId+"'";
		List<TicketIdno> ticketIdnoList = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).list();
		return ticketIdnoList;
	}
}