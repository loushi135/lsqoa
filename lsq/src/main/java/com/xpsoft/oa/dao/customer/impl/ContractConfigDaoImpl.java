 package com.xpsoft.oa.dao.customer.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.customer.ContractConfigDao;
 import com.xpsoft.oa.model.customer.ContractConfig;
 
 public class ContractConfigDaoImpl extends BaseDaoImpl<ContractConfig>
   implements ContractConfigDao
 {
   public ContractConfigDaoImpl()
   {
     super(ContractConfig.class);
   }
 }

