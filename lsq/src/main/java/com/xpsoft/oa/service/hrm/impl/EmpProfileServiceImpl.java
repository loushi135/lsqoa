 package com.xpsoft.oa.service.hrm.impl;
 
 import com.xpsoft.core.service.impl.BaseServiceImpl;
 import com.xpsoft.oa.dao.hrm.EmpProfileDao;
 import com.xpsoft.oa.model.hrm.EmpProfile;
 import com.xpsoft.oa.service.hrm.EmpProfileService;
 
 public class EmpProfileServiceImpl extends BaseServiceImpl<EmpProfile>
   implements EmpProfileService
 {
   private EmpProfileDao dao;
 
   public EmpProfileServiceImpl(EmpProfileDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public boolean checkProfileNo(String profileNo)
   {
     return this.dao.checkProfileNo(profileNo);
   }
 }

