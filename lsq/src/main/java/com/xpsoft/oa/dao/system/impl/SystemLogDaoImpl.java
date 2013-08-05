 package com.xpsoft.oa.dao.system.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.system.SystemLogDao;
 import com.xpsoft.oa.model.system.SystemLog;
 
 public class SystemLogDaoImpl extends BaseDaoImpl<SystemLog>
   implements SystemLogDao
 {
   public SystemLogDaoImpl()
   {
     super(SystemLog.class);
   }
 }

