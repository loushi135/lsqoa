 package com.xpsoft.oa.service.customer.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.customer.ProjectDao;
 import com.xpsoft.oa.model.customer.Project;
 import com.xpsoft.oa.service.customer.ProjectService;
 
 public class ProjectServiceImpl extends BaseServiceImpl<Project>
   implements ProjectService
 {
   private ProjectDao dao;
 
   public ProjectServiceImpl(ProjectDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public boolean checkProjectNo(String projectNo)
   {
     return this.dao.checkProjectNo(projectNo);
   }
 }
