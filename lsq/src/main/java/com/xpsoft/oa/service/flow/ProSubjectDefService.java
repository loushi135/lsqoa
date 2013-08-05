package com.xpsoft.oa.service.flow;


import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.flow.ProSubjectDef;

public interface ProSubjectDefService extends BaseService<ProSubjectDef>{
	public ProSubjectDef getByDefId(Long defId);
}


