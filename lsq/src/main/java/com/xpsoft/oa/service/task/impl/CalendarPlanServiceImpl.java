 package com.xpsoft.oa.service.task.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.dao.task.CalendarPlanDao;
 import com.xpsoft.oa.model.task.CalendarPlan;
 import com.xpsoft.oa.service.task.CalendarPlanService;
 import java.util.Date;
 import java.util.List;
 
 public class CalendarPlanServiceImpl extends BaseServiceImpl<CalendarPlan>
   implements CalendarPlanService
 {
   private CalendarPlanDao dao;
 
   public CalendarPlanServiceImpl(CalendarPlanDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<CalendarPlan> getTodayPlans(Long userId, PagingBean pb)
   {
     return this.dao.getTodayPlans(userId, pb);
   }
 
   public List<CalendarPlan> getByPeriod(Long userId, Date startTime, Date endTime)
   {
     return this.dao.getByPeriod(userId, startTime, endTime);
   }
 
   public List showCalendarPlanByUserId(Long userId, PagingBean pb)
   {
     return this.dao.showCalendarPlanByUserId(userId, pb);
   }
 }

