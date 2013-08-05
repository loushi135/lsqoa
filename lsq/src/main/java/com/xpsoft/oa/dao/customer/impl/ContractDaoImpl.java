 package com.xpsoft.oa.dao.customer.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.customer.ContractDao;
 import com.xpsoft.oa.model.customer.Contract;
 
 public class ContractDaoImpl extends BaseDaoImpl<Contract>
   implements ContractDao
 {
   public ContractDaoImpl()
   {
     super(Contract.class);
   }
 }

