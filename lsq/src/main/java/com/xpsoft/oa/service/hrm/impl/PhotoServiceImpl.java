package com.xpsoft.oa.service.hrm.impl;


import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.xpsoft.oa.dao.hrm.PhotoFolderDao;
import com.xpsoft.oa.dao.system.FileAttachDao;
import com.xpsoft.core.util.ContextUtil;
import com.xpsoft.oa.model.hrm.PhotoFolder;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.oa.dao.hrm.PhotoDao;
import com.xpsoft.oa.model.hrm.Photo;
import com.xpsoft.oa.service.hrm.PhotoService;

public class PhotoServiceImpl extends BaseServiceImpl<Photo> implements PhotoService{
	private PhotoDao dao;
	@Autowired
	private FileAttachDao fileAttachDao;
	@Autowired
	private PhotoFolderDao photoFolderDao;
	public PhotoServiceImpl(PhotoDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public void saveMultiPhoto(String folderId, String fileIds) {

		// TODO Auto-generated method stub
		if(StringUtils.isNotBlank(fileIds)){
			String fileId[] = fileIds.split(",");
			PhotoFolder photoFolder = photoFolderDao.get(Long.valueOf(folderId));
			for(String fId:fileId){
				FileAttach fileAttach = fileAttachDao.get(Long.valueOf(fId));
				Photo photo = new Photo();
				photo.setFile(fileAttach);
				photo.setPhotoFolder(photoFolder);
				photo.setUser(ContextUtil.getCurrentUser());
				photo.setCreateDate(new Date());
				dao.save(photo);
			}
		}
	
		
	}

}