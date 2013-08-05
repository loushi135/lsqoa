 package com.xpsoft.oa.dao.communicate.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.communicate.MailDao;
 import com.xpsoft.oa.model.communicate.Mail;
 
 public class MailDaoImpl extends BaseDaoImpl<Mail>
   implements MailDao
 {
   public MailDaoImpl()
   {
     super(Mail.class);
   }
 }

