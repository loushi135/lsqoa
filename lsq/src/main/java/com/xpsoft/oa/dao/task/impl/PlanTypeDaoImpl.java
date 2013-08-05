 package com.xpsoft.oa.dao.task.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.task.PlanTypeDao;
 import com.xpsoft.oa.model.task.PlanType;
 
 public class PlanTypeDaoImpl extends BaseDaoImpl<PlanType>
   implements PlanTypeDao
 {
   public PlanTypeDaoImpl()
   {
     super(PlanType.class);
   }
 }
