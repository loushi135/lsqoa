 package com.xpsoft.oa.service.task.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.task.PlanTypeDao;
 import com.xpsoft.oa.model.task.PlanType;
 import com.xpsoft.oa.service.task.PlanTypeService;
 
 public class PlanTypeServiceImpl extends BaseServiceImpl<PlanType>
   implements PlanTypeService
 {
   private PlanTypeDao dao;
 
   public PlanTypeServiceImpl(PlanTypeDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

