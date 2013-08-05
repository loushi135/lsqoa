package com.xpsoft.oa.dao.hrm.impl;


import java.util.List;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.hrm.PhotoFolderDao;
import com.xpsoft.oa.model.hrm.PhotoFolder;

public class PhotoFolderDaoImpl extends BaseDaoImpl<PhotoFolder> implements PhotoFolderDao{

	public PhotoFolderDaoImpl() {
		super(PhotoFolder.class);
	}

	@Override
	public List<PhotoFolder> findByParentId(Long id) {
		final String str="from PhotoFolder p where p.parent.id=?";
		Object[] params={id};
		return findByHql(str, params);
	}

}