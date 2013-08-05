package com.xpsoft.oa.dao.statistics;


import java.util.List;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.statistics.TicketIdno;

/**
 * 
 * @author 
 *
 */
public interface TicketIdnoDao extends BaseDao<TicketIdno>{
	
	public List<TicketIdno> getListNew(Long ticketId);
	
}