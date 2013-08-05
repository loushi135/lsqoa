 package com.xpsoft.oa.service.system.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.system.SystemLogDao;
 import com.xpsoft.oa.model.system.SystemLog;
 import com.xpsoft.oa.service.system.SystemLogService;
 
 public class SystemLogServiceImpl extends BaseServiceImpl<SystemLog>
   implements SystemLogService
 {
   private SystemLogDao dao;
 
   public SystemLogServiceImpl(SystemLogDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

