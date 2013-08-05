 package com.xpsoft.test.document;
 
 import com.xpsoft.oa.dao.document.DocFolderDao;
 import com.xpsoft.oa.dao.document.DocPrivilegeDao;
 import com.xpsoft.oa.dao.document.DocumentDao;
 import com.xpsoft.oa.dao.system.AppUserDao;
 import com.xpsoft.oa.model.system.AppUser;
 import com.xpsoft.test.BaseTestCase;
 import java.io.PrintStream;
 import javax.annotation.Resource;
 import org.junit.Test;
 
 public class DocPrivilegeDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private DocPrivilegeDao docPrivilegeDao;
 
   @Resource
   private DocumentDao documentDao;
 
   @Resource
   private DocFolderDao docFolderDao;
 
   @Resource
   private AppUserDao dao;
 
   @Test
   public void str()
   {
     AppUser user = (AppUser)this.dao.get(Long.valueOf(2L));
     Integer right = this.docPrivilegeDao.getRightsByDocument(user, Long.valueOf(1L));
     System.out.println(right);
   }
 }

