 package com.xpsoft.test.hrm;
 
 import com.xpsoft.oa.dao.hrm.JobDao;
 import com.xpsoft.oa.model.hrm.Job;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class JobDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private JobDao jobDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     Job job = new Job();
 
     this.jobDao.save(job);
   }
 }

