package com.xpsoft.oa.service.flow.impl;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.flow.ProcessDelReasonDao;
import com.xpsoft.oa.model.flow.ProcessDelReason;
import com.xpsoft.oa.service.flow.ProcessDelReasonService;




public class ProcessDelReasonServiceImpl extends BaseServiceImpl<ProcessDelReason> implements ProcessDelReasonService{
	private ProcessDelReasonDao dao;
	
	public ProcessDelReasonServiceImpl(ProcessDelReasonDao dao) {
		super(dao);
		this.dao=dao;
	}

}