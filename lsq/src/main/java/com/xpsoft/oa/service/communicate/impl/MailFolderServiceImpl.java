 package com.xpsoft.oa.service.communicate.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.communicate.MailFolderDao;
 import com.xpsoft.oa.model.communicate.MailFolder;
 import com.xpsoft.oa.service.communicate.MailFolderService;
 import java.util.List;
 
 public class MailFolderServiceImpl extends BaseServiceImpl<MailFolder>
   implements MailFolderService
 {
   private MailFolderDao dao;
 
   public MailFolderServiceImpl(MailFolderDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<MailFolder> getUserFolderByParentId(Long curUserId, Long parentId)
   {
     return this.dao.getUserFolderByParentId(curUserId, parentId);
   }
 
   public List<MailFolder> getAllUserFolderByParentId(Long userId, Long parentId)
   {
     return this.dao.getAllUserFolderByParentId(userId, parentId);
   }
 
   public List<MailFolder> getFolderLikePath(String path)
   {
     return this.dao.getFolderLikePath(path);
   }
 }

