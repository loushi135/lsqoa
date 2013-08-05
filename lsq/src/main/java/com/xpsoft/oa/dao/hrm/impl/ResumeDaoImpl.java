 package com.xpsoft.oa.dao.hrm.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.hrm.ResumeDao;
 import com.xpsoft.oa.model.hrm.Resume;
 
 public class ResumeDaoImpl extends BaseDaoImpl<Resume>
   implements ResumeDao
 {
   public ResumeDaoImpl()
   {
     super(Resume.class);
   }
 }

