 package com.xpsoft.oa.service.customer.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.customer.ContractConfigDao;
 import com.xpsoft.oa.model.customer.ContractConfig;
 import com.xpsoft.oa.service.customer.ContractConfigService;
 
 public class ContractConfigServiceImpl extends BaseServiceImpl<ContractConfig>
   implements ContractConfigService
 {
   private ContractConfigDao dao;
 
   public ContractConfigServiceImpl(ContractConfigDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

