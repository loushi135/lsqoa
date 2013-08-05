 package com.xpsoft.oa.service.admin.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.admin.AssetsTypeDao;
 import com.xpsoft.oa.model.admin.AssetsType;
 import com.xpsoft.oa.service.admin.AssetsTypeService;
 
 public class AssetsTypeServiceImpl extends BaseServiceImpl<AssetsType>
   implements AssetsTypeService
 {
   private AssetsTypeDao dao;
 
   public AssetsTypeServiceImpl(AssetsTypeDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

