 package com.xpsoft.oa.service.system.impl;
 
 import java.io.File;
import java.util.List;

import com.xpsoft.core.service.impl.BaseServiceImpl;
import com.xpsoft.core.util.AppUtil;
import com.xpsoft.oa.dao.system.FileAttachDao;
import com.xpsoft.oa.model.info.InMessage;
import com.xpsoft.oa.model.system.FileAttach;
import com.xpsoft.oa.service.system.FileAttachService;
 
 public class FileAttachServiceImpl extends BaseServiceImpl<FileAttach>
   implements FileAttachService
 {
   private FileAttachDao dao;
 
   public FileAttachServiceImpl(FileAttachDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public void removeByPath(String filePath)
   {
     FileAttach fileAttach = this.dao.getByPath(filePath);
 
     String fullFilePath = AppUtil.getAppAbsolutePath() + "/attachFiles/" + filePath;
 
     this.logger.info("file:" + fullFilePath);
 
     File file = new File(fullFilePath);
 
     if (file.exists()) {
       file.delete();
     }
     if (fileAttach != null)
       this.dao.remove(fileAttach);
   }
 
   public FileAttach getByPath(String filePath)
   {
     return this.dao.getByPath(filePath);
   }

@Override
public List<FileAttach> getFileAttachById(String fileIds) {
	
	return dao.getFileAttachById(fileIds);
}
 }

