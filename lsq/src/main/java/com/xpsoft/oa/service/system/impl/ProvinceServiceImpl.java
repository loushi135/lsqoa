package com.xpsoft.oa.service.system.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.system.ProvinceDao;
import com.xpsoft.oa.model.system.Province;
import com.xpsoft.oa.service.system.ProvinceService;

public class ProvinceServiceImpl extends BaseServiceImpl<Province> implements ProvinceService{
	private ProvinceDao dao;
	
	public ProvinceServiceImpl(ProvinceDao dao) {
		super(dao);
		this.dao=dao;
	}

}