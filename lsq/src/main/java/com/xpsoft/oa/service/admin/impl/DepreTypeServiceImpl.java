 package com.xpsoft.oa.service.admin.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.admin.DepreTypeDao;
 import com.xpsoft.oa.model.admin.DepreType;
 import com.xpsoft.oa.service.admin.DepreTypeService;
 
 public class DepreTypeServiceImpl extends BaseServiceImpl<DepreType>
   implements DepreTypeService
 {
   private DepreTypeDao dao;
 
   public DepreTypeServiceImpl(DepreTypeDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

