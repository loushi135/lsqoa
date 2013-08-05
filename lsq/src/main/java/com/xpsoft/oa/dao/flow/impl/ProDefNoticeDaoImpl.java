package com.xpsoft.oa.dao.flow.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.flow.ProDefNoticeDao;
import com.xpsoft.oa.model.flow.ProDefNotice;

public class ProDefNoticeDaoImpl extends BaseDaoImpl<ProDefNotice> implements ProDefNoticeDao{

	public ProDefNoticeDaoImpl() {
		super(ProDefNotice.class);
	}

}