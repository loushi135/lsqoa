 package com.xpsoft.test.admin;
 
 import com.xpsoft.oa.dao.admin.BookSnDao;
 import com.xpsoft.oa.model.admin.BookSn;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class BookSnDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private BookSnDao bookSnDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     BookSn bookSn = new BookSn();
 
     this.bookSnDao.save(bookSn);
   }
 }

