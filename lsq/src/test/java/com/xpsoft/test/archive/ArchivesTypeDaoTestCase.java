 package com.xpsoft.test.archive;
 
 import com.xpsoft.oa.dao.archive.ArchivesTypeDao;
 import com.xpsoft.oa.model.archive.ArchivesType;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ArchivesTypeDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ArchivesTypeDao archivesTypeDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     ArchivesType archivesType = new ArchivesType();
 
     this.archivesTypeDao.save(archivesType);
   }
 }

