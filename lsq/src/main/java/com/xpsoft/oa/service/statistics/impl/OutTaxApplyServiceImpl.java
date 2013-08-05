package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.OutTaxApplyDao;
import com.xpsoft.oa.model.statistics.OutTaxApply;
import com.xpsoft.oa.service.statistics.OutTaxApplyService;

public class OutTaxApplyServiceImpl extends BaseServiceImpl<OutTaxApply> implements OutTaxApplyService{
	private OutTaxApplyDao dao;
	
	public OutTaxApplyServiceImpl(OutTaxApplyDao dao) {
		super(dao);
		this.dao=dao;
	}

}