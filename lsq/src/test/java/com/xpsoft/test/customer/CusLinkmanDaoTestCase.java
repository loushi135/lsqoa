 package com.xpsoft.test.customer;
 
 import com.xpsoft.oa.dao.customer.CusLinkmanDao;
 import com.xpsoft.oa.model.customer.CusLinkman;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class CusLinkmanDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private CusLinkmanDao cusLinkmanDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     CusLinkman cusLinkman = new CusLinkman();
 
     this.cusLinkmanDao.save(cusLinkman);
   }
 }

