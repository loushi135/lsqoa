 package com.xpsoft.oa.service.hrm.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.core.web.paging.PagingBean;
 import com.xpsoft.oa.dao.hrm.SalaryItemDao;
 import com.xpsoft.oa.model.hrm.SalaryItem;
 import com.xpsoft.oa.service.hrm.SalaryItemService;
 import java.util.List;
 
 public class SalaryItemServiceImpl extends BaseServiceImpl<SalaryItem>
   implements SalaryItemService
 {
   private SalaryItemDao dao;
 
   public SalaryItemServiceImpl(SalaryItemDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<SalaryItem> getAllExcludeId(String excludeIds, PagingBean pb)
   {
     return this.dao.getAllExcludeId(excludeIds, pb);
   }
 }

