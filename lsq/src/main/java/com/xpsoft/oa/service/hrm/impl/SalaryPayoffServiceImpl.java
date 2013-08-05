 package com.xpsoft.oa.service.hrm.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.hrm.SalaryPayoffDao;
 import com.xpsoft.oa.model.hrm.SalaryPayoff;
 import com.xpsoft.oa.service.hrm.SalaryPayoffService;
 
 public class SalaryPayoffServiceImpl extends BaseServiceImpl<SalaryPayoff>
   implements SalaryPayoffService
 {
   private SalaryPayoffDao dao;
 
   public SalaryPayoffServiceImpl(SalaryPayoffDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

