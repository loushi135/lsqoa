 package com.xpsoft.oa.dao.personal.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.personal.DutySectionDao;
 import com.xpsoft.oa.model.personal.DutySection;
 
 public class DutySectionDaoImpl extends BaseDaoImpl<DutySection>
   implements DutySectionDao
 {
   public DutySectionDaoImpl()
   {
     super(DutySection.class);
   }
 }

