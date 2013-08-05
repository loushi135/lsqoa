 package com.xpsoft.test.admin;
 
 import com.xpsoft.oa.dao.admin.CarApplyDao;
 import com.xpsoft.oa.model.admin.CarApply;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class CarApplyDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private CarApplyDao carApplyDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     CarApply carApply = new CarApply();
 
     this.carApplyDao.save(carApply);
   }
 }

