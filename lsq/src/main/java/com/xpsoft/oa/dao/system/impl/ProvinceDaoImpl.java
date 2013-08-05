package com.xpsoft.oa.dao.system.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.system.ProvinceDao;
import com.xpsoft.oa.model.system.Province;

public class ProvinceDaoImpl extends BaseDaoImpl<Province> implements ProvinceDao{

	public ProvinceDaoImpl() {
		super(Province.class);
	}

}