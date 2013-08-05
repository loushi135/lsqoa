package com.xpsoft.oa.service.bbs.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.bbs.BbsReplyDao;
import com.xpsoft.oa.model.bbs.BbsReply;
import com.xpsoft.oa.service.bbs.BbsReplyService;

public class BbsReplyServiceImpl extends BaseServiceImpl<BbsReply> implements BbsReplyService{
	private BbsReplyDao dao;
	
	public BbsReplyServiceImpl(BbsReplyDao dao) {
		super(dao);
		this.dao=dao;
	}

}