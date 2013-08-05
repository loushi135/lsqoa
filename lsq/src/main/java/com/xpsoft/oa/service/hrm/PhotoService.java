package com.xpsoft.oa.service.hrm;


import com.xpsoft.core.service.BaseService;
import com.xpsoft.oa.model.hrm.Photo;

public interface PhotoService extends BaseService<Photo>{

	public  void saveMultiPhoto(String folderId, String fileIds);
	
}


