 package com.xpsoft.oa.dao.flow.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.flow.ProTypeDao;
 import com.xpsoft.oa.model.flow.ProType;
 
 public class ProTypeDaoImpl extends BaseDaoImpl<ProType>
   implements ProTypeDao
 {
   public ProTypeDaoImpl()
   {
     super(ProType.class);
   }
 }

