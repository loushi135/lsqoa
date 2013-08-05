package com.xpsoft.oa.dao.flow;


import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.flow.ProSubjectDef;

/**
 * 
 * @author 
 *
 */
public interface ProSubjectDefDao extends BaseDao<ProSubjectDef>{

	ProSubjectDef getByDefId(Long defId);
	
}