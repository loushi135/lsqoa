 package com.xpsoft.oa.service.admin.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.admin.FixedAssetsDao;
 import com.xpsoft.oa.model.admin.FixedAssets;
 import com.xpsoft.oa.service.admin.FixedAssetsService;
 
 public class FixedAssetsServiceImpl extends BaseServiceImpl<FixedAssets>
   implements FixedAssetsService
 {
   private FixedAssetsDao dao;
 
   public FixedAssetsServiceImpl(FixedAssetsDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }
