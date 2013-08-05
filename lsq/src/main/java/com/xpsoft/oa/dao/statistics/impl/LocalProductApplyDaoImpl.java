package com.xpsoft.oa.dao.statistics.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.LocalProductApplyDao;
import com.xpsoft.oa.model.statistics.LocalProductApply;

public class LocalProductApplyDaoImpl extends BaseDaoImpl<LocalProductApply> implements LocalProductApplyDao{

	public LocalProductApplyDaoImpl() {
		super(LocalProductApply.class);
	}

}