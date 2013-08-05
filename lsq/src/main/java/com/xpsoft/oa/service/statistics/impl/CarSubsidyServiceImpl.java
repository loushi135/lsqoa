package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.CarSubsidyDao;
import com.xpsoft.oa.model.statistics.CarSubsidy;
import com.xpsoft.oa.service.statistics.CarSubsidyService;

public class CarSubsidyServiceImpl extends BaseServiceImpl<CarSubsidy> implements CarSubsidyService{
	private CarSubsidyDao dao;
	
	public CarSubsidyServiceImpl(CarSubsidyDao dao) {
		super(dao);
		this.dao=dao;
	}

}