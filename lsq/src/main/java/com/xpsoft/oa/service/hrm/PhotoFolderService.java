package com.xpsoft.oa.service.hrm;


import java.util.List;

import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.hrm.PhotoFolder;

public interface PhotoFolderService extends BaseService<PhotoFolder>{

	public List<PhotoFolder> findByParentId(long id);
	
}


