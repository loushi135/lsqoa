package com.xpsoft.oa.dao.hrm;


import java.util.List;

import com.xpsoft.core.dao.BaseDao;
import com.xpsoft.oa.model.hrm.PhotoFolder;

/**
 * 
 * @author 
 *
 */
public interface PhotoFolderDao extends BaseDao<PhotoFolder>{
	public List<PhotoFolder> findByParentId(Long id);
		
	
}