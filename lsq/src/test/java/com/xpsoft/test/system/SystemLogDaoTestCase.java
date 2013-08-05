 package com.xpsoft.test.system;
 
 import com.xpsoft.oa.dao.system.SystemLogDao;
 import com.xpsoft.oa.model.system.SystemLog;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class SystemLogDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private SystemLogDao systemLogDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     SystemLog systemLog = new SystemLog();
 
     this.systemLogDao.save(systemLog);
   }
 }

