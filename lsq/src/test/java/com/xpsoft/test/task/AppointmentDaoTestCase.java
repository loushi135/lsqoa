 package com.xpsoft.test.task;
 
 import com.xpsoft.oa.dao.task.AppointmentDao;
 import com.xpsoft.oa.model.task.Appointment;
 import com.xpsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class AppointmentDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private AppointmentDao appointmentDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     Appointment appointment = new Appointment();
     this.appointmentDao.save(appointment);
   }
 }

