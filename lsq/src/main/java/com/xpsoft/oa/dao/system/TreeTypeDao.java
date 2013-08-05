package com.xpsoft.oa.dao.system;


import java.util.List;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.system.TreeType;

/**
 * 
 * @author 
 *
 */
public interface TreeTypeDao extends BaseDao<TreeType>{
	public List<TreeType> findByParentId(long id);
}