 package com.xpsoft.oa.service.system.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.system.AppFunctionDao;
 import com.xpsoft.oa.model.system.AppFunction;
 import com.xpsoft.oa.service.system.AppFunctionService;
 
 public class AppFunctionServiceImpl extends BaseServiceImpl<AppFunction>
   implements AppFunctionService
 {
   private AppFunctionDao dao;
 
   public AppFunctionServiceImpl(AppFunctionDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public AppFunction getByKey(String key)
   {
     return this.dao.getByKey(key);
   }
 }

