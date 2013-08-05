 package com.xpsoft.oa.service.customer.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.customer.ProviderDao;
 import com.xpsoft.oa.model.customer.Provider;
 import com.xpsoft.oa.service.customer.ProviderService;
 
 public class ProviderServiceImpl extends BaseServiceImpl<Provider>
   implements ProviderService
 {
   private ProviderDao dao;
 
   public ProviderServiceImpl(ProviderDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }
