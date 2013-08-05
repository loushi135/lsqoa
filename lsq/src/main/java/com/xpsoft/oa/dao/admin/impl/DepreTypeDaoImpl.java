 package com.xpsoft.oa.dao.admin.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.admin.DepreTypeDao;
 import com.xpsoft.oa.model.admin.DepreType;
 
 public class DepreTypeDaoImpl extends BaseDaoImpl<DepreType>
   implements DepreTypeDao
 {
   public DepreTypeDaoImpl()
   {
     super(DepreType.class);
   }
 }

