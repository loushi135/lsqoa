package com.xpsoft.oa.dao.statistics.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.DesignContractDao;
import com.xpsoft.oa.model.statistics.DesignContract;

public class DesignContractDaoImpl extends BaseDaoImpl<DesignContract> implements DesignContractDao{

	public DesignContractDaoImpl() {
		super(DesignContract.class);
	}

}