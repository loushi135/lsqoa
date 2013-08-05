 package com.xpsoft.oa.dao.task.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.dao.task.AppointmentDao;
 import com.xpsoft.oa.model.task.Appointment;
 import java.util.ArrayList;
 import java.util.List;
 
 public class AppointmentDaoImpl extends BaseDaoImpl<Appointment>
   implements AppointmentDao
 {
   public AppointmentDaoImpl()
   {
     super(Appointment.class);
   }
 
   public List showAppointmentByUserId(Long userId, PagingBean pb)
   {
     ArrayList paramList = new ArrayList();
     StringBuffer hql = new StringBuffer("select vo from Appointment vo where vo.appUser.userId=?");
     paramList.add(userId);
     hql.append(" order by vo.appointId desc");
 
     return findByHql(hql.toString(), paramList.toArray(), pb);
   }
 }

