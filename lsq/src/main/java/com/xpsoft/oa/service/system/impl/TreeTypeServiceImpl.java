package com.xpsoft.oa.service.system.impl;


import java.util.List;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.system.TreeTypeDao;
import com.xpsoft.oa.model.system.TreeType;
import com.xpsoft.oa.service.system.TreeTypeService;

public class TreeTypeServiceImpl extends BaseServiceImpl<TreeType> implements TreeTypeService{
	private TreeTypeDao dao;
	
	public TreeTypeServiceImpl(TreeTypeDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<TreeType> findByParentId(long id) {
		// TODO Auto-generated method stub
		return dao.findByParentId(id);
	}

	@Override
	public List<TreeType> findByParentIdAndClassName(Long parentId,
			String className) {
		// TODO Auto-generated method stub
		String hql = " from TreeType where parent.id=? and className=?";
		return dao.findByHql(hql, new Object[]{parentId,className});
	}

	
}