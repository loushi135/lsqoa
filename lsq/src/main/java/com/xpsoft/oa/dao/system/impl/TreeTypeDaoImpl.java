package com.xpsoft.oa.dao.system.impl;


import java.util.List;

import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.system.TreeTypeDao;
import com.xpsoft.oa.model.system.TreeType;

public class TreeTypeDaoImpl extends BaseDaoImpl<TreeType> implements TreeTypeDao{

	public TreeTypeDaoImpl() {
		super(TreeType.class);
	}

	@Override
	public List<TreeType> findByParentId(long id) {
		final String str="from TreeType where parent.id=?";
		Object[] params={id};
		return findByHql(str, params);
	}

}