package com.xpsoft.oa.dao.admin;

import java.util.List;

import com.xpsoft.core.command.QueryFilter;
import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.admin.AssetsApply;

/**
 * 
 * @author 
 *
 */
public interface AssetsApplyDao extends BaseDao<AssetsApply>{

	List<AssetsApply> getAll2000(QueryFilter filter);
	
}