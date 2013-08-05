package com.xpsoft.oa.dao.hrm.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.hrm.PhotoCommentDao;
import com.xpsoft.oa.model.hrm.PhotoComment;

public class PhotoCommentDaoImpl extends BaseDaoImpl<PhotoComment> implements PhotoCommentDao{

	public PhotoCommentDaoImpl() {
		super(PhotoComment.class);
	}

}