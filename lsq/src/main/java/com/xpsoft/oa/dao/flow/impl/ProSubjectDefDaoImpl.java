package com.xpsoft.oa.dao.flow.impl;


import com.xpsoft.core.dao.impl.BaseDaoImpl;
import com.xpsoft.oa.dao.flow.ProSubjectDefDao;
import com.xpsoft.oa.model.flow.ProSubjectDef;

public class ProSubjectDefDaoImpl extends BaseDaoImpl<ProSubjectDef> implements ProSubjectDefDao{

	public ProSubjectDefDaoImpl() {
		super(ProSubjectDef.class);
	}

	@Override
	public ProSubjectDef getByDefId(Long defId) {
		
		String hql="from ProSubjectDef p where p.defId=?";
		
		return (ProSubjectDef) findUnique(hql, new Object[]{defId});
	}

}