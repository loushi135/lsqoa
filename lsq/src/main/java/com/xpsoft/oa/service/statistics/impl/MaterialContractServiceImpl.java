package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.MaterialContractDao;
import com.xpsoft.oa.model.statistics.MaterialContract;
import com.xpsoft.oa.service.statistics.MaterialContractService;

public class MaterialContractServiceImpl extends BaseServiceImpl<MaterialContract> implements MaterialContractService{
	private MaterialContractDao dao;
	
	public MaterialContractServiceImpl(MaterialContractDao dao) {
		super(dao);
		this.dao=dao;
	}

}