 package com.xpsoft.oa.dao.admin.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.admin.CarApplyDao;
 import com.xpsoft.oa.model.admin.CarApply;
 
 public class CarApplyDaoImpl extends BaseDaoImpl<CarApply>
   implements CarApplyDao
 {
   public CarApplyDaoImpl()
   {
     super(CarApply.class);
   }
 }

