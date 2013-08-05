package com.xpsoft.oa.dao.system.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.system.CityDao;
import com.xpsoft.oa.model.system.City;

public class CityDaoImpl extends BaseDaoImpl<City> implements CityDao{

	public CityDaoImpl() {
		super(City.class);
	}

}