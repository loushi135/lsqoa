 package com.xpsoft.test.archive;
 
 import com.xpsoft.oa.dao.archive.DocHistoryDao;
 import com.xpsoft.oa.model.archive.DocHistory;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class DocHistoryDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private DocHistoryDao docHistoryDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     DocHistory docHistory = new DocHistory();
 
     this.docHistoryDao.save(docHistory);
   }
 }

