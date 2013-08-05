 package com.xpsoft.test.archive;
 
 import com.xpsoft.oa.dao.archive.ArchivesDepDao;
 import com.xpsoft.oa.model.archive.ArchivesDep;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ArchivesDepDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ArchivesDepDao archivesDepDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     ArchivesDep archivesDep = new ArchivesDep();
 
     this.archivesDepDao.save(archivesDep);
   }
 }

