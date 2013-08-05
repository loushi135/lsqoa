package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.DesignContractDao;
import com.xpsoft.oa.model.statistics.DesignContract;
import com.xpsoft.oa.service.statistics.DesignContractService;

public class DesignContractServiceImpl extends BaseServiceImpl<DesignContract> implements DesignContractService{
	private DesignContractDao dao;
	
	public DesignContractServiceImpl(DesignContractDao dao) {
		super(dao);
		this.dao=dao;
	}

}