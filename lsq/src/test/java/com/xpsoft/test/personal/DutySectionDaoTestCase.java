 package com.xpsoft.test.personal;
 
 import com.xpsoft.oa.dao.personal.DutySectionDao;
 import com.xpsoft.oa.model.personal.DutySection;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class DutySectionDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private DutySectionDao dutySectionDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     DutySection dutySection = new DutySection();
 
     this.dutySectionDao.save(dutySection);
   }
 }

