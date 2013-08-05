package com.xpsoft.core.service.impl;

import com.xpsoft.core.dao.ProContentMsgDao;
import com.xpsoft.core.model.ProContentMsg;
import com.xpsoft.core.service.ProContentMsgService;



public class ProContentMsgServiceImpl extends BaseServiceImpl<ProContentMsg> implements ProContentMsgService{
	private ProContentMsgDao dao;
	
	public ProContentMsgServiceImpl(ProContentMsgDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public void deleteByProMsgDetailId(Long pmdId) {
		dao.findByHql("delete ProContentMsg pcm where pcm.promsgdetailId="+pmdId, null);
	}
	
}