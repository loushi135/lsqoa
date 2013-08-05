 package com.xpsoft.oa.dao.customer.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.customer.CusConnectionDao;
 import com.xpsoft.oa.model.customer.CusConnection;
 
 public class CusConnectionDaoImpl extends BaseDaoImpl<CusConnection>
   implements CusConnectionDao
 {
   public CusConnectionDaoImpl()
   {
     super(CusConnection.class);
   }
 }

