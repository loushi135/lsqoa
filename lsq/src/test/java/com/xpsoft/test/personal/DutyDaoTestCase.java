 package com.xpsoft.test.personal;
 
 import com.xpsoft.oa.dao.personal.DutyDao;
 import com.xpsoft.oa.model.personal.Duty;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class DutyDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private DutyDao dutyDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     Duty duty = new Duty();
 
     this.dutyDao.save(duty);
   }
 }

