package com.xpsoft.oa.service.admin.impl;

import java.util.List;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.admin.AssetsApplyDao;
import com.xpsoft.oa.model.admin.AssetsApply;
import com.xpsoft.oa.service.admin.AssetsApplyService;

public class AssetsApplyServiceImpl extends BaseServiceImpl<AssetsApply> implements AssetsApplyService{
	private AssetsApplyDao dao;
	
	public AssetsApplyServiceImpl(AssetsApplyDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<AssetsApply> getAll2000(QueryFilter filter) {
		return dao.getAll2000(filter);
	}

}