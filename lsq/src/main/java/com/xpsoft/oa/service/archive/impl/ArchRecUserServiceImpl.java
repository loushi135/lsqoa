 package com.xpsoft.oa.service.archive.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.archive.ArchRecUserDao;
 import com.xpsoft.oa.model.archive.ArchRecUser;
 import com.xpsoft.oa.service.archive.ArchRecUserService;
 import java.util.List;
 
 public class ArchRecUserServiceImpl extends BaseServiceImpl<ArchRecUser>
   implements ArchRecUserService
 {
   private ArchRecUserDao dao;
 
   public ArchRecUserServiceImpl(ArchRecUserDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List findDepAll()
   {
     return this.dao.findDepAll();
   }
 
   public ArchRecUser getByDepId(Long depId)
   {
     return this.dao.getByDepId(depId);
   }
 }

