 package com.xpsoft.test.admin;
 
 import com.xpsoft.oa.dao.admin.BookBorRetDao;
 import com.xpsoft.oa.model.admin.BookBorRet;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class BookBorRetDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private BookBorRetDao bookBorRetDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     BookBorRet bookBorRet = new BookBorRet();
 
     this.bookBorRetDao.save(bookBorRet);
   }
 }

