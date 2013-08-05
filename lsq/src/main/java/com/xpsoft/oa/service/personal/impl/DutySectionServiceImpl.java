 package com.xpsoft.oa.service.personal.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.personal.DutySectionDao;
 import com.xpsoft.oa.model.personal.DutySection;
 import com.xpsoft.oa.service.personal.DutySectionService;
 
 public class DutySectionServiceImpl extends BaseServiceImpl<DutySection>
   implements DutySectionService
 {
   private DutySectionDao dao;
 
   public DutySectionServiceImpl(DutySectionDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

