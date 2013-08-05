package com.xpsoft.oa.dao.statistics.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.statistics.MaterialContractDao;
import com.xpsoft.oa.model.statistics.MaterialContract;

public class MaterialContractDaoImpl extends BaseDaoImpl<MaterialContract> implements MaterialContractDao{

	public MaterialContractDaoImpl() {
		super(MaterialContract.class);
	}

}