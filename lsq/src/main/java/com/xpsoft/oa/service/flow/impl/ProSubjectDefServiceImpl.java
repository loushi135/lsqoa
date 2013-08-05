package com.xpsoft.oa.service.flow.impl;


import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.flow.ProSubjectDefDao;
import com.xpsoft.oa.model.flow.ProSubjectDef;
import com.xpsoft.oa.service.flow.ProSubjectDefService;

public class ProSubjectDefServiceImpl extends BaseServiceImpl<ProSubjectDef> implements ProSubjectDefService{
	private ProSubjectDefDao dao;
	
	public ProSubjectDefServiceImpl(ProSubjectDefDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public ProSubjectDef getByDefId(Long defId) {
		
		return dao.getByDefId(defId);
	}

}