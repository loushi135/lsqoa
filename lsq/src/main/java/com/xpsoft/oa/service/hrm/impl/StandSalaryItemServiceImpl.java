 package com.xpsoft.oa.service.hrm.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.hrm.StandSalaryItemDao;
 import com.xpsoft.oa.model.hrm.StandSalaryItem;
 import com.xpsoft.oa.service.hrm.StandSalaryItemService;
 import java.util.List;
 
 public class StandSalaryItemServiceImpl extends BaseServiceImpl<StandSalaryItem>
   implements StandSalaryItemService
 {
   private StandSalaryItemDao dao;
 
   public StandSalaryItemServiceImpl(StandSalaryItemDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<StandSalaryItem> getAllByStandardId(Long standardId)
   {
     return this.dao.getAllByStandardId(standardId);
   }
 }

