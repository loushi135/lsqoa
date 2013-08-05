 package com.xpsoft.oa.service.task.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.dao.task.AppointmentDao;
 import com.xpsoft.oa.model.task.Appointment;
 import com.xpsoft.oa.service.task.AppointmentService;
 import java.util.List;
 
 public class AppointmentServiceImpl extends BaseServiceImpl<Appointment>
   implements AppointmentService
 {
   private AppointmentDao dao;
 
   public AppointmentServiceImpl(AppointmentDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List showAppointmentByUserId(Long userId, PagingBean pb)
   {
     return this.dao.showAppointmentByUserId(userId, pb);
   }
 }

