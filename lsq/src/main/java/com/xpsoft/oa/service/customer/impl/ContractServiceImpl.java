 package com.xpsoft.oa.service.customer.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.customer.ContractDao;
 import com.xpsoft.oa.model.customer.Contract;
 import com.xpsoft.oa.service.customer.ContractService;
 
 public class ContractServiceImpl extends BaseServiceImpl<Contract>
   implements ContractService
 {
   private ContractDao dao;
 
   public ContractServiceImpl(ContractDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }
