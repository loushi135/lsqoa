 package com.xpsoft.oa.service.customer.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.customer.CusConnectionDao;
 import com.xpsoft.oa.model.customer.CusConnection;
 import com.xpsoft.oa.service.customer.CusConnectionService;
 
 public class CusConnectionServiceImpl extends BaseServiceImpl<CusConnection>
   implements CusConnectionService
 {
   private CusConnectionDao dao;
 
   public CusConnectionServiceImpl(CusConnectionDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

