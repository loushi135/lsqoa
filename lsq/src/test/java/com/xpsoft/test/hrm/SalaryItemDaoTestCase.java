 package com.xpsoft.test.hrm;
 
 import com.xpsoft.oa.dao.hrm.SalaryItemDao;
 import com.xpsoft.oa.model.hrm.SalaryItem;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class SalaryItemDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private SalaryItemDao salaryItemDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     SalaryItem salaryItem = new SalaryItem();
 
     this.salaryItemDao.save(salaryItem);
   }
 }

