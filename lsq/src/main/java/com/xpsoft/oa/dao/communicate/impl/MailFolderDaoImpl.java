 package com.xpsoft.oa.dao.communicate.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.communicate.MailFolderDao;
 import com.xpsoft.oa.model.communicate.MailFolder;
 import java.util.List;
 
 public class MailFolderDaoImpl extends BaseDaoImpl<MailFolder>
   implements MailFolderDao
 {
   public MailFolderDaoImpl()
   {
     super(MailFolder.class);
   }
 
   public List<MailFolder> getUserFolderByParentId(Long userId, Long parentId)
   {
     String hql = "from MailFolder mf where mf.appUser.userId=? and parentId=?";
     return findByHql(hql, new Object[] { userId, parentId });
   }
 
   public List<MailFolder> getAllUserFolderByParentId(Long userId, Long parentId)
   {
     String hql = "from MailFolder mf where mf.appUser.userId=? and parentId=? or userId is null";
     return findByHql(hql, new Object[] { userId, parentId });
   }
 
   public List<MailFolder> getFolderLikePath(String path)
   {
     String hql = "from MailFolder mf where mf.path like ?";
     return findByHql(hql, new Object[] { path + '%' });
   }
 }

