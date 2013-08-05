package com.xpsoft.oa.dao.flow.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.flow.ProcessDelReasonDao;
import com.xpsoft.oa.model.flow.ProcessDelReason;

public class ProcessDelReasonDaoImpl extends BaseDaoImpl<ProcessDelReason> implements ProcessDelReasonDao{

	public ProcessDelReasonDaoImpl() {
		super(ProcessDelReason.class);
	}

}