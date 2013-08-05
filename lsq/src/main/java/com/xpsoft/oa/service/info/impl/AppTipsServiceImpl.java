 package com.xpsoft.oa.service.info.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.info.AppTipsDao;
 import com.xpsoft.oa.model.info.AppTips;
 import com.xpsoft.oa.service.info.AppTipsService;
 
 public class AppTipsServiceImpl extends BaseServiceImpl<AppTips>
   implements AppTipsService
 {
   private AppTipsDao dao;
 
   public AppTipsServiceImpl(AppTipsDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

