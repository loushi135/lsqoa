 package com.xpsoft.oa.service.task.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.dao.task.WorkPlanDao;
 import com.xpsoft.oa.model.system.AppUser;
 import com.xpsoft.oa.model.task.WorkPlan;
 import com.xpsoft.oa.service.task.WorkPlanService;
 import java.util.List;
 
 public class WorkPlanServiceImpl extends BaseServiceImpl<WorkPlan>
   implements WorkPlanService
 {
   private WorkPlanDao dao;
 
   public WorkPlanServiceImpl(WorkPlanDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<WorkPlan> findByDepartment(WorkPlan workPlan, AppUser user, PagingBean pb)
   {
     return this.dao.findByDepartment(workPlan, user, pb);
   }
 }

