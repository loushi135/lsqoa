 package com.xpsoft.oa.service.communicate.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.communicate.MailDao;
 import com.xpsoft.oa.model.communicate.Mail;
 import com.xpsoft.oa.service.communicate.MailService;
 
 public class MailServiceImpl extends BaseServiceImpl<Mail>
   implements MailService
 {
   private MailDao dao;
 
   public MailServiceImpl(MailDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

