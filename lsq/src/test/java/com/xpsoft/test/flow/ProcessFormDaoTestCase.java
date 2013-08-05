 package com.xpsoft.test.flow;
 
 import com.xpsoft.oa.dao.flow.ProcessFormDao;
 import com.xpsoft.oa.model.flow.ProcessForm;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ProcessFormDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ProcessFormDao processFormDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     ProcessForm processForm = new ProcessForm();
 
     this.processFormDao.save(processForm);
   }
 }
