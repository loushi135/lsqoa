 package com.xpsoft.oa.service.admin.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.admin.CarApplyDao;
 import com.xpsoft.oa.model.admin.CarApply;
 import com.xpsoft.oa.service.admin.CarApplyService;
 
 public class CarApplyServiceImpl extends BaseServiceImpl<CarApply>
   implements CarApplyService
 {
   private CarApplyDao dao;
 
   public CarApplyServiceImpl(CarApplyDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

