 package com.xpsoft.test.communicate;
 
 import com.xpsoft.oa.dao.communicate.PhoneGroupDao;
 import com.xpsoft.oa.model.communicate.PhoneGroup;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class PhoneGroupDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private PhoneGroupDao phoneGroupDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     PhoneGroup phoneGroup = new PhoneGroup();
 
     this.phoneGroupDao.save(phoneGroup);
   }
 }

