 package com.xpsoft.test.flow;
 
 import com.xpsoft.oa.dao.flow.TaskDao;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 
 public class TaskDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private TaskDao taskDao;
 
   @Test
   public void testPersonTask()
   {
     String userId = "1";
   }
 }

