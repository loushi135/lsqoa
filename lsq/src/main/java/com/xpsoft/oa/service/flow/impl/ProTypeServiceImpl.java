 package com.xpsoft.oa.service.flow.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.flow.ProTypeDao;
 import com.xpsoft.oa.model.flow.ProType;
 import com.xpsoft.oa.service.flow.ProTypeService;
 
 public class ProTypeServiceImpl extends BaseServiceImpl<ProType>
   implements ProTypeService
 {
   private ProTypeDao dao;
 
   public ProTypeServiceImpl(ProTypeDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

