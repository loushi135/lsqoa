package com.xpsoft.oa.dao.statistics.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.CarSubsidyDao;
import com.xpsoft.oa.model.statistics.CarSubsidy;

public class CarSubsidyDaoImpl extends BaseDaoImpl<CarSubsidy> implements CarSubsidyDao{

	public CarSubsidyDaoImpl() {
		super(CarSubsidy.class);
	}

}