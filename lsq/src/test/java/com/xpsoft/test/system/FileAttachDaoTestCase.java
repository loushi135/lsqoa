 package com.xpsoft.test.system;
 
 import com.xpsoft.oa.dao.system.FileAttachDao;
 import com.xpsoft.oa.model.system.FileAttach;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class FileAttachDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private FileAttachDao fileAttachDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     FileAttach fileAttach = new FileAttach();
 
     this.fileAttachDao.removeByPath("communicate/mail/200910/2a4367669a1a4ea2b811013ceed199ce.png");
   }
 }

