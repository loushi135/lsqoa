 package com.xpsoft.oa.service.hrm.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.hrm.StandSalaryDao;
 import com.xpsoft.oa.model.hrm.StandSalary;
 import com.xpsoft.oa.service.hrm.StandSalaryService;
 import java.util.List;
 
 public class StandSalaryServiceImpl extends BaseServiceImpl<StandSalary>
   implements StandSalaryService
 {
   private StandSalaryDao dao;
 
   public StandSalaryServiceImpl(StandSalaryDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public boolean checkStandNo(String standardNo)
   {
     return this.dao.checkStandNo(standardNo);
   }
 
   public List<StandSalary> findByPassCheck()
   {
     return this.dao.findByPassCheck();
   }
 }

