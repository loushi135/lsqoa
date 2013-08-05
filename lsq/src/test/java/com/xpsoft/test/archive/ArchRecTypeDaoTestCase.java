 package com.xpsoft.test.archive;
 
 import com.xpsoft.oa.dao.archive.ArchRecTypeDao;
 import com.xpsoft.oa.model.archive.ArchRecType;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ArchRecTypeDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ArchRecTypeDao archRecTypeDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     ArchRecType archRecType = new ArchRecType();
 
     this.archRecTypeDao.save(archRecType);
   }
 }

