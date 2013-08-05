package com.xpsoft.oa.service.hrm.impl;


import java.util.List;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.hrm.PhotoFolderDao;
import com.xpsoft.oa.model.hrm.PhotoFolder;
import com.xpsoft.oa.service.hrm.PhotoFolderService;

public class PhotoFolderServiceImpl extends BaseServiceImpl<PhotoFolder> implements PhotoFolderService{
	private PhotoFolderDao dao;
	
	public PhotoFolderServiceImpl(PhotoFolderDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<PhotoFolder> findByParentId(long id) {
		
		return dao.findByParentId(id);
	}

}