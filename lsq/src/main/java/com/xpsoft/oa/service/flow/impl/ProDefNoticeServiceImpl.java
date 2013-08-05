package com.xpsoft.oa.service.flow.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.flow.ProDefNoticeDao;
import com.xpsoft.oa.model.flow.ProDefNotice;
import com.xpsoft.oa.service.flow.ProDefNoticeService;

public class ProDefNoticeServiceImpl extends BaseServiceImpl<ProDefNotice> implements ProDefNoticeService{
	private ProDefNoticeDao dao;
	
	public ProDefNoticeServiceImpl(ProDefNoticeDao dao) {
		super(dao);
		this.dao=dao;
	}

}