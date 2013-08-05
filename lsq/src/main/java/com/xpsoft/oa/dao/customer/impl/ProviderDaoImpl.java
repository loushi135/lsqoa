 package com.xpsoft.oa.dao.customer.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.customer.ProviderDao;
 import com.xpsoft.oa.model.customer.Provider;
 
 public class ProviderDaoImpl extends BaseDaoImpl<Provider>
   implements ProviderDao
 {
   public ProviderDaoImpl()
   {
     super(Provider.class);
   }
 }

