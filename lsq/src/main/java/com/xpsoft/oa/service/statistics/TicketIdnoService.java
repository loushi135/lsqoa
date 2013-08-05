package com.xpsoft.oa.service.statistics;


import java.util.List;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.statistics.TicketIdno;

public interface TicketIdnoService extends BaseService<TicketIdno>{

	List<TicketIdno> getListNew(Long ticketId);
	
}


