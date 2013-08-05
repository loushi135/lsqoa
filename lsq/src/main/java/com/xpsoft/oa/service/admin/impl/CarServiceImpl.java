 package com.xpsoft.oa.service.admin.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.admin.CarDao;
 import com.xpsoft.oa.model.admin.Car;
 import com.xpsoft.oa.service.admin.CarService;
 
 public class CarServiceImpl extends BaseServiceImpl<Car>
   implements CarService
 {
   private CarDao dao;
 
   public CarServiceImpl(CarDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

