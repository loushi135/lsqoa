 package com.xpsoft.test.archive;
 
 import com.xpsoft.oa.dao.archive.ArchivesDocDao;
 import com.xpsoft.oa.model.archive.ArchivesDoc;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ArchivesDocDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ArchivesDocDao archivesDocDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     ArchivesDoc archivesDoc = new ArchivesDoc();
 
     this.archivesDocDao.save(archivesDoc);
   }
 }

