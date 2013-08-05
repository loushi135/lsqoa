 package com.xpsoft.oa.service.admin.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.admin.OfficeGoodsTypeDao;
 import com.xpsoft.oa.model.admin.OfficeGoodsType;
 import com.xpsoft.oa.service.admin.OfficeGoodsTypeService;
 
 public class OfficeGoodsTypeServiceImpl extends BaseServiceImpl<OfficeGoodsType>
   implements OfficeGoodsTypeService
 {
   private OfficeGoodsTypeDao dao;
 
   public OfficeGoodsTypeServiceImpl(OfficeGoodsTypeDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

