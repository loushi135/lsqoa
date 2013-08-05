 package com.xpsoft.oa.service.system.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.system.FunUrlDao;
 import com.xpsoft.oa.model.system.FunUrl;
 import com.xpsoft.oa.service.system.FunUrlService;
 
 public class FunUrlServiceImpl extends BaseServiceImpl<FunUrl>
   implements FunUrlService
 {
   private FunUrlDao dao;
 
   public FunUrlServiceImpl(FunUrlDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public FunUrl getByPathFunId(String path, Long funId)
   {
     return this.dao.getByPathFunId(path, funId);
   }
 }

