 package com.xpsoft.oa.service.hrm.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.hrm.JobChangeDao;
 import com.xpsoft.oa.model.hrm.JobChange;
 import com.xpsoft.oa.service.hrm.JobChangeService;
 
 public class JobChangeServiceImpl extends BaseServiceImpl<JobChange>
   implements JobChangeService
 {
   private JobChangeDao dao;
 
   public JobChangeServiceImpl(JobChangeDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

