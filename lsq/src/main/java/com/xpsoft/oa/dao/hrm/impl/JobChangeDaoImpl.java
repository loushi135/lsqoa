 package com.xpsoft.oa.dao.hrm.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.hrm.JobChangeDao;
 import com.xpsoft.oa.model.hrm.JobChange;
 
 public class JobChangeDaoImpl extends BaseDaoImpl<JobChange>
   implements JobChangeDao
 {
   public JobChangeDaoImpl()
   {
     super(JobChange.class);
   }
 }

