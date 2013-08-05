 package com.xpsoft.test.task;
 
 import com.xpsoft.oa.dao.task.CalendarPlanDao;
 import com.xpsoft.oa.model.task.CalendarPlan;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class CalendarPlanDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private CalendarPlanDao calendarPlanDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     CalendarPlan calendarPlan = new CalendarPlan();
 
     this.calendarPlanDao.save(calendarPlan);
   }
 }

