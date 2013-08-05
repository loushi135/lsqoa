 package com.xpsoft.test.flow;
 
 import com.xpsoft.oa.dao.flow.ProcessRunDao;
 import com.xpsoft.oa.model.flow.ProcessRun;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ProcessRunDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ProcessRunDao processRunDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     ProcessRun processRun = new ProcessRun();
 
     this.processRunDao.save(processRun);
   }
 }

