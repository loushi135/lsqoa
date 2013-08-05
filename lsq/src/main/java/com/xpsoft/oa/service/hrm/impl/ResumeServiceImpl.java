 package com.xpsoft.oa.service.hrm.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.hrm.ResumeDao;
 import com.xpsoft.oa.model.hrm.Resume;
 import com.xpsoft.oa.service.hrm.ResumeService;
 
 public class ResumeServiceImpl extends BaseServiceImpl<Resume>
   implements ResumeService
 {
   private ResumeDao dao;
 
   public ResumeServiceImpl(ResumeDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

