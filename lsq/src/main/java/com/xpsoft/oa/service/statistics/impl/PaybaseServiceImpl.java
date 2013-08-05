package com.xpsoft.oa.service.statistics.impl;


import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.PaybaseDao;
import com.xpsoft.oa.model.statistics.Paybase;
import com.xpsoft.oa.service.statistics.PaybaseService;

public class PaybaseServiceImpl extends BaseServiceImpl<Paybase> implements PaybaseService{
	private PaybaseDao dao;
	
	public PaybaseServiceImpl(PaybaseDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public BigDecimal getTotalByMaterialId(Long materialId) {
		// TODO Auto-generated method stub
		return dao.getTotalByMaterialId(materialId);
	}

	@Override
	public BigDecimal getTotalByProAndSupp(Long proId, Long suppId) {
		// TODO Auto-generated method stub
		return dao.getTotalByProAndSupp(proId, suppId);
	}

	@Override
	public List<Paybase> getByProName(String proName) {
		
		return dao.getByProName(proName);
	}

	@Override
	public List<Paybase> getAll(DetachedCriteria criteria) {
		return dao.getAll(criteria);
	}

	@Override
	public List<Paybase> getByProName(String proName, int pageFirst,
			int pageSize) {
		return dao.getByProName(proName,pageFirst,pageSize);
	}

	@Override
	public int getCountByProName(String proName) {
		return dao.getCountByProName(proName);
	}


}