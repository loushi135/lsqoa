 package com.xpsoft.oa.dao.admin.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.admin.CarDao;
 import com.xpsoft.oa.model.admin.Car;
 
 public class CarDaoImpl extends BaseDaoImpl<Car>
   implements CarDao
 {
   public CarDaoImpl()
   {
     super(Car.class);
   }
 }

