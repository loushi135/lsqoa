package com.xpsoft.oa.dao.bbs.impl;


import java.util.List;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.bbs.BbsGroupDao;
import com.xpsoft.oa.model.bbs.BbsGroup;

public class BbsGroupDaoImpl extends BaseDaoImpl<BbsGroup> implements BbsGroupDao{

	public BbsGroupDaoImpl() {
		super(BbsGroup.class);
	}

	@Override
	public List<BbsGroup> findByParentId(Long id) {
	     String hql = "from BbsGroup g where g.parentId=?";
	     Object[] params = {id};
	     return findByHql(hql, params);
	}

	@Override
	public List<BbsGroup> findByGroupName(String paramString) {
		String hql="from BbsGroup g where g.groupName=?";
		 Object[] params = {paramString};
		 return findByHql(hql, params);
	}
}