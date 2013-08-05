package com.xpsoft.oa.service.bbs.impl;


import java.util.List;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.bbs.BbsGroupDao;
import com.xpsoft.oa.model.bbs.BbsGroup;
import com.xpsoft.oa.service.bbs.BbsGroupService;

public class BbsGroupServiceImpl extends BaseServiceImpl<BbsGroup> implements BbsGroupService{
	private BbsGroupDao dao;
	
	public BbsGroupServiceImpl(BbsGroupDao dao) {
		super(dao);
		this.dao=dao;
	}
	@Override
	public List<BbsGroup> findByParentId(Long id) {
		return dao.findByParentId(id);
	}

	@Override
	public List<BbsGroup> findByGroupName(String paramString) {
		return dao.findByGroupName(paramString);
	}

}