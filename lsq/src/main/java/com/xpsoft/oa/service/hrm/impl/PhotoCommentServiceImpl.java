package com.xpsoft.oa.service.hrm.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.hrm.PhotoCommentDao;
import com.xpsoft.oa.model.hrm.PhotoComment;
import com.xpsoft.oa.service.hrm.PhotoCommentService;

public class PhotoCommentServiceImpl extends BaseServiceImpl<PhotoComment> implements PhotoCommentService{
	private PhotoCommentDao dao;
	
	public PhotoCommentServiceImpl(PhotoCommentDao dao) {
		super(dao);
		this.dao=dao;
	}

}