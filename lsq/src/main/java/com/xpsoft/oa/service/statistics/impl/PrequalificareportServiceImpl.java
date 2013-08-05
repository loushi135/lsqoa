package com.xpsoft.oa.service.statistics.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.statistics.PrequalificareportDao;
import com.xpsoft.oa.model.statistics.Prequalificareport;
import com.xpsoft.oa.service.statistics.PrequalificareportService;

public class PrequalificareportServiceImpl extends BaseServiceImpl<Prequalificareport> implements PrequalificareportService{
	private PrequalificareportDao dao;
	
	public PrequalificareportServiceImpl(PrequalificareportDao dao) {
		super(dao);
		this.dao=dao;
	}

}