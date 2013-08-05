package com.xpsoft.oa.service.info.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.info.NoticeViewDetailDao;
import com.xpsoft.oa.model.info.NoticeViewDetail;
import com.xpsoft.oa.service.info.NoticeViewDetailService;

public class NoticeViewDetailServiceImpl extends BaseServiceImpl<NoticeViewDetail> implements NoticeViewDetailService{
	private NoticeViewDetailDao dao;
	
	public NoticeViewDetailServiceImpl(NoticeViewDetailDao dao) {
		super(dao);
		this.dao=dao;
	}

}