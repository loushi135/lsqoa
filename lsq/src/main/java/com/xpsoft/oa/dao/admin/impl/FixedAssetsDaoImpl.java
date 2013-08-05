 package com.xpsoft.oa.dao.admin.impl;
 
 import com.xpsoft.core.dao.impl.BaseDaoImpl;
 import com.xpsoft.oa.dao.admin.FixedAssetsDao;
 import com.xpsoft.oa.model.admin.FixedAssets;
 
 public class FixedAssetsDaoImpl extends BaseDaoImpl<FixedAssets>
   implements FixedAssetsDao
 {
   public FixedAssetsDaoImpl()
   {
     super(FixedAssets.class);
   }
 }

