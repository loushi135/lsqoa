package com.xpsoft.oa.service.admin.impl;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.admin.AssetsApplycontentDao;
import com.xpsoft.oa.model.admin.AssetsApplycontent;
import com.xpsoft.oa.service.admin.AssetsApplycontentService;

public class AssetsApplycontentServiceImpl extends BaseServiceImpl<AssetsApplycontent> implements AssetsApplycontentService{
	private AssetsApplycontentDao dao;
	
	public AssetsApplycontentServiceImpl(AssetsApplycontentDao dao) {
		super(dao);
		this.dao=dao;
	}

}