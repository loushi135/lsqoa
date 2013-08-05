package com.xpsoft.oa.service.flow.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.flow.ProOtherDefDao;
import com.xpsoft.oa.model.flow.ProOtherDef;
import com.xpsoft.oa.service.flow.ProOtherDefService;

public class ProOtherDefServiceImpl extends BaseServiceImpl<ProOtherDef> implements ProOtherDefService{
	private ProOtherDefDao dao;
	
	public ProOtherDefServiceImpl(ProOtherDefDao dao) {
		super(dao);
		this.dao=dao;
	}

}