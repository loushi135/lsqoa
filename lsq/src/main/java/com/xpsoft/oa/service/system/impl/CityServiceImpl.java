package com.xpsoft.oa.service.system.impl;


import java.util.List;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.system.CityDao;
import com.xpsoft.oa.model.system.City;
import com.xpsoft.oa.service.system.CityService;

public class CityServiceImpl extends BaseServiceImpl<City> implements CityService{
	private CityDao dao;
	
	public CityServiceImpl(CityDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<City> findCityByProvinceId(Long provinceId) {
		// TODO Auto-generated method stub
		return dao.findByHql("from City where province.id =? order by sort asc", new Object[]{provinceId});
	}

	
}