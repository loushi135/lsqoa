package com.xpsoft.core.service.impl;

import java.util.List;

import com.xpsoft.core.dao.ProMsgReceivedDao;
import com.xpsoft.core.model.ProMsgReceived;
import com.xpsoft.core.service.ProMsgReceivedService;

public class ProMsgReceivedServiceImpl extends BaseServiceImpl<ProMsgReceived> implements ProMsgReceivedService{
	private ProMsgReceivedDao dao;
	
	public ProMsgReceivedServiceImpl(ProMsgReceivedDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<ProMsgReceived> getByStrId(String strId) {
		List<ProMsgReceived> list = dao.findByHql("from ProMsgReceived pmr where pmr.strId="+strId, null);
		return list;
	}

}